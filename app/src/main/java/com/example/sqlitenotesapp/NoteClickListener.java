package com.example.sqlitenotesapp;

public interface NoteClickListener {
    void onNoteClicked(int rowId, String subject, String description);
}
