import jdk.nashorn.internal.parser.JSONParser;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
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

            String mode = match.getString("mode");
            String status = "";

            Server server = new Server();

            if (mode.equals("insert")) {
                String homeTeam = match.getString("HomeTeam");
                String awayTeam = match.getString("AwayTeam");
                int scoreHomeTeam = match.getInt("ScoreTeamHome");
                int scoreAwayTeam = match.getInt("ScoreTeamAway");
                String dateMatch = match.getString("DateMatch");

                try {
                    server.addMatch(homeTeam, awayTeam, scoreHomeTeam, scoreAwayTeam, dateMatch);
                    status = "Completed";
                    System.out.println("match added");
                } catch (Exception e) {
                    status = "Failed";
                    System.out.println("match not added");
                }
                DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                dos.writeUTF(status);
                dos.close();
            } else if (mode.equals("getAll")) {
                List<Match> matchList = server.listMatchs();

                ObjectMapper mapper = new ObjectMapper();
                String matchListJson = mapper.writeValueAsString(matchList);
                System.out.println(matchListJson);

                try (OutputStreamWriter out = new OutputStreamWriter(
                        sock.getOutputStream(), StandardCharsets.UTF_8)) {
                    out.write(matchListJson);
                    System.out.println("Success");
                } catch (Exception e){
                    System.out.println("Failed");
                    System.out.println(e.toString());
                }
            }

            inputStreamReader.close();
            sock.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
