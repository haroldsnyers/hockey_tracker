package ck.edu.com.hockey_tracker.Data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchModel implements Serializable {
//    int homecrest;
//    int awaycrest;
    private int id;
    private String hometeam;
    private String awayteam;
    private int scorehometeam;
    private int scoreawayteam;
    private String date;
    private String location;
    private ArrayList<String> imagePathList;

    public MatchModel(){}

    public MatchModel(String homeTeam, String awayTeam, int scorehometeam, int scoreawayteam,
                       String date, String location, ArrayList<String> imagePathList) {

        this.hometeam = homeTeam;
        this.awayteam = awayTeam;
        this.scorehometeam = scorehometeam;
        this.scoreawayteam = scoreawayteam;
        this.date = date;
        this.location = location;
        this.imagePathList = imagePathList;
    }

    public MatchModel(int id, String homeTeam, String awayTeam, int scorehometeam, int scoreawayteam,
                      String date, String location, ArrayList<String> imagePathList) {
        this.id = id;
        this.hometeam = homeTeam;
        this.awayteam = awayTeam;
        this.scorehometeam = scorehometeam;
        this.scoreawayteam = scoreawayteam;
        this.date = date;
        this.location = location;
        this.imagePathList = imagePathList;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
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
        return hometeam;
    }

    public void setHometeam(String hometeam) {
        this.hometeam = hometeam;
    }

    public String getAwayteam() {
        return awayteam;
    }

    public void setAwayteam(String awayteam) {
        this.awayteam = awayteam;
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
