package com.example.sqlitenotesapp;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class AppPresenter {

    private FetchDatabaseResults fetchDatabaseResults;
    private final DBManager dbManager;

    AppPresenter(FetchDatabaseResults fetchDatabaseResults, Context context) {
        this.fetchDatabaseResults = fetchDatabaseResults;
        this.dbManager = new DBManager(context);
        dbManager.open();
    }

    public AppPresenter(Context context) {
        this.dbManager = new DBManager(context);
        dbManager.open();
    }

    public void requestNotesFromDatabase() {
        fetchDatabaseResults.onDataFetched(getNotesFromDatabase());
    }

    public void requestDataBaseEntryInsertion(String titleName, String description, String date) {
        dbManager.insert(titleName, description, date);
    }

    public void requestDataBaseEntryUpdate(int rowId, String titleName, String description, String date) {
        dbManager.update(rowId, titleName, description, date);
    }

    public void requestDataBaseEntryDeletion(int rowId) {
        dbManager.delete(rowId);
    }

    public ArrayList<NotesModel> getNotesFromDatabase () {
        Cursor cursor = dbManager.fetch();
        ArrayList<NotesModel> notesModelArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int indexId = cursor.getColumnIndex(DatabaseHelper.ID);
            int rowId = cursor.getInt(indexId);

            int subjectId = cursor.getColumnIndex(DatabaseHelper.SUBJECT);
            String subject = cursor.getString(subjectId);

            int descId = cursor.getColumnIndex(DatabaseHelper.DESC);
            String desc = cursor.getString(descId);

            int dateId = cursor.getColumnIndex(DatabaseHelper.DATE);
            String date = cursor.getString(dateId);

            NotesModel notesModel = new NotesModel();
            notesModel.setRowId(rowId);
            notesModel.setSubject(subject);
            notesModel.setDescription(desc);
            notesModel.setDate(date);

            notesModelArrayList.add(notesModel);
        }
        return notesModelArrayList;
    }

    public void requestDataBaseClose() {
        dbManager.close();
    }
}
