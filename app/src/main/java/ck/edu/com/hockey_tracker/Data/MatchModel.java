package ck.edu.com.hockey_tracker.Data;

public class MatchModel {
//    int homecrest;
//    int awaycrest;
    String ID;
    String hometeam;
    String awayteam;
    int scorehometeam;
    int scoreawayteam;
    String date;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
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
}
