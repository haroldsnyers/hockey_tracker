package ck.edu.com.hockey_tracker.Data;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class DownloadModel extends AsyncTask<Void, Void, String> {
    Context context;
    private String mode;
    private int idMatch;
    private MatchModel matchModel;
    private QuarterModel matchTtotals;
    private QuarterModel quarterModelQ1;
    private QuarterModel quarterModelQ2;
    private QuarterModel quarterModelQ3;
    private QuarterModel quarterModelQ4;

    public DownloadModel(Context context, String mode, MatchModel matchModel, QuarterModel matchTotals, QuarterModel quarterModel1,
                         QuarterModel quarterModel2, QuarterModel quarterModel3, QuarterModel quarterModel4) {
        this.context = context;
        this.mode = mode;
        this.matchModel = matchModel;

        this.matchTtotals = matchTotals;
        this.quarterModelQ1 = quarterModel1;
        this.quarterModelQ2 = quarterModel2;
        this.quarterModelQ3 = quarterModel3;
        this.quarterModelQ4 = quarterModel4;
    }

    public DownloadModel(Context context, String mode) {
        this.context = context;
        this.mode = mode;
    }

    public DownloadModel(Context context, String mode, int idMatch) {
        this.context = context;
        this.mode = mode;
        this.idMatch = idMatch;
    }

    @Override
    protected String doInBackground(Void... params) {
        String matchListLoader;
        String answer = "failed";
        String IP_COMPUTER = "192.168.1.3";
        String IP_Mobile = "10.0.2.2";
        try (Socket socket = new Socket(IP_COMPUTER, 9876)){
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            PrintWriter pw = new PrintWriter(dos);
            switch (this.mode) {
                case "INSERT":
                    String match = matchModel.toJSONNew();
                    ObjectMapper objectMapper = new ObjectMapper();
                    String matchT = objectMapper.writeValueAsString(matchTtotals);
                    String quarter1 = objectMapper.writeValueAsString(quarterModelQ1);
                    String quarter2 = objectMapper.writeValueAsString(quarterModelQ2);
                    String quarter3 = objectMapper.writeValueAsString(quarterModelQ3);
                    String quarter4 = objectMapper.writeValueAsString(quarterModelQ4);
                    pw.println(match);
                    pw.println(matchT);
                    pw.println(quarter1);
                    pw.println(quarter2);
                    pw.println(quarter3);
                    pw.println(quarter4);
                    pw.flush();
                    try {
                        answer = dis.readUTF();
                        Log.d("ASNWER", answer);
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        // We were cancelled; stop sleeping!
                    }

                    break;
                case "GETALL": {
                    String messageGet = "";
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("mode", "getAll");
                        messageGet = jsonObject.toString();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return "";
                    }

                    pw.println(messageGet);
                    pw.flush();

                    BufferedReader in = new BufferedReader(new InputStreamReader(dis));
                    String jsonMatchArray = in.readLine();
                    matchListLoader = jsonMatchArray;
                    answer = matchListLoader;
                    Log.d("JSONMATCH", jsonMatchArray);
                    break;
                }
                case "GETMATCH": {
                    String messageGet = "";
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("mode", "getMatch");
                        jsonObject.put("idMatch", this.idMatch);
                        messageGet = jsonObject.toString();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return "";
                    }

                    pw.println(messageGet);
                    pw.flush();

                    BufferedReader in = new BufferedReader(new InputStreamReader(dis));
                    String jsonMatchArray = in.readLine();
                    matchListLoader = jsonMatchArray;
                    answer = matchListLoader;
                    Log.d("JSONMATCH", jsonMatchArray);
                    break;
                }
            }
            dis.close();
            dos.close();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("ERROR", e.toString());
        }

        return answer;
    }
}
