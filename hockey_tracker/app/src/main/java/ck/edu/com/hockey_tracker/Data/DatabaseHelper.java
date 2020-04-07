package ck.edu.com.hockey_tracker.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import ck.edu.com.hockey_tracker.MainActivity;

public class DatabaseHelper extends SQLiteOpenHelper {

    //database name
    public static final String DATABASE_NAME = "hockey.db";
    //database version
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "matches";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        //creating table
        query = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, HomeTeam TEXT, AwayTeam TEXT, " +
                "HomeTeam_score INTEGER, AwayTeam_score INTEGER, Date_match TEXT, Location TEXT)";
        db.execSQL(query);
    }

    //upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //add the new note
    public long addmatch(String hometeam, String awayteam, int scoreteamhome, int scoreteamaway,
                         String date, String location) {
        SQLiteDatabase sqLiteDatabase = this .getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("HomeTeam", hometeam);
        values.put("AwayTeam", awayteam);
        values.put("HomeTeam_score", scoreteamhome);
        values.put("AwayTeam_score", scoreteamaway);

        values.put("Date_match", date);
        values.put("Location", location);

        //inserting new row
        long lastrow = sqLiteDatabase.insert(TABLE_NAME, null , values);
        deleteLatest();
        //close database connection
        sqLiteDatabase.close();
        return lastrow;
    }

    //get the all notes
    public ArrayList<MatchModel> getMatches() {
        ArrayList<MatchModel> arrayList = new ArrayList<>();

        // select all query
        String select_query= "SELECT *FROM " + TABLE_NAME;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MatchModel matchModel = new MatchModel();
                matchModel.setID(cursor.getInt(0));
                matchModel.setHometeam(cursor.getString(1));
                matchModel.setAwayteam(cursor.getString(2));
                matchModel.setScorehometeam(Integer.parseInt(cursor.getString(3)));
                matchModel.setScoreawayteam(Integer.parseInt(cursor.getString(4)));
                matchModel.setDate(cursor.getString(5));
                matchModel.setLocation(cursor.getString(6));
                arrayList.add(matchModel);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    public void deleteLatest() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, "id not in (select id from " + TABLE_NAME  + " order by Date_match desc limit 10)", null);
    }

    //delete the note
    public void delete(String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //deleting row
        sqLiteDatabase.delete(TABLE_NAME, "ID=" + ID, null);
        sqLiteDatabase.close();
    }

    //update the note
//    public void updateNote(String title, String des, String ID) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues values =  new ContentValues();
//        values.put("Title", title);
//        values.put("Description", des);
//        //updating row
//        sqLiteDatabase.update(TABLE_NAME, values, "ID=" + ID, null);
//        sqLiteDatabase.close();
//    }
}
