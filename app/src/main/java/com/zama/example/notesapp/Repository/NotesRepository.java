package com.zama.example.notesapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.zama.example.notesapp.Model.Notes;
import com.zama.example.notesapp.NotesDao.NotesDao;
import com.zama.example.notesapp.NotesDatabase.NotesDatabase;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getallNotes;

    public LiveData<List<Notes>> HighToLow;
    public LiveData<List<Notes>> LowToHigh;


    public NotesRepository(Application application) {
        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
        notesDao = database.notesDao();
        getallNotes = notesDao.getallNotes();
        HighToLow = notesDao.HighToLow();
        LowToHigh = notesDao.LowToHigh();
    }

    public void insertNotes(Notes notes) {
        notesDao.insertNotes(notes);
    }

    public void deleteNotes(int id) {
        notesDao.deleteNotes(id);
    }

    public void updateNotes(Notes notes) {
        notesDao.updateNotes(notes);
    }
}
