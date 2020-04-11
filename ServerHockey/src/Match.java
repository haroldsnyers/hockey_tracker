import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "matches")
public class Match implements Serializable {
    @Id @GeneratedValue
    @Column(name="idmatches")
    private int id;

    @Column(name="HomeTeam")
    private String homeTeam;

    @Column(name="AwayTeam")
    private String awayTeam;

    @Column(name="ScoreTeamHome")
    private int scorehometeam;

    @Column(name="ScoreTeamAway")
    private int scoreawayteam;

    @Column(name="DateMatch")
    private String date;

    @Column(name="Location")
    private String location;

    @Column(name="imagePath")
    private ArrayList<String> imagePathList;

    @OneToMany(mappedBy="match", cascade = CascadeType.ALL)
    private Set<Quarter> subQuarter;

    public Match() {
    }

    public Match(String homeTeam, String awayTeam, int scoreTeamHome, int scoreTeamAway, String date, String location, ArrayList<String> imagePathList) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.scorehometeam = scoreTeamHome;
        this.scoreawayteam = scoreTeamAway;
        this.date = date;
        this.location = location;
        this.imagePathList = imagePathList;
    }

    public Match(int id, String homeTeam, String awayTeam, int scoreTeamHome, int scoreTeamAway, ArrayList<String> imagePathList, String date, String location) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.scorehometeam = scoreTeamHome;
        this.scoreawayteam = scoreTeamAway;
        this.date = date;
        this.location = location;
        this.imagePathList = imagePathList;
    }

    public int getId() {
        return id;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setScorehometeam(int scorehometeam) {
        this.scorehometeam = scorehometeam;
    }

    public int getScorehometeam() {
        return scorehometeam;
    }

    public void setScoreawayteam(int scoreawayteam) {
        this.scoreawayteam = scoreawayteam;
    }

    public int getScoreawayteam() {
        return scoreawayteam;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setSubQuarter(Set subQuarter) {
        this.subQuarter = subQuarter;
    }

    @JsonIgnore
    public Set getSubQuarter() {
        return subQuarter;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<String> getImagePathList() {
        return imagePathList;
    }

    public void setImagePathList(ArrayList<String> imagePathList) {
        this.imagePathList = imagePathList;
    }
}
