package com.zama.example.notesapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zama.example.notesapp.Activity.UpdatesActivity;
import com.zama.example.notesapp.Activity.MainActivity;
import com.zama.example.notesapp.Model.Notes;
import com.zama.example.notesapp.R;
import com.zama.example.notesapp.databinding.ActivityItemNotesBinding;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotesitem;


    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
        allNotesitem = new ArrayList<>(notes);
    }

    public void searchNotes(List<Notes> filterredNames){
        this.notes = filterredNames;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesViewHolder(ActivityItemNotesBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.notesViewHolder holder, int position) {


        Notes note = notes.get(position);
        switch (note.NotesPriority) {
            case "1":
                holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
                break;
            case "2":
                holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
                break;
            case "3":
                holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
                break;
        }


        holder.title.setText(note.NotesTitle);
        holder.subtitle.setText(note.NotesSubtitle);
        holder.notesDate.setText(note.NotesDate);

        holder.itemView.setOnClickListener(view -> {

            Intent intent = new Intent(mainActivity, UpdatesActivity.class);

            intent.putExtra("id",note.id);
            intent.putExtra("title",note.NotesTitle);
            intent.putExtra("subtitle",note.NotesSubtitle);
            intent.putExtra("priority",note.NotesPriority);
            intent.putExtra("note",note.Notes);

            mainActivity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class notesViewHolder extends RecyclerView.ViewHolder {

        TextView title, subtitle, notesDate;
        View notesPriority;

        public notesViewHolder(ActivityItemNotesBinding binding) {
            super(binding.getRoot());

            /*title = itemView.findViewById(R.id.noteTitleTV);
            subtitle = itemView.findViewById(R.id.notesSubtitleTV);
            notesDate = itemView.findViewById(R.id.notesDateTV);
            notesPriority = itemView.findViewById(R.id.notespriority);*/
            title = binding.noteTitleTV;
            subtitle = binding.notesSubtitleTV;
            notesDate = binding.notesDateTV;
            notesPriority = binding.notespriority;
        }
    }


}
