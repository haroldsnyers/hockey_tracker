package ck.edu.com.hockey_tracker.Data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class MatchModel implements Serializable {
//    int homecrest;
//    int awaycrest;
    int ID;
    String homeTeam;
    String awayTeam;
    int scorehometeam;
    int scoreawayteam;
    String date;

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

            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }
}
