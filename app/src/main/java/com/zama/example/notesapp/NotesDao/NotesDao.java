package com.zama.example.notesapp.NotesDao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zama.example.notesapp.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("Select * From Notes_database")
    LiveData< List<Notes>> getallNotes();


    @Query("Select * From Notes_database ORDER By notes_priority DESC")
    LiveData< List<Notes>> HighToLow();


    @Query("Select * From Notes_database ORDER By notes_priority ASC")
    LiveData< List<Notes>> LowToHigh();

    @Insert
    void insertNotes(Notes...notes);

    @Query("DELETE From Notes_database WHERE id=:id ")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);
}
