package com.example.sqlitenotesapp;

import java.util.ArrayList;

public interface FetchDatabaseResults {
    void onDataFetched(ArrayList<NotesModel> notesModelArrayList);
}
