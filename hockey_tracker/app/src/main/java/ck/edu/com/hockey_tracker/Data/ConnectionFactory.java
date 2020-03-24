package ck.edu.com.hockey_tracker.Data;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Class to connect to mySQL database
 *
 * NOT USED as jdbc driver is not working properly and generates errors that
 * haven't been solved
 *
 * alternative way has been made, socket that sends json object to a server under hibernate
 */
public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://127.0.0.1:3004/db_matches";
    public static final String USER = "Harold_admin";
    public static final String PASS = "BrqzKsrqkzL5QJ85NQ98";

    /**
     * Get a connection to database
     * @return Connection object
     */
    public static Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL,USER,PASS);
        }
        catch(Exception e)
        {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public interface UserDao {
        MatchModel getMatch();
        Set<MatchModel> getAllMatches();
        boolean insertMatch();
    }

    private MatchModel extractUserFromResultSet(ResultSet rs) throws SQLException {
        MatchModel matchModel = new MatchModel();
        matchModel.setID( rs.getInt("idmatches") );
        matchModel.setHometeam( rs.getString("HomeTeam") );
        matchModel.setAwayteam( rs.getString("AwayTeam") );
        matchModel.setScorehometeam( rs.getInt("ScoreTeamHome") );
        matchModel.setScorehometeam( rs.getInt("ScoreTeamAway") );
        matchModel.setDate( rs.getString("DateMatch") );
        return matchModel;
    }

    public ArrayList<MatchModel> getAllUsers() {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM matches");
            ArrayList<MatchModel> matches = new ArrayList<MatchModel>();
            while(rs.next())
            {
                MatchModel matchExtModel = extractUserFromResultSet(rs);
                matches.add(matchExtModel);
            }
            return matches;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean insertUser(MatchModel match) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO matches VALUES (NULL, ?, ?, ?)");
            ps.setString(1, match.getHometeam());
            ps.setString(2, match.getAwayteam());
            ps.setInt(3, match.getScorehometeam());
            ps.setInt(4, match.getScoreawayteam());
            ps.setString(5, match.getDate());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Test Connection
     */
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection();
    }
}
