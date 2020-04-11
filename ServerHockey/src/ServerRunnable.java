import jdk.nashorn.internal.parser.JSONParser;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.*;

public class ServerRunnable implements Runnable {

    private Socket sock;

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

            switch (mode) {
                case "insert":
                    String homeTeam = match.getString("HomeTeam");
                    String awayTeam = match.getString("AwayTeam");
                    int scoreHomeTeam = match.getInt("ScoreTeamHome");
                    int scoreAwayTeam = match.getInt("ScoreTeamAway");
                    String dateMatch = match.getString("DateMatch");
                    String location = match.getString("Location");
                    ArrayList<String> imagePath = new ArrayList<>();

                    JSONArray jArray = match.getJSONArray("ImagePath");
                    if (jArray != null) {
                        for (int i = 0; i < jArray.length(); i++) {
                            System.out.println(jArray.getString(i));
                            imagePath.add(jArray.getString(i));
                        }
                    }

                    ObjectMapper objectMapper = new ObjectMapper();
                    Quarter quarterTotals = new Quarter();
                    Quarter quarter1Object = new Quarter();
                    Quarter quarter2Object = new Quarter();
                    Quarter quarter3Object = new Quarter();
                    Quarter quarter4Object = new Quarter();

                    for (int q = 0; q < 5; q++) {
                        if (q == 0) {
                            String quarter0 = br.readLine();
                            System.out.println(quarter0);
                            quarter1Object = objectMapper.readValue(quarter0, new TypeReference<Quarter>() {
                            });
                        } else if (q == 1) {
                            String quarter1 = br.readLine();
                            quarter2Object = objectMapper.readValue(quarter1, new TypeReference<Quarter>() {
                            });
                        } else if (q == 2) {
                            String quarter2 = br.readLine();
                            quarter3Object = objectMapper.readValue(quarter2, new TypeReference<Quarter>() {
                            });
                        } else if (q == 3) {
                            String quarter3 = br.readLine();
                            quarter3Object = objectMapper.readValue(quarter3, new TypeReference<Quarter>() {
                            });
                        } else {
                            String quarter4 = br.readLine();
                            quarter4Object = objectMapper.readValue(quarter4, new TypeReference<Quarter>() {
                            });
                        }
                    }

                    try {
                        server.addMatch(homeTeam, awayTeam, scoreHomeTeam, scoreAwayTeam, dateMatch, location, imagePath, quarterTotals, quarter1Object, quarter2Object, quarter3Object, quarter4Object);
                        status = "Completed";
                        System.out.println("match added");
                    } catch (Exception e) {
                        status = "Failed";
                        System.out.println("match not added");
                    }
                    DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                    dos.writeUTF(status);
                    dos.close();
                    break;
                case "getAll": {
                    List<Match> matchList = server.listMatchs();

                    sendMatch(matchList);
                    break;
                }
                case "getMatch": {
                    int idMatch = match.getInt("idMatch");
                    List<Quarter> quarterList = server.getMatchQuery(idMatch);
                    sendMatch(quarterList);
                    break;

                }
            }

            inputStreamReader.close();
            sock.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendMatch(List list) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String matchListJson = mapper.writeValueAsString(list);
        System.out.println(matchListJson);

        try (OutputStreamWriter out = new OutputStreamWriter(
                sock.getOutputStream(), StandardCharsets.UTF_8)) {
            out.write(matchListJson);
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Failed");
            System.out.println(e.toString());
        }
    }
}
