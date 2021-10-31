package com.zama.example.notesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import com.zama.example.notesapp.Model.Notes;
import com.zama.example.notesapp.NotesViewModel;
import com.zama.example.notesapp.R;
import com.zama.example.notesapp.databinding.ActivityUpdatesBinding;

import java.util.Date;

public class UpdatesActivity extends AppCompatActivity {

    ActivityUpdatesBinding binding;
    String priority = "1";
    String stitle,ssubtitle,snotes,spriority;
    int iid;
    NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdatesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);


        iid = getIntent().getIntExtra("id",0);
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        spriority = getIntent().getStringExtra("priority");
        snotes = getIntent().getStringExtra("note");

        binding.upTitle.setText(stitle);
        binding.upSubtitle.setText(ssubtitle);
        binding.upnotes.setText(snotes);


        binding.greenpty.setOnClickListener(view -> {
            binding.greenpty.setImageResource(R.drawable.ic_baseline_done_24);
            binding.Yellowpty.setImageResource(0);
            binding.redpty.setImageResource(0);
            priority = "1";
        });


        binding.Yellowpty.setOnClickListener(view -> {
            binding.greenpty.setImageResource(0);
            binding.Yellowpty.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redpty.setImageResource(0);
            priority = "2";
        });


        binding.redpty.setOnClickListener(view -> {
            binding.greenpty.setImageResource(0);
            binding.Yellowpty.setImageResource(0);
            binding.redpty.setImageResource(R.drawable.ic_baseline_done_24);

            priority = "3";
        });

        binding.btnupdatefloating.setOnClickListener(view -> {

         String   title = binding.upTitle.getText().toString();
         String   subtitle = binding.upSubtitle.getText().toString();
         String   notes = binding.upnotes.getText().toString();

            UpateNotes(title, subtitle, notes);

        });



    }

    private void UpateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMM d yyyy", date.getTime());

        Notes updatenotes = new Notes();
        updatenotes.id = iid;
        updatenotes.NotesTitle = title;
        updatenotes.NotesSubtitle = subtitle;
        updatenotes.NotesDate = sequence.toString();
        updatenotes.Notes = notes;
        updatenotes.NotesPriority = priority;
        Log.d("UpdateNotesTest", "UpdateNotes:" + updatenotes);
        notesViewModel.updateNote(updatenotes);

        Toast.makeText(this, "Notes Updated Successfully", Toast.LENGTH_SHORT).show();
        finish();

    }
}