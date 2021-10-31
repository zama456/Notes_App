package com.zama.example.notesapp.NotesDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.zama.example.notesapp.Model.Notes;
import com.zama.example.notesapp.NotesDao.NotesDao;

@Database(entities = {Notes.class}, version=1)
public abstract class NotesDatabase extends RoomDatabase {

    private androidx.room.Room Room;

    public abstract NotesDao notesDao();

    public static NotesDatabase INSTANCE;

    public static NotesDatabase getDatabaseInstance(Context context) {
        if (INSTANCE == null){
            INSTANCE= androidx.room.Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class,"Notes_Database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
