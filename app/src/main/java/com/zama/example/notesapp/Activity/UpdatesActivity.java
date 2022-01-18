package com.zama.example.notesapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
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

        if(spriority.equals("1")){
            binding.greenpty.setImageResource(R.drawable.ic_baseline_done_24);
        }else if(spriority.equals("2")){
            binding.Yellowpty.setImageResource(R.drawable.ic_baseline_done_24);

        }else if(spriority.equals("3")){
            binding.redpty.setImageResource(R.drawable.ic_baseline_done_24);

        }

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





        binding.llAddmg1.setOnClickListener(view -> {
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

            binding.btnupdatefloating.setOnClickListener(view -> {

                String title = binding.upTitle.getText().toString();
                String subtitle = binding.upSubtitle.getText().toString();
                String notes = binding.upnotes.getText().toString();


                UpateNotes(title, subtitle, notes);

            });


        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.ic_delete){

            BottomSheetDialog sheetDialog =new BottomSheetDialog(UpdatesActivity.this,
                    R.style.BottomSheetStyle);
            View view = LayoutInflater.from(UpdatesActivity.this).inflate(R.layout.delete_bottom_sheet,
                    (LinearLayout) findViewById(R.id.bottomSheet));

            sheetDialog.setContentView(view);

            TextView yes, no;

            yes = view.findViewById(R.id.delete_yes);
            no= view.findViewById(R.id.delete_no);

            yes.setOnClickListener(view1 -> {
                notesViewModel.deleteNote(iid);
                finish();
            });

            no.setOnClickListener(view1 -> {
                sheetDialog.dismiss();
            });
            sheetDialog.show();
        }
        return true;
    }

}
