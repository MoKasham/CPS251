package com.example.add_contacts

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {

    @Insert
    fun insertContact(contact: Contact)

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contact>>

    @Query("DELETE FROM contacts WHERE contactId = :id")
    fun deleteContactById(id: Int)
    @Query("SELECT * FROM contacts WHERE contactName LIKE '%' || :search || '%'")
    fun searchContacts(search: String): LiveData<List<Contact>>

    @Query("SELECT * FROM contacts ORDER BY contactName ASC")
    fun getContactsAsc(): LiveData<List<Contact>>

    @Query("SELECT * FROM contacts ORDER BY contactName DESC")
    fun getContactsDesc(): LiveData<List<Contact>>

}
