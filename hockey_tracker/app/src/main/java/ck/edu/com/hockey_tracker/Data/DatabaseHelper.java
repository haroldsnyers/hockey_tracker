package ck.edu.com.hockey_tracker.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ck.edu.com.hockey_tracker.MainActivity;

public class DatabaseHelper extends SQLiteOpenHelper {

    //database name
    public static final String DATABASE_NAME = "hockey.db";
    //database version
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "matches";
    public static final String TABLE_NAME1 = "quarters";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        //creating table
        query = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, HomeTeam TEXT, AwayTeam TEXT, " +
                "HomeTeam_score INTEGER, AwayTeam_score INTEGER, Date_match TEXT, Location TEXT, ImagePath TEXT)";
        db.execSQL(query);
        query = "CREATE TABLE " + TABLE_NAME1 +
                "(ID INTEGER PRIMARY KEY, Quarter INTEGER, GoalsHome INTEGER, GoalsAway INTEGER, ShotsHome INTEGER, " +
                "ShotsHomeMissed INTEGER, ShotsHomeMissedKeeper INTEGER, ShotsAway INTEGER, ShotsAwayMissed INTEGER, " +
                "ShotsAwayMissedKeeper INTEGER, HomeGreenCards INTEGER, HomeYellowCards INTEGER, HomeRedCards INTEGER, " +
                "AwayGreenCards INTEGER, AwayYellowCards INTEGER, AwayRedCards INTEGER, StrokeConvertedHome INTEGER, " +
                "StrokeNotConvertedHome INTEGER, StrokeConvertedAway INTEGER, StrokeNotConvertedAway INTEGER, " +
                "FaultHomeKick INTEGER, FaultHomeBackstick INTEGER, FaultHomeStick INTEGER, FaultHomeUndercutting INTEGER, " +
                "FaultHomeObstruction INTEGER, FaultAwayKick INTEGER, FaultAwayBackstick INTEGER, FaultAwayStick INTEGER, " +
                "FaultAwayUndercutting INTEGER, FaultAwayObstruction INTEGER, pcConvertedHome INTEGER, " +
                "pcNotConvertedHome INTEGER, pcConvertedAway INTEGER, pcNotConvertedAway INTEGER,  " +
                "FaultPosition25Home INTEGER, FaultPosition50Home INTEGER, FaultPosition75Home INTEGER, " +
                "FaultPosition100Home INTEGER, FaultPosition25Away INTEGER, FaultPosition50Away INTEGER, " +
                "FaultPosition75Away INTEGER, FaultPosition100Away INTEGER, outsideHomeSide INTEGER, " +
                "outsideHomeClearance INTEGER, outsideHomeCorner INTEGER, outsideAwaySide INTEGER, " +
                "outsideAwayClearance INTEGER, outsideAwayCorner INTEGER, id_match_fk INTEGER)";
        db.execSQL(query);
    }

    //upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
    }

    //add the new note
    public long addmatch(String hometeam, String awayteam, int scoreteamhome, int scoreteamaway,
                         String date, String location, String imagePathList) {
        SQLiteDatabase sqLiteDatabase = this .getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("HomeTeam", hometeam);
        values.put("AwayTeam", awayteam);
        values.put("HomeTeam_score", scoreteamhome);
        values.put("AwayTeam_score", scoreteamaway);

        values.put("Date_match", date);
        values.put("Location", location);
        values.put("ImagePath", imagePathList);

        //inserting new row
        long lastrow = sqLiteDatabase.insert(TABLE_NAME, null , values);
        //close database connection
        sqLiteDatabase.close();
        return lastrow;
    }

    //add the new note
    public void addQuarter(int quarter, int goalsHome, int goalsAway, int shotsHome, int shotsHomeMissed,
                           int shotsHomeMissedKeeper, int shotsAway, int shotsAwayMissed, int shotsAwayMissedKeeper,
                           int homeGreenCards, int homeYellowCards, int homeRedCards, int awayGreenCards,
                           int awayYellowCards, int awayRedCards, int strokeConvertedHome, int strokeNotConvertedHome,
                           int strokeConvertedAway, int strokeNotConvertedAway, int faultHomeBackstick, int faultHomeKick,
                           int faultHomeUndercutting, int faultHomeStick, int faultHomeObstruction, int faultAwayBackstick,
                           int faultAwayKick, int faultAwayUndercutting, int faultAwayStick, int faultAwayObstruction,
                           int pcConvertedHome, int pcNotConvertedHome, int pcConvertedAway, int pcNotConvertedAway,
                           int faultPosition25Home, int faultPosition50Home, int faultPosition75Home, int faultPosition100Home,
                           int faultPosition25Away, int faultPosition50Away, int faultPosition75Away, int faultPosition100Away,
                           int outsideHomeSide, int outsideHomeClearance, int outsideHomeCorner, int outsideAwaySide,
                           int outsideAwayClearance, int outsideAwayCorner, long idMatch) {
        SQLiteDatabase sqLiteDatabase = this .getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Quarter", quarter);
        values.put("GoalsHome", goalsHome);
        values.put("GoalsAway", goalsAway);
        values.put("ShotsHome", shotsHome);
        values.put("ShotsHomeMissed", shotsHomeMissed);
        values.put("ShotsHomeMissedKeeper", shotsHomeMissedKeeper);
        values.put("ShotsAway", shotsAway);
        values.put("ShotsAwayMissed", shotsAwayMissed);
        values.put("ShotsAwayMissedKeeper", shotsAwayMissedKeeper);
        values.put("HomeGreenCards", homeGreenCards);
        values.put("HomeYellowCards", homeYellowCards);
        values.put("HomeRedCards", homeRedCards);
        values.put("AwayGreenCards", awayGreenCards);
        values.put("AwayYellowCards", awayYellowCards);
        values.put("AwayRedCards", awayRedCards);
        values.put("StrokeConvertedHome", strokeConvertedHome);
        values.put("StrokeNotConvertedHome", strokeNotConvertedHome);
        values.put("StrokeConvertedAway", strokeConvertedAway);
        values.put("StrokeNotConvertedAway", strokeNotConvertedAway);
        values.put("FaultHomeKick", faultHomeKick);
        values.put("FaultHomeBackstick", faultHomeBackstick);
        values.put("FaultHomeStick", faultHomeStick);
        values.put("FaultHomeUndercutting", faultHomeUndercutting);
        values.put("FaultHomeObstruction", faultHomeObstruction);
        values.put("FaultAwayKick", faultAwayKick);
        values.put("FaultAwayBackstick", faultAwayBackstick);
        values.put("FaultAwayStick", faultAwayStick);
        values.put("FaultAwayUndercutting", faultAwayUndercutting);
        values.put("FaultAwayObstruction", faultAwayObstruction);
        values.put("pcConvertedHome", pcConvertedHome);
        values.put("pcNotConvertedHome", pcNotConvertedHome);
        values.put("pcConvertedAway", pcConvertedAway);
        values.put("pcNotConvertedAway", pcNotConvertedAway);
        values.put("FaultPosition25Home", faultPosition25Home);
        values.put("FaultPosition50Home", faultPosition50Home);
        values.put("FaultPosition75Home", faultPosition75Home);
        values.put("FaultPosition100Home", faultPosition100Home);
        values.put("FaultPosition25Away", faultPosition25Away);
        values.put("FaultPosition50Away", faultPosition50Away);
        values.put("FaultPosition75Away", faultPosition75Away);
        values.put("FaultPosition100Away", faultPosition100Away);
        values.put("outsideHomeSide", outsideHomeSide);
        values.put("outsideHomeClearance", outsideHomeClearance);
        values.put("outsideHomeCorner", outsideHomeCorner);
        values.put("outsideAwaySide", outsideAwaySide);
        values.put("outsideAwayClearance", outsideAwayClearance);
        values.put("outsideAwayCorner", outsideAwayCorner);
        values.put("id_match_fk", idMatch);

        sqLiteDatabase.insert(TABLE_NAME1, null , values);
        sqLiteDatabase.close();
    }

    //get the all notes
    public ArrayList<MatchModel> getMatches() {
        ArrayList<MatchModel> arrayList;

        // select all query
        String select_query= "SELECT *FROM " + TABLE_NAME;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        arrayList = select(cursor);

        if (arrayList.size() > 5) {
            long id = arrayList.get(0).getID();
            delete(id, db);
            deleteQuarters(id, db);
            Cursor cursor1 = db.rawQuery(select_query, null);
            arrayList = select(cursor1);
        }
        return arrayList;
    }

    public ArrayList<MatchModel> select(Cursor cursor) {
        ArrayList<MatchModel> arrayList = new ArrayList<>();
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
                String value = cursor.getString(7); //assign this via Reader
                ArrayList<String> imagePath = Stream.of(value.split(","))
                        .collect(Collectors.toCollection(ArrayList<String>::new));
                matchModel.setImagePathList(imagePath);
                arrayList.add(matchModel);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    //get the all notes
    public ArrayList<QuarterModel> getQuarters(long idMatch) {
        ArrayList<QuarterModel> arrayList = new ArrayList<>();

        // select all query
        String select_query= "SELECT *FROM " + TABLE_NAME1 + " WHERE id_match_fk = " + idMatch;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                QuarterModel quarterModel = new QuarterModel();
                quarterModel.setID(cursor.getInt(0));
                quarterModel.setQuarterNumber(cursor.getInt(1));
                quarterModel.setGoalsHome(cursor.getInt(2));
                quarterModel.setGoalsAway(cursor.getInt(3));

                quarterModel.setShotsHome(cursor.getInt(4));
                quarterModel.setShotsHomeMissed(cursor.getInt(5));
                quarterModel.setShotsHomeMissedKeeper(cursor.getInt(6));
                quarterModel.setShotsAway(cursor.getInt(7));
                quarterModel.setShotsAwayMissed(cursor.getInt(8));
                quarterModel.setShotsAwayMissedKeeper(cursor.getInt(9));

                quarterModel.setHomeGreenCards(cursor.getInt(10));
                quarterModel.setHomeYellowCards(cursor.getInt(11));
                quarterModel.setHomeRedCards(cursor.getInt(12));
                quarterModel.setAwayGreenCards(cursor.getInt(13));
                quarterModel.setAwayYellowCards(cursor.getInt(14));
                quarterModel.setAwayRedCards(cursor.getInt(15));

                quarterModel.setStrokeConvertedHome(cursor.getInt(16));
                quarterModel.setStrokeNotConvertedHome(cursor.getInt(17));
                quarterModel.setStrokeConvertedAway(cursor.getInt(18));
                quarterModel.setStrokeNotConvertedAway(cursor.getInt(19));

                quarterModel.setFaultHomeKick(cursor.getInt(20));
                quarterModel.setFaultHomeBackstick(cursor.getInt(21));
                quarterModel.setFaultHomeStick(cursor.getInt(22));
                quarterModel.setFaultHomeUndercutting(cursor.getInt(23));
                quarterModel.setFaultHomeObstruction(cursor.getInt(24));
                quarterModel.setFaultAwayKick(cursor.getInt(20));
                quarterModel.setFaultAwayBackstick(cursor.getInt(21));
                quarterModel.setFaultAwayStick(cursor.getInt(22));
                quarterModel.setFaultAwayUndercutting(cursor.getInt(23));
                quarterModel.setFaultAwayObstruction(cursor.getInt(24));

                quarterModel.setPcConvertedHome(cursor.getInt(25));
                quarterModel.setPcNotConvertedHome(cursor.getInt(26));
                quarterModel.setPcConvertedAway(cursor.getInt(27));
                quarterModel.setPcNotConvertedAway(cursor.getInt(28));

                quarterModel.setFaultPosition25Home(cursor.getInt(29));
                quarterModel.setFaultPosition50Home(cursor.getInt(30));
                quarterModel.setFaultPosition75Home(cursor.getInt(31));
                quarterModel.setFaultPosition100Home(cursor.getInt(32));
                quarterModel.setFaultPosition25Away(cursor.getInt(33));
                quarterModel.setFaultPosition50Away(cursor.getInt(34));
                quarterModel.setFaultPosition75Away(cursor.getInt(35));
                quarterModel.setFaultPosition100Away(cursor.getInt(36));

                quarterModel.setOutsideHomeSide(cursor.getInt(37));
                quarterModel.setOutsideHomeClearance(cursor.getInt(38));
                quarterModel.setOutsideHomeCorner(cursor.getInt(39));
                quarterModel.setOutsideAwaySide(cursor.getInt(40));
                quarterModel.setOutsideAwayClearance(cursor.getInt(41));
                quarterModel.setOutsideAwayCorner(cursor.getInt(42));
                quarterModel.setID_MATCH(cursor.getInt(43));

                arrayList.add(quarterModel);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    //delete the note
    public void delete(long ID, SQLiteDatabase db) {
        boolean ok = db.delete(TABLE_NAME, "ID=" + ID, null) > 0;
    }

    public void deleteQuarters(long IDMatch, SQLiteDatabase db) {
        boolean ok = db.delete(TABLE_NAME1, "id_match_fk=" + IDMatch, null) >0;
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
