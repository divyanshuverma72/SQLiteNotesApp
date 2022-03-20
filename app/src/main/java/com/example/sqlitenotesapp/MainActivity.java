package com.example.sqlitenotesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.sqlitenotesapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NoteClickListener, FetchDatabaseResults {

    private NotesAdapter notesAdapter;
    ActivityMainBinding activityMainBinding;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        notesAdapter = new NotesAdapter(this);
        notesAdapter.setHasStableIds(true);
        notesAdapter.setNoteClickListener(this);

        activityMainBinding.recycelrView.setHasFixedSize(true);
        activityMainBinding.recycelrView.setAdapter(notesAdapter);
        activityMainBinding.recycelrView.setLayoutManager(new LinearLayoutManager(this));

        AppPresenter appPresenter = new AppPresenter(this, this);
        appPresenter.requestNotesFromDatabase();
    }

    @Override
    public void onNoteClicked(int rowId, String subject, String description) {
        Intent intent = new Intent(this, AddNotesActivity.class);
        intent.putExtra("rowId", rowId);
        intent.putExtra("subject", subject);
        intent.putExtra("description", description);

        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addNotes) {
            startActivity(new Intent(this, AddNotesActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataFetched(ArrayList<NotesModel> notesModelArrayList) {
        if (notesModelArrayList.isEmpty()) {
            activityMainBinding.emptyMsg.setVisibility(View.VISIBLE);
        } else {
            activityMainBinding.emptyMsg.setVisibility(View.GONE);
        }
        notesAdapter.setNotesList(notesModelArrayList);
    }
}