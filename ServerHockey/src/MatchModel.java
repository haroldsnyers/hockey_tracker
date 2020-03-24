import java.io.Serializable;

public class MatchModel implements Serializable {
    public String homeTeam;

    public String awayTeam;

    public int scorehometeam;

    public int scoreawayteam;

    public String date;

    MatchModel(String homeTeam, String awayTeam, int scorehometeam, int scoreawayteam, String date)
    {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.scorehometeam = scorehometeam;
        this.scoreawayteam = scoreawayteam;
        this.date = date;
    }
    void showDetails()
    {
        System.out.println("HomeTeam:"+homeTeam);
        System.out.println("AwayTeam:"+awayTeam);
        System.out.println("ScoreHomeTeam:"+scorehometeam);
        System.out.println("ScoreAwayTeam:"+scoreawayteam);
        System.out.println("Date:"+date);

    }
}
