package com.example.add_contacts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var contactViewModel: ContactViewModel
    private lateinit var adapter: ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = ContactAdapter(listOf()) { contact ->
            deleteContact(contact)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe all contacts by default
        contactViewModel.allContacts.observe(this, Observer { contacts ->
            adapter.updateContacts(contacts)
        })
        findViewById<Button>(R.id.btnAsc).setOnClickListener {
            contactViewModel.getContactsAscending().observe(this, Observer { contacts ->
                adapter.updateContacts(contacts)
            })
        }

        findViewById<Button>(R.id.btnDesc).setOnClickListener {
            contactViewModel.getContactsDescending().observe(this, Observer { contacts ->
                adapter.updateContacts(contacts)
            })
        }

        // Add button listener for adding contacts
        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            addContact()
        }

        // Add search functionality
        findViewById<SearchView>(R.id.searchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Optional: Handle search action button click
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchQuery = newText?.trim() ?: ""
                if (searchQuery.isNotEmpty()) {
                    contactViewModel.searchContacts(searchQuery).observe(this@MainActivity, Observer { contacts ->
                        if (contacts.isEmpty()) {
                            Toast.makeText(this@MainActivity, "No matching contacts found", Toast.LENGTH_SHORT).show()
                        } else {
                            adapter.updateContacts(contacts)
                        }
                    })
                } else {
                    // Reset to show all contacts if search query is empty
                    contactViewModel.allContacts.observe(this@MainActivity, Observer { contacts ->
                        adapter.updateContacts(contacts)
                    })
                }
                return true
            }
        })


    }
    private fun addContact() {
        val name = findViewById<EditText>(R.id.etName).text.toString()
        val phone = findViewById<EditText>(R.id.etPhone).text.toString()

        if (name.isNotBlank() && phone.isNotBlank()) {
            val contact = Contact(contactName = name, contactPhone = phone)
            contactViewModel.insertContact(contact)
        } else {
            Toast.makeText(this, "Enter valid name and phone number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteContact(contact: Contact) {
        contactViewModel.deleteContactById(contact.contactId)
    }

}