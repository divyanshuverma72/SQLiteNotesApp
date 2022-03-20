package com.example.sqlitenotesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitenotesapp.databinding.NotesLayoutBinding;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ItemViewHolder> {

    private final ArrayList<NotesModel> notesModelArrayList;
    private NotesLayoutBinding notesLayoutBinding;
    private NoteClickListener noteClickListener;
    private final Context context;

    public NotesAdapter(Context context) {
        this.notesModelArrayList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        notesLayoutBinding = NotesLayoutBinding.inflate(LayoutInflater.from(context));
        return new ItemViewHolder(notesLayoutBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.subjectName.setText(notesModelArrayList.get(position).getSubject());
        holder.descName.setText(notesModelArrayList.get(position).getDescription());
        holder.date.setText(notesModelArrayList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return notesModelArrayList.size();
    }

    public void setNotesList(ArrayList<NotesModel> notesModelArrayList) {
        this.notesModelArrayList.addAll(notesModelArrayList);
    }

    public void setNoteClickListener(NoteClickListener noteClickListener) {
        this.noteClickListener = noteClickListener;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView subjectName;
        private final TextView descName;
        private final TextView date;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            subjectName = NotesAdapter.this.notesLayoutBinding.title;
            descName = NotesAdapter.this.notesLayoutBinding.description;
            date = NotesAdapter.this.notesLayoutBinding.date;

            NotesAdapter.this.notesLayoutBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            noteClickListener.onNoteClicked(notesModelArrayList.get(getAdapterPosition()).getRowId(), notesModelArrayList.get(getAdapterPosition()).getSubject(),
                    notesModelArrayList.get(getAdapterPosition()).getDescription());
        }
    }
}
