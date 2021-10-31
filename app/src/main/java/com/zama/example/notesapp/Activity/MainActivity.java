package com.zama.example.notesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zama.example.notesapp.Adapter.NotesAdapter;
import com.zama.example.notesapp.InsertNotesActivity;
import com.zama.example.notesapp.NotesViewModel;
import com.zama.example.notesapp.R;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnMain;
    NotesViewModel notesViewModel;
    RecyclerView notesRecycler;
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMain = findViewById(R.id.btnMain);
        notesRecycler = findViewById(R.id.notesRecycler);

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);


        btnMain.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));


        });

        notesViewModel.getallNotes.observe(this, notes -> {

            notesRecycler.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new NotesAdapter(this, notes);
            notesRecycler.setAdapter(adapter);
        });

    }

}
