package com.zama.example.notesapp.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_database")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "notes_title")
    public String NotesTitle;

    @ColumnInfo(name = "notes_subtitle")
    public String NotesSubtitle;

    @ColumnInfo(name = "notes_date")
    public String NotesDate;

    @ColumnInfo(name = "notes")
    public String Notes;

    @ColumnInfo(name = "notes_priority")
    public String NotesPriority;


}
