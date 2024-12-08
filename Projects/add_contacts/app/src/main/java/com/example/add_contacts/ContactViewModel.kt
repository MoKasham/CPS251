package com.example.add_contacts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val contactDao: ContactDao = ContactDatabase.getDatabase(application).contactDao()
    val allContacts: LiveData<List<Contact>> = contactDao.getAllContacts()

    fun searchContacts(searchQuery: String): LiveData<List<Contact>> {
        return contactDao.searchContacts(searchQuery)
    }

    fun insertContact(contact: Contact) {
        CoroutineScope(Dispatchers.IO).launch {
            contactDao.insertContact(contact)
        }
    }

    fun deleteContactById(contactId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            contactDao.deleteContactById(contactId)
        }
    }

    // Fetch contacts in ascending order
    fun getContactsAscending(): LiveData<List<Contact>> {
        return contactDao.getContactsAsc()
    }

    // Fetch contacts in descending order
    fun getContactsDescending(): LiveData<List<Contact>> {
        return contactDao.getContactsDesc()
    }
}

