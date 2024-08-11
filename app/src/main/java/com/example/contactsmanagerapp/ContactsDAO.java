package com.example.contactsmanagerapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface ContactsDAO {
    @Insert
    void insert(Contacts contact);

    @Delete
    void delete(Contacts contact);

    @Query("SELECT * FROM contacts_table")
    LiveData<List<Contacts>> getAllContacts();


}
