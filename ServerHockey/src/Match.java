import javax.persistence.*;
import java.io.Serializable;

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
}
