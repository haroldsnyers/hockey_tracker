package ck.edu.com.hockey_tracker.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class MatchModel implements Serializable {
//    int homecrest;
//    int awaycrest;
    private int ID;
    private String homeTeam;
    private String awayTeam;
    private int scorehometeam;
    private int scoreawayteam;
    private String date;
    private String location;
    private ArrayList<String> imagePathList;

    public MatchModel(){}

    public MatchModel(String homeTeam, String awayTeam, int scorehometeam, int scoreawayteam,
                      String date, String location, ArrayList<String> imagePathList) {

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.scorehometeam = scorehometeam;
        this.scoreawayteam = scoreawayteam;
        this.date = date;
        this.location = location;
        this.imagePathList = imagePathList;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    //    public int getHomecrest() {
//        return homecrest;
//    }
//
//    public void setHomecrest(int homecrest) {
//        this.homecrest = homecrest;
//    }
//
//    public int getAwaycrest() {
//        return awaycrest;
//    }
//
//    public void setAwaycrest(int awaycrest) {
//        this.awaycrest = awaycrest;
//    }

    public String getHometeam() {
        return homeTeam;
    }

    public void setHometeam(String hometeam) {
        this.homeTeam = hometeam;
    }

    public String getAwayteam() {
        return awayTeam;
    }

    public void setAwayteam(String awayteam) {
        this.awayTeam = awayteam;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScorehometeam() {
        return scorehometeam;
    }

    public void setScorehometeam(int scorehometeam) {
        this.scorehometeam = scorehometeam;
    }

    public int getScoreawayteam() {
        return scoreawayteam;
    }

    public void setScoreawayteam(int scoreawayteam) {
        this.scoreawayteam = scoreawayteam;
    }

    public String toJSONNew(){

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("mode", "insert");
            jsonObject.put("HomeTeam", getHometeam());
            jsonObject.put("AwayTeam", getAwayteam());
            jsonObject.put("ScoreTeamHome", getScorehometeam());
            jsonObject.put("ScoreTeamAway", getScoreawayteam());
            jsonObject.put("DateMatch", getDate());
            jsonObject.put("Location", getLocation());
            JSONArray jsonArray = new JSONArray(getImagePathList());
            jsonObject.put("ImagePath", jsonArray);

            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<String> getImagePathList() {
        return imagePathList;
    }

    public void setImagePathList(ArrayList<String> imagePathList) {
        this.imagePathList = imagePathList;
    }
}
