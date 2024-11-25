package com.example.timer_project_richard

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.SystemClock

class StopWatchService : Service() {

    private val binder = StopWatchBinder()
    private val handler = Handler()
    private var startTime = 0L
    private var elapsedTime = 0L
    private var running = false

    private val updateRunnable = object : Runnable {
        override fun run() {
            if (running) {
                elapsedTime = SystemClock.elapsedRealtime() - startTime
                handler.postDelayed(this, 100)
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder = binder

    fun startStopwatch() {
        if (!running) {
            startTime = SystemClock.elapsedRealtime() - elapsedTime
            running = true
            handler.post(updateRunnable)
        }
    }

    fun pauseStopwatch() {
        if (running) {
            running = false
            handler.removeCallbacks(updateRunnable)
        }
    }

    fun resetStopwatch() {
        running = false
        handler.removeCallbacks(updateRunnable)
        elapsedTime = 0L
    }

    fun getElapsedTime(): Long {
        return elapsedTime
    }

    inner class StopWatchBinder : Binder() {
        fun getService(): StopWatchService = this@StopWatchService
    }
}
