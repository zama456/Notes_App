package com.zama.example.notesapp.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.zama.example.notesapp.Model.Notes;
import com.zama.example.notesapp.NotesViewModel;
import com.zama.example.notesapp.R;
import com.zama.example.notesapp.databinding.ActivityInsertNotesBinding;

import java.io.InputStream;
import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding binding;
    String title, subtitle, notes;
    NotesViewModel notesViewModel;
    ImageView AddVI;
    String priority = "1";
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

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


        binding.btnNotesdone.setOnClickListener(view -> {
            title = binding.etTitle.getText().toString();
            subtitle = binding.etSubtitle.getText().toString();
            notes = binding.notesData.getText().toString();
            CreateNotes(title, subtitle, notes);

        });


        binding.llAddImg.setOnClickListener(view -> {
            Toast.makeText(this, "add Image", Toast.LENGTH_SHORT).show();
            Intent addImg = new Intent(Intent.ACTION_PICK);
            addImg.setType("image/*");
            startActivityForResult(addImg, 101);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (data != null) {
                Uri imgUri = data.getData();
                binding.addImgIV.setImageURI(imgUri);

            }
        }
    }

    private void CreateNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMM d yyyy", date.getTime());

        Notes notes1 = new Notes();
        notes1.NotesTitle = title;
        notes1.NotesSubtitle = subtitle;
        notes1.NotesDate = sequence.toString();
        notes1.Notes = notes;
        notes1.NotesPriority = priority;
        Log.d("CreateNotesTest", "CreateNotes:" + notes1);
        notesViewModel.insertNote(notes1);
        Toast.makeText(this, "Notes Created Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void selectImage() {

    }

}