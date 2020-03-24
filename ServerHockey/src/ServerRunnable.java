import jdk.nashorn.internal.parser.JSONParser;

import java.io.*;
import java.net.Socket;
import org.json.*;

public class ServerRunnable implements Runnable {

    private Socket sock;
    private Server server;

    public ServerRunnable(Socket s) {
        sock = s;
    }


    @Override
    public void run() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(sock.getInputStream());
            BufferedReader br = new BufferedReader(inputStreamReader);
            String content = br.readLine();
            System.out.println(content);

            JSONObject match = new JSONObject(content);
            System.out.println(match.toString());
            String homeTeam = match.getString("HomeTeam");
            String awayTeam = match.getString("AwayTeam");
            int scoreHomeTeam = match.getInt("ScoreTeamHome");
            int scoreAwayTeam = match.getInt("ScoreTeamAway");
            String dateMatch = match.getString("DateMatch");
            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

            Server server = new Server();

            String status;
            try {
                System.out.println("adding match");
                server.addMatch(homeTeam, awayTeam, scoreHomeTeam, scoreAwayTeam, dateMatch);
                System.out.println("match added");
                status = "Complete";
            } catch (Exception e) {
                status = "failed";
            }

            dos.writeUTF(status);

            inputStreamReader.close();
            dos.close();
            sock.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
