package com.example.timer_project_richard

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.example.timer_project_richard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var handler: Handler
    private var stopwatchService: StopWatchService? = null
    private var isBound = false

    private val updateRunnable = object : Runnable {
        override fun run() {
            stopwatchService?.let {
                val elapsedTime = it.getElapsedTime()
                updateUI(elapsedTime)
            }
            handler.postDelayed(this, 100)
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as StopWatchService.StopWatchBinder
            stopwatchService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            stopwatchService = null
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handler = Handler()

        Intent(this, StopWatchService::class.java).also {
            bindService(it, serviceConnection, Context.BIND_AUTO_CREATE)
        }

        binding.startButton.setOnClickListener {
            stopwatchService?.startStopwatch()
        }

        binding.pauseButton.setOnClickListener {
            stopwatchService?.pauseStopwatch()
        }

        binding.resetButton.setOnClickListener {
            stopwatchService?.resetStopwatch()
            updateUI(0)
        }
    }

    override fun onResume() {
        super.onResume()
        handler.post(updateRunnable)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(updateRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
    }

    private fun updateUI(elapsedTime: Long) {
        val milliseconds = (elapsedTime % 1000) / 10
        val seconds = (elapsedTime / 1000) % 60
        val minutes = (elapsedTime / (1000 * 60)) % 60
        val hours = (elapsedTime / (1000 * 60 * 60)) % 24
        binding.timeTextView.text = String.format("%02d:%02d:%02d.%02d", hours, minutes, seconds, milliseconds)
    }
}
