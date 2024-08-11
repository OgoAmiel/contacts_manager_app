package com.example.contactsmanagerapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final ContactsDAO contactsDAO;
    ExecutorService executor;
    Handler handler;

    public Repository(Application application) {

        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactsDAO = contactDatabase.getContactsDAO();

        //Used for Background Database Operations
        executor = Executors.newSingleThreadExecutor();

        //Used for updating the UI
        handler = new Handler(Looper.getMainLooper());
    }
    public void addContact(Contacts contact){

        //Runnable: Executing Tasks on Separate Threads
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactsDAO.insert(contact);

            }
        });
    }
    public void deleteContact(Contacts contact){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactsDAO.delete(contact);

            }
        });
    }
    public LiveData<List<Contacts>> getAllContacts(){
        return contactsDAO.getAllContacts();
    }
}
