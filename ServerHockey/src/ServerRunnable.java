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
            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

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

            Server server = new Server();

            String status;
            try {

                server.addMatch(homeTeam, awayTeam, scoreHomeTeam, scoreAwayTeam, dateMatch);

                status = "Completed";
            } catch (Exception e) {
                status = "Failed";
            }
            System.out.println("adding match");
            dos.writeUTF(status);
            System.out.println("match added");
            inputStreamReader.close();
            dos.close();
            sock.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
