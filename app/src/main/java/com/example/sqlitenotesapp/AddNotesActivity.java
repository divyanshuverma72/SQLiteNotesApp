package com.example.sqlitenotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sqlitenotesapp.databinding.ActivityAddNotesBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNotesActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityAddNotesBinding activityAddNotesBinding;
    private int rowId;
    private AppPresenter appPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAddNotesBinding = ActivityAddNotesBinding.inflate(getLayoutInflater());

        setContentView(activityAddNotesBinding.getRoot());
        appPresenter = new AppPresenter(this);
        rowId = getIntent().getIntExtra("rowId", 0);

        if (rowId != 0) {
            setTitle("Update Record");
            activityAddNotesBinding.addButton.setVisibility(View.GONE);
            activityAddNotesBinding.deleteButton.setVisibility(View.VISIBLE);
            activityAddNotesBinding.updateButton.setVisibility(View.VISIBLE);

            activityAddNotesBinding.title.setText(getIntent().getStringExtra("subject"));
            activityAddNotesBinding.description.setText(getIntent().getStringExtra("description"));
        } else {
            setTitle("Add Record");
            activityAddNotesBinding.addButton.setVisibility(View.VISIBLE);
            activityAddNotesBinding.deleteButton.setVisibility(View.GONE);
            activityAddNotesBinding.updateButton.setVisibility(View.GONE);
        }

        activityAddNotesBinding.addButton.setOnClickListener(this);
        activityAddNotesBinding.updateButton.setOnClickListener(this);
        activityAddNotesBinding.deleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String titleName = activityAddNotesBinding.title.getText().toString();
        final String description = activityAddNotesBinding.description.getText().toString();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM, yyyy hh.mm aa", Locale.ENGLISH);
        Date date = new Date();
        final String modifiedDate = formatter.format(date);

        if (view.getId() == R.id.addButton) {
            appPresenter.requestDataBaseEntryInsertion(titleName, description, modifiedDate);
        } else if (view.getId() == R.id.updateButton) {
            appPresenter.requestDataBaseEntryUpdate(rowId, titleName, description, modifiedDate);
        } else if (view.getId() == R.id.deleteButton) {
            appPresenter.requestDataBaseEntryDeletion(rowId);
        }
        startActivity(new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appPresenter.requestDataBaseClose();
    }
}