package com.zama.example.notesapp.Activity;

import static com.zama.example.notesapp.R.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zama.example.notesapp.Adapter.NotesAdapter;
import com.zama.example.notesapp.Model.Notes;
import com.zama.example.notesapp.NotesViewModel;
import com.zama.example.notesapp.R;

import java.util.ArrayList;
import java.util.List;

import kotlin.UInt;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnMain;
    NotesViewModel notesViewModel;
    RecyclerView notesRecycler;
    NotesAdapter adapter;
    TextView NoFilter, HighToLow, LowToHigh,AddVI;

    List<Notes> filterNotesalllist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        btnMain = findViewById(id.btnMain);
        notesRecycler = findViewById(id.notesRecycler);

        HighToLow = findViewById(id.HighToLow);
        LowToHigh = findViewById(id.LowToHigh);
        NoFilter = findViewById(id.NoFilter);
//
//        AddVI = findViewById(id.AddIV);
//       AddVI.setOnClickListener(view -> {
//           Intent intent = new Intent();
////               intent.setType("image/*");
////               intent.setAction(Intent.ACTION_GET_CONTENT);
////               startActivityForResult(Intent.createChooser(intent,"Title"),SELECT_IMAGE_CODE);
//       });

        NoFilter.setBackgroundResource(drawable.filter_selected_shape);

        NoFilter.setOnClickListener(view -> {
            LoadData(0);
            HighToLow.setBackgroundResource(drawable.filter_un_shape);
            LowToHigh.setBackgroundResource(drawable.filter_un_shape);
            NoFilter.setBackgroundResource(drawable.filter_selected_shape);
        });
        LowToHigh.setOnClickListener(view -> {
            LoadData(1);
            HighToLow.setBackgroundResource(drawable.filter_un_shape);
            LowToHigh.setBackgroundResource(drawable.filter_selected_shape);
            NoFilter.setBackgroundResource(drawable.filter_un_shape);
        });
        HighToLow.setOnClickListener(view -> {
            LoadData(2);
            HighToLow.setBackgroundResource(drawable.filter_selected_shape);
            LowToHigh.setBackgroundResource(drawable.filter_un_shape);
            NoFilter.setBackgroundResource(drawable.filter_un_shape);
        });

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);


        btnMain.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));


        });

        notesViewModel.getallNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
                filterNotesalllist = notes;

            }
        });
    }


    private void LoadData(int i) {

        if (i == 0) {
            notesViewModel.getallNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNotesalllist = notes;

                }
            });
        } else if (i == 1) {
            notesViewModel.HighToLow.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNotesalllist = notes;

                }
            });
        } else if (i == 2) {
            notesViewModel.LowToHigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNotesalllist = notes;

                }
            });
        }
    }

    public void setAdapter(List<Notes> notes) {


        notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(this, notes);
        notesRecycler.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NotesFilter(newText);
                return false;
            }
        });

        return true;
    }

    private void NotesFilter(String newText) {

        ArrayList<Notes> FilterNames = new ArrayList<>();

        for (Notes notes : this.filterNotesalllist) {
            if (notes.NotesTitle.contains(newText) || notes.NotesSubtitle.contains(newText)) {
                FilterNames.add(notes);
            }
        }
        this.adapter.searchNotes(FilterNames);
    }
}


