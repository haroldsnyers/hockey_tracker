package ck.edu.com.hockey_tracker;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ck.edu.com.hockey_tracker.Data.DatabaseHelper;
import ck.edu.com.hockey_tracker.Data.DownloadModel;
import ck.edu.com.hockey_tracker.Data.MatchModel;
import ck.edu.com.hockey_tracker.Data.QuarterModel;

public class RecordActivity extends AppCompatActivity {

    private int CURRENT_QUARTER = 1;
    private TextView currentQuarterTextView;

    private Context mContext;

    private LinearLayout linearLayout;

    private PopupWindow mPopupWindow;

    private int totalHome;
    private int totalAway;

    private int[] goalsHome = {0, 0, 0, 0};
    private int[] goalsAway = {0, 0, 0, 0};

    private int[] shotsHome = {0, 0, 0, 0};
    private int[] shotsHomeMissed = {0, 0, 0, 0};
    private int[] shotsHomeMissedKeeper = {0, 0, 0, 0};
    private int[] shotsAway = {0, 0, 0, 0};
    private int[] shotsAwayMissed = {0, 0, 0, 0};
    private int[] shotsAwayMissedKeeper = {0, 0, 0, 0};

    private int[] homeGreenCards = {0, 0, 0, 0};
    private int[] homeYellowCards = {0, 0, 0, 0};
    private int[] homeRedCards = {0, 0, 0, 0};
    private int[] awayGreenCards = {0, 0, 0, 0};
    private int[] awayYellowCards = {0, 0, 0, 0};
    private int[] awayRedCards = {0, 0, 0, 0};

    private int[] strokeConvertedHome = {0, 0, 0, 0};
    private int[] strokeNotConvertedHome = {0, 0, 0, 0};
    private int[] strokeConvertedAway = {0, 0, 0, 0};
    private int[] strokeNotConvertedAway = {0, 0, 0, 0};

    private int[] faultHomeBackstick = {0, 0, 0, 0};
    private int[] faultHomeKick = {0, 0, 0, 0};
    private int[] faultHomeUndercutting = {0, 0, 0, 0};
    private int[] faultHomeStick = {0, 0, 0, 0};
    private int[] faultHomeObstruction = {0, 0, 0, 0};
    private int[] faultAwayBackstick = {0, 0, 0, 0};
    private int[] faultAwayKick = {0, 0, 0, 0};
    private int[] faultAwayUndercutting = {0, 0, 0, 0};
    private int[] faultAwayStick = {0, 0, 0, 0};
    private int[] faultAwayObstruction = {0, 0, 0, 0};

    private int[] pcConvertedHome = {0, 0, 0, 0};
    private int[] pcNotConvertedHome = {0, 0, 0, 0};
    private int[] pcConvertedAway = {0, 0, 0, 0};
    private int[] pcNotConvertedAway = {0, 0, 0, 0};

    private int[] faultPosition25Home = {0, 0, 0, 0};
    private int[] faultPosition50Home = {0, 0, 0, 0};
    private int[] faultPosition75Home = {0, 0, 0, 0};
    private int[] faultPosition100Home = {0, 0, 0, 0};
    private int[] faultPosition25Away = {0, 0, 0, 0};
    private int[] faultPosition50Away = {0, 0, 0, 0};
    private int[] faultPosition75Away = {0, 0, 0, 0};
    private int[] faultPosition100Away = {0, 0, 0, 0};

    private int[] outsideHomeSide = {0, 0, 0, 0};
    private int[] outsideHomeClearance = {0, 0, 0, 0};
    private int[] outsideHomeCorner = {0, 0, 0, 0};
    private int[] outsideAwaySide = {0, 0, 0, 0};
    private int[] outsideAwayClearance = {0, 0, 0, 0};
    private int[] outsideAwayCorner = {0, 0, 0, 0};

    private TextView homeTeam;
    private TextView awayTeam;

    private TextView homeTotal;
    private TextView homeQ1;
    private TextView homeQ2;
    private TextView homeQ3;
    private TextView homeQ4;

    private TextView awayTotal;
    private TextView awayQ1;
    private TextView awayQ2;
    private TextView awayQ3;
    private TextView awayQ4;

    private String date;
    private String location;

    DatabaseHelper databaseHelper;

    private static final String TAG = "RecordActivityCamera";

    private static final int IMAGE_CAPTURE_CODE = 1001;

    ArrayList<String> imagePathList = new ArrayList<>();

    String FILE_AUTHORITY_PROVIDER = "com.example.android.fileprovider";

    Uri image_uri;

    public static final int REQUEST_CODE = 1000;

    public static final int IMG_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
        setSupportActionBar(toolbar);

        mContext = getApplicationContext();

        currentQuarterTextView = findViewById(R.id.current_quarter);
        linearLayout = findViewById(R.id.main_area);

        homeTeam = findViewById(R.id.team_home_name);
        awayTeam = findViewById(R.id.team_away_name);
        homeTotal = findViewById(R.id.home_total);
        homeQ1 = findViewById(R.id.home_Q1);
        homeQ2 = findViewById(R.id.home_Q2);
        homeQ3 = findViewById(R.id.home_Q3);
        homeQ4 = findViewById(R.id.home_Q4);
        awayTotal = findViewById(R.id.away_total);
        awayQ1 = findViewById(R.id.away_Q1);
        awayQ2 = findViewById(R.id.away_Q2);
        awayQ3 = findViewById(R.id.away_Q3);
        awayQ4 = findViewById(R.id.away_Q4);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper = new DatabaseHelper(getApplicationContext());

        Intent intentThatStartedThisActivity = getIntent();

        // we shouldn't assume that every intent has a data in it thus getting the extra
        // with an if statement
        // checks if intent has an extra data called EXTRA_TEXT
        if (intentThatStartedThisActivity.hasExtra("HOME")) {
            String queryEntered = intentThatStartedThisActivity.getStringExtra("HOME");
            homeTeam.setText(queryEntered);
        }
        if (intentThatStartedThisActivity.hasExtra("AWAY")) {
            String queryEntered = intentThatStartedThisActivity.getStringExtra("AWAY");
            awayTeam.setText(queryEntered);
        }
        if (intentThatStartedThisActivity.hasExtra("DATE")) {
            String queryEntered = intentThatStartedThisActivity.getStringExtra("DATE");
            date = queryEntered;
        }
        if (intentThatStartedThisActivity.hasExtra("LOCATION")) {
            String queryEntered = intentThatStartedThisActivity.getStringExtra("LOCATION");
            location = queryEntered;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItemSave = menu.findItem(R.id.action_save);
        MenuItem menuItemCamera = menu.findItem(R.id.action_picture);
        MenuItem menuItemSettings = menu.findItem(R.id.action_settings);
        menuItemSave.setVisible(true);
        menuItemCamera.setVisible(true);
        menuItemSettings.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();

//        if (id == R.id.home) {
//            this.finish();
//        } else
        if (id == R.id.action_save){
            safeMatch();
            return true;
        } else if (id == R.id.action_picture) {
            Log.d("CAMERA", "start");
            captureImage();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void safeMatch() {
        String imagePathString = "None";

        if (imagePathList.size() > 0) {
            imagePathString = imagePathList.get(0);
            for(int i = 1; i < imagePathList.size(); i++) {
                imagePathString += "," + imagePathList.get(i);

            }
        }
        Log.d("IMAGELIST", imagePathString);

        long id_match = databaseHelper.addmatch(
                homeTeam.getText().toString(),
                awayTeam.getText().toString(),
                totalHome,
                totalAway,
                date,
                location, 
                imagePathString);
        
        MatchModel matchModel = new MatchModel(homeTeam.getText().toString(), awayTeam.getText().toString(),
                totalHome, totalAway, date, location, imagePathList );
        QuarterModel matchTotals = new QuarterModel();
        matchTotals = getMatchTotals(id_match);
        QuarterModel quarterModel1 = new QuarterModel();
        QuarterModel quarterModel2 = new QuarterModel();
        QuarterModel quarterModel3 = new QuarterModel();
        QuarterModel quarterModel4 = new QuarterModel();
        for (int q = 0; q < 4; q++) {
            databaseHelper.addQuarter(q+1, goalsHome[q], goalsAway[q], shotsHome[q], shotsHomeMissed[q],
                shotsHomeMissedKeeper[q], shotsAway[q], shotsAwayMissed[q], shotsAwayMissedKeeper[q],
                homeGreenCards[q], homeYellowCards[q], homeRedCards[q], awayGreenCards[q],
                awayYellowCards[q], awayRedCards[q], strokeConvertedHome[q], strokeNotConvertedHome[q],
                strokeConvertedAway[q], strokeNotConvertedAway[q], faultHomeBackstick[q], faultHomeKick[q],
                faultHomeUndercutting[q], faultHomeStick[q], faultHomeObstruction[q], faultAwayBackstick[q],
                faultAwayKick[q], faultAwayUndercutting[q], faultAwayStick[q], faultAwayObstruction[q],
                pcConvertedHome[q], pcNotConvertedHome[q], pcConvertedAway[q], pcNotConvertedAway[q],
                faultPosition25Home[q], faultPosition50Home[q], faultPosition75Home[q], faultPosition100Home[q],
                faultPosition25Away[q], faultPosition50Away[q], faultPosition75Away[q], faultPosition100Away[q],
                outsideHomeSide[q], outsideHomeClearance[q], outsideHomeCorner[q], outsideAwaySide[q],
                outsideAwayClearance[q], outsideAwayCorner[q], id_match);

            QuarterModel quarterModel = new QuarterModel(q+1, goalsHome[q], goalsAway[q], shotsHome[q], shotsHomeMissed[q],
                    shotsHomeMissedKeeper[q], shotsAway[q], shotsAwayMissed[q], shotsAwayMissedKeeper[q],
                    homeGreenCards[q], homeYellowCards[q], homeRedCards[q], awayGreenCards[q],
                    awayYellowCards[q], awayRedCards[q], strokeConvertedHome[q], strokeNotConvertedHome[q],
                    strokeConvertedAway[q], strokeNotConvertedAway[q], faultHomeBackstick[q], faultHomeKick[q],
                    faultHomeUndercutting[q], faultHomeStick[q], faultHomeObstruction[q], faultAwayBackstick[q],
                    faultAwayKick[q], faultAwayUndercutting[q], faultAwayStick[q], faultAwayObstruction[q],
                    pcConvertedHome[q], pcNotConvertedHome[q], pcConvertedAway[q], pcNotConvertedAway[q],
                    faultPosition25Home[q], faultPosition50Home[q], faultPosition75Home[q], faultPosition100Home[q],
                    faultPosition25Away[q], faultPosition50Away[q], faultPosition75Away[q], faultPosition100Away[q],
                    outsideHomeSide[q], outsideHomeClearance[q], outsideHomeCorner[q], outsideAwaySide[q],
                    outsideAwayClearance[q], outsideAwayCorner[q]);


            if (q == 0) {
                quarterModel1 = quarterModel;
            } else if (q == 1) {
                quarterModel2 = quarterModel;
            } else if (q == 2) {
                quarterModel3 = quarterModel;
            } else {
                quarterModel4 = quarterModel;
            }

        }
        Log.d("INTERNAL", "internal done");



        new Download(RecordActivity.this, "INSERT", matchModel, matchTotals, quarterModel1, quarterModel2,
                quarterModel3, quarterModel4).execute();
        Log.d("EXTERNAL", "external begin");

    }
    
    public QuarterModel getMatchTotals(long idMatch) {
        int goalsHomeT=0, goalsAwayT=0, shotsHomeT=0, shotsHomeMissedT=0, shotsHomeMissedKeeperT=0, shotsAwayT=0, 
                shotsAwayMissedT=0, shotsAwayMissedKeeperT=0, homeGreenCardsT=0, homeYellowCardsT=0, homeRedCardsT=0, 
                awayGreenCardsT=0, awayYellowCardsT=0, awayRedCardsT=0, strokeConvertedHomeT=0, strokeNotConvertedHomeT=0, 
                strokeConvertedAwayT=0, strokeNotConvertedAwayT=0, faultHomeBackstickT=0, faultHomeKickT=0, faultHomeUndercuttingT=0, 
                faultHomeStickT=0, faultHomeObstructionT=0, faultAwayBackstickT=0, faultAwayKickT=0, faultAwayUndercuttingT=0, 
                faultAwayStickT=0, faultAwayObstructionT=0, pcConvertedHomeT=0, pcNotConvertedHomeT=0, pcConvertedAwayT=0, pcNotConvertedAwayT=0, 
                faultPosition25HomeT=0, faultPosition50HomeT=0, faultPosition75HomeT=0, faultPosition100HomeT=0, faultPosition25AwayT=0, 
                faultPosition50AwayT=0, faultPosition75AwayT=0, faultPosition100AwayT=0, outsideHomeSideT=0, outsideHomeClearanceT=0, 
                outsideHomeCornerT=0, outsideAwaySideT=0, outsideAwayCornerT=0, outsideAwayClearanceT = 0;
        
        for (int q = 0; q < 4; q++) {
            goalsHomeT = goalsHome[q];
            goalsAwayT = goalsAway[q];
            shotsHomeT = shotsHome[q];
            shotsHomeMissedT = shotsHomeMissed[q];
            shotsHomeMissedKeeperT = shotsHomeMissedKeeper[q];
            shotsAwayT = shotsAway[q];
            shotsAwayMissedT = shotsAwayMissed[q];
            shotsAwayMissedKeeperT = shotsAwayMissedKeeper[q];
            homeGreenCardsT = homeGreenCards[q];
            homeYellowCardsT = homeYellowCards[q];
            homeRedCardsT = homeRedCards[q];
            awayGreenCardsT = awayGreenCards[q];
            awayYellowCardsT = awayYellowCards[q];
            awayRedCardsT = awayRedCards[q];
            strokeConvertedHomeT = strokeConvertedHome[q];
            strokeNotConvertedHomeT = strokeNotConvertedHome[q];
            strokeConvertedAwayT = strokeConvertedAway[q];
            strokeNotConvertedAwayT = strokeNotConvertedAway[q];
            faultHomeBackstickT = faultHomeBackstick[q];
            faultHomeKickT = faultHomeKick[q];
            faultHomeUndercuttingT = faultHomeUndercutting[q];
            faultHomeStickT = faultHomeStick[q];
            faultHomeObstructionT = faultHomeObstruction[q];
            faultAwayBackstickT = faultAwayBackstick[q];
            faultAwayKickT = faultAwayKick[q];
            faultAwayUndercuttingT = faultAwayUndercutting[q];
            faultAwayStickT = faultAwayStick[q];
            faultAwayObstructionT = faultAwayObstruction[q];
            pcConvertedHomeT = pcConvertedHome[q];
            pcNotConvertedHomeT = pcNotConvertedHome[q];
            pcConvertedAwayT = pcConvertedAway[q];
            pcNotConvertedAwayT = pcNotConvertedAway[q];
            faultPosition25HomeT = faultPosition25Home[q];
            faultPosition50HomeT = faultPosition50Home[q];
            faultPosition75HomeT = faultPosition75Home[q];
            faultPosition100HomeT = faultPosition100Home[q];
            faultPosition25AwayT = faultPosition25Away[q];
            faultPosition50AwayT = faultPosition50Away[q];
            faultPosition75AwayT = faultPosition75Away[q];
            faultPosition100AwayT = faultPosition100Away[q];
            outsideHomeSideT = outsideHomeSide[q];
            outsideHomeClearanceT = outsideHomeClearance[q];
            outsideHomeCornerT = outsideHomeClearance[q];
            outsideAwaySideT = outsideAwaySide[q];
            outsideAwayClearanceT = outsideAwayClearance[q];
            outsideAwayCornerT = outsideAwayCorner[q];
        }

        databaseHelper.addQuarter(0, goalsHomeT, goalsAwayT, shotsHomeT, shotsHomeMissedT,
                shotsHomeMissedKeeperT, shotsAwayT, shotsAwayMissedT, shotsAwayMissedKeeperT,
                homeGreenCardsT, homeYellowCardsT, homeRedCardsT, awayGreenCardsT,
                awayYellowCardsT, awayRedCardsT, strokeConvertedHomeT, strokeNotConvertedHomeT,
                strokeConvertedAwayT, strokeNotConvertedAwayT, faultHomeBackstickT, faultHomeKickT,
                faultHomeUndercuttingT, faultHomeStickT, faultHomeObstructionT, faultAwayBackstickT,
                faultAwayKickT, faultAwayUndercuttingT, faultAwayStickT, faultAwayObstructionT,
                pcConvertedHomeT, pcNotConvertedHomeT, pcConvertedAwayT, pcNotConvertedAwayT,
                faultPosition25HomeT, faultPosition50HomeT, faultPosition75HomeT, faultPosition100HomeT,
                faultPosition25AwayT, faultPosition50AwayT, faultPosition75AwayT, faultPosition100AwayT,
                outsideHomeSideT, outsideHomeClearanceT, outsideHomeCornerT, outsideAwaySideT,
                outsideAwayClearanceT, outsideAwayCornerT, idMatch);

        QuarterModel quarterModel = new QuarterModel(0, goalsHomeT, goalsAwayT, shotsHomeT, shotsHomeMissedT,
                shotsHomeMissedKeeperT, shotsAwayT, shotsAwayMissedT, shotsAwayMissedKeeperT,
                homeGreenCardsT, homeYellowCardsT, homeRedCardsT, awayGreenCardsT,
                awayYellowCardsT, awayRedCardsT, strokeConvertedHomeT, strokeNotConvertedHomeT,
                strokeConvertedAwayT, strokeNotConvertedAwayT, faultHomeBackstickT, faultHomeKickT,
                faultHomeUndercuttingT, faultHomeStickT, faultHomeObstructionT, faultAwayBackstickT,
                faultAwayKickT, faultAwayUndercuttingT, faultAwayStickT, faultAwayObstructionT,
                pcConvertedHomeT, pcNotConvertedHomeT, pcConvertedAwayT, pcNotConvertedAwayT,
                faultPosition25HomeT, faultPosition50HomeT, faultPosition75HomeT, faultPosition100HomeT,
                faultPosition25AwayT, faultPosition50AwayT, faultPosition75AwayT, faultPosition100AwayT,
                outsideHomeSideT, outsideHomeClearanceT, outsideHomeCornerT, outsideAwaySideT,
                outsideAwayClearanceT, outsideAwayCornerT);
        
        return quarterModel;
    }

    public class Download extends DownloadModel {
        ProgressDialog mProgressDialog;
        Context context;
        private String mode;

        public Download(Context context, String mode, MatchModel matchModel, QuarterModel matchTotals, QuarterModel quarterModel1,
                        QuarterModel quarterModel2, QuarterModel quarterModel3, QuarterModel quarterModel4) {
            super(context, mode, matchModel, matchTotals, quarterModel1, quarterModel2, quarterModel3, quarterModel4);
            this.context = context;
            this.mode = mode;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("EXTERNAL", "external begin 1");

            mProgressDialog = ProgressDialog.show(this.context, "",
                    "Please wait, getting database...");
        }

        protected void onPostExecute(String answer) {
            mProgressDialog.dismiss();
            if (this.mode.equals("INSERT")) {
                if (answer.equals("Completed")) {
                    Toast.makeText(getApplicationContext(), "Match Added", Toast.LENGTH_SHORT).show();
                    Context context = RecordActivity.this;
                    Class destinationActivity = MainActivity.class;
                    Intent startChildActivityintent = new Intent(context, destinationActivity);
                    // getting text entered and passing along as an extra under the name of EXTRA_TEXT
                    startActivity(startChildActivityintent);
                } else {
                    Toast.makeText(getApplicationContext(), "An error occured while adding match", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void updateScore() {
        totalHome = goalsHome[0] + goalsHome[1] + goalsHome[2] + goalsHome[3];
        totalAway = goalsAway[0] + goalsAway[1] + goalsAway[2] + goalsAway[3];
        homeTotal.setText(String.format("%d", totalHome));
        homeQ1.setText(String.format("%d", goalsHome[0]));
        homeQ2.setText(String.format("%d", goalsHome[1]));
        homeQ3.setText(String.format("%d", goalsHome[2]));
        homeQ4.setText(String.format("%d", goalsHome[3]));
        awayTotal.setText(String.format("%d", totalAway));
        awayQ1.setText(String.format("%d", goalsAway[0]));
        awayQ2.setText(String.format("%d", goalsAway[1]));
        awayQ3.setText(String.format("%d", goalsAway[2]));
        awayQ4.setText(String.format("%d", goalsAway[3]));
    }

    public void stroke(View viewParent) {
        int idButton = viewParent.getId();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View strokeView = inflater.inflate(R.layout.layout_stroke,null);

                /*
                    public PopupWindow (View contentView, int width, int height)
                        Create a new non focusable popup window which can display the contentView.
                        The dimension of the window must be passed to this constructor.

                        The popup does not provide any background. This should be handled by
                        the content view.

                    Parameters
                        contentView : the popup's content
                        width : the popup's width
                        height : the popup's height
                */
        // Initialize a new instance of popup window
        mPopupWindow = new PopupWindow(
                strokeView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                true
        );
        mPopupWindow.setOutsideTouchable(false);

        // Set an elevation value for popup window
        // Call requires API level 21
        mPopupWindow.setElevation(5.0f);

        RadioGroup radioGroup = strokeView.findViewById(R.id.stroke_radio_group_cards);
        Switch switchConverted = strokeView.findViewById(R.id.switch_converted);

        Button buttonConfirm = strokeView.findViewById(R.id.stroke_confirm);
        buttonConfirm.setOnClickListener(v -> {
            int checkedId = radioGroup.getCheckedRadioButtonId();

            // find which radioButton is checked by id
            if (idButton == R.id.stroke_team_home_record) {
                if(checkedId == R.id.card_green) {
                    homeGreenCards[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.card_yellow) {
                    homeYellowCards[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.card_red) {
                    homeRedCards[CURRENT_QUARTER - 1] =+ 1;
                }

                if (switchConverted.isChecked()) {
                    goalsHome[CURRENT_QUARTER - 1] =+ 1;
                    strokeConvertedHome[CURRENT_QUARTER - 1] =+ 1;
                    updateScore();
                } else {
                    strokeNotConvertedHome[CURRENT_QUARTER - 1] =+ 1;
                }
            } else if (idButton == R.id.stroke_team_away_record) {
                if(checkedId == R.id.card_green) {
                    awayGreenCards[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.card_yellow) {
                    awayYellowCards[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.card_red) {
                    awayRedCards[CURRENT_QUARTER - 1] =+ 1;
                }

                if (switchConverted.isChecked()) {
                    goalsAway[CURRENT_QUARTER - 1] =+ 1;
                    strokeConvertedAway[CURRENT_QUARTER - 1] =+ 1;
                    updateScore();
                } else {
                    strokeNotConvertedAway[CURRENT_QUARTER - 1] =+ 1;
                }
            }
            mPopupWindow.dismiss();
        });

        Button buttonCancel = strokeView.findViewById(R.id.stroke_cancel);
        buttonCancel.setOnClickListener(v -> mPopupWindow.dismiss());
                /*
                    public void showAtLocation (View parent, int gravity, int x, int y)
                        Display the content view in a popup window at the specified location. If the
                        popup window cannot fit on screen, it will be clipped.
                        Learn WindowManager.LayoutParams for more information on how gravity and the x
                        and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
                        to specifying Gravity.LEFT | Gravity.TOP.

                    Parameters
                        parent : a parent view to get the getWindowToken() token from
                        gravity : the gravity which controls the placement of the popup window
                        x : the popup's x location offset
                        y : the popup's y location offset
                */
        // Finally, show the popup window at the center location of root relative layout
        mPopupWindow.showAtLocation(linearLayout, Gravity.CENTER,0,0);
    }

    public void shot(View viewParent) {
        int idButton = viewParent.getId();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View shotView = inflater.inflate(R.layout.layout_shot,null);

        // Initialize a new instance of popup window
        mPopupWindow = new PopupWindow(
                shotView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                true
        );
        mPopupWindow.setOutsideTouchable(false);

        // Set an elevation value for popup window
        mPopupWindow.setElevation(5.0f);

        RadioGroup radioGroup = shotView.findViewById(R.id.shot_radio_group);

        Button buttonConfirm = shotView.findViewById(R.id.shot_confirm);
        buttonConfirm.setOnClickListener(v -> {
            int checkedId = radioGroup.getCheckedRadioButtonId();
            // find which radioButton is checked by id
            if (idButton == R.id.shot_team_home_record) {
                shotsHome[CURRENT_QUARTER - 1] =+ 1;
                if(checkedId == R.id.shot_goal) {
                    goalsHome[CURRENT_QUARTER - 1] =+ 1;
                    updateScore();
                } else if(checkedId == R.id.shot_missed_outside) {
                    shotsHomeMissed[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.shot_missed_keeper) {
                    shotsHomeMissedKeeper[CURRENT_QUARTER - 1] =+ 1;
                }
            } else if (idButton == R.id.shot_team_away_record) {
                shotsAway[CURRENT_QUARTER - 1] =+ 1;
                if(checkedId == R.id.shot_goal) {
                    goalsAway[CURRENT_QUARTER - 1] =+ 1;
                    updateScore();
                } else if(checkedId == R.id.shot_missed_outside) {
                    shotsAwayMissed[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.shot_missed_keeper) {
                    shotsAwayMissedKeeper[CURRENT_QUARTER - 1] =+ 1;
                }
            }
            mPopupWindow.dismiss();
        });

        Button buttonCancel = shotView.findViewById(R.id.shot_cancel);
        buttonCancel.setOnClickListener(v -> mPopupWindow.dismiss());

        // Finally, show the popup window at the center location of root relative layout
        mPopupWindow.showAtLocation(linearLayout, Gravity.CENTER,0,0);
    }

    public void pc(View viewParent) {
        int idButton = viewParent.getId();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View pcView = inflater.inflate(R.layout.layout_pc,null);

        // Initialize a new instance of popup window
        mPopupWindow = new PopupWindow(
                pcView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                true
        );
        mPopupWindow.setOutsideTouchable(false);

        // Set an elevation value for popup window
        mPopupWindow.setElevation(5.0f);

        RadioGroup radioGroupCards = pcView.findViewById(R.id.pc_radio_group_cards);
        RadioGroup radioGroupFault = pcView.findViewById(R.id.pc_radio_group_fault);
        Switch switchConverted = pcView.findViewById(R.id.switch_converted);

        Button buttonConfirm = pcView.findViewById(R.id.pc_confirm);
        buttonConfirm.setOnClickListener(v -> {
            int checkedIdCards = radioGroupCards.getCheckedRadioButtonId();
            int checkedIdfault = radioGroupFault.getCheckedRadioButtonId();

            // find which radioButton is checked by id
            if (idButton == R.id.pc_team_home_record) {
                if(checkedIdCards == R.id.card_green) {
                    homeGreenCards[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdCards == R.id.card_yellow) {
                    homeYellowCards[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdCards == R.id.card_red) {
                    homeRedCards[CURRENT_QUARTER - 1] =+ 1;
                }

                if(checkedIdfault == R.id.fault_kick) {
                    faultHomeKick[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_backstick) {
                    faultHomeBackstick[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_stick) {
                    faultHomeStick[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_undercutting) {
                    faultHomeUndercutting[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_obstruction) {
                    faultHomeObstruction[CURRENT_QUARTER - 1] =+ 1;
                }

                if (switchConverted.isChecked()) {
                    goalsHome[CURRENT_QUARTER - 1] =+ 1;
                    pcConvertedHome[CURRENT_QUARTER - 1] =+ 1;
                    updateScore();
                } else {
                    pcNotConvertedHome[CURRENT_QUARTER - 1] =+ 1;
                }
            } else if (idButton == R.id.pc_team_away_record) {
                if(checkedIdCards == R.id.card_green) {
                    awayGreenCards[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdCards == R.id.card_yellow) {
                    awayYellowCards[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdCards == R.id.card_red) {
                    awayRedCards[CURRENT_QUARTER - 1] =+ 1;
                }

                if(checkedIdfault == R.id.fault_kick) {
                    faultAwayKick[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_backstick) {
                    faultAwayBackstick[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_stick) {
                    faultAwayStick[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_undercutting) {
                    faultAwayUndercutting[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_obstruction) {
                    faultAwayObstruction[CURRENT_QUARTER - 1] =+ 1;
                }

                if (switchConverted.isChecked()) {
                    goalsAway[CURRENT_QUARTER - 1] =+ 1;
                    pcConvertedAway[CURRENT_QUARTER - 1] =+ 1;
                    updateScore();
                } else {
                    pcNotConvertedAway[CURRENT_QUARTER - 1] =+ 1;
                }
            }
            mPopupWindow.dismiss();
        });

        Button buttonCancel = pcView.findViewById(R.id.pc_cancel);
        buttonCancel.setOnClickListener(v -> mPopupWindow.dismiss());

        // Finally, show the popup window at the center location of root relative layout
        mPopupWindow.showAtLocation(linearLayout, Gravity.CENTER,0,0);
    }

    public void outside(View viewParent) {
        int idButton = viewParent.getId();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View outsideView = inflater.inflate(R.layout.layout_outside,null);

        // Initialize a new instance of popup window
        mPopupWindow = new PopupWindow(
                outsideView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                true
        );
        mPopupWindow.setOutsideTouchable(false);

        // Set an elevation value for popup window
        mPopupWindow.setElevation(5.0f);

        RadioGroup radioGroup = outsideView.findViewById(R.id.outside_radio_group);

        Button buttonConfirm = outsideView.findViewById(R.id.outside_confirm);
        buttonConfirm.setOnClickListener(v -> {
            int checkedId = radioGroup.getCheckedRadioButtonId();
            shotsHome[CURRENT_QUARTER - 1] =+ 1;
            // find which radioButton is checked by id
            if (idButton == R.id.outside_team_home_record) {
                if(checkedId == R.id.outside_clearance) {
                    outsideHomeClearance[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.outside_side) {
                    outsideHomeSide[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.outside_corner) {
                    outsideHomeCorner[CURRENT_QUARTER - 1] =+ 1;
                }
            } else if (idButton == R.id.outside_team_away_record) {
                if(checkedId == R.id.outside_clearance) {
                    outsideAwayClearance[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.outside_side) {
                    outsideAwaySide[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.outside_corner) {
                    outsideAwayCorner[CURRENT_QUARTER - 1] =+ 1;
                }
            }
            mPopupWindow.dismiss();
        });

        Button buttonCancel = outsideView.findViewById(R.id.outside_cancel);
        buttonCancel.setOnClickListener(v -> mPopupWindow.dismiss());

        // Finally, show the popup window at the center location of root relative layout
        mPopupWindow.showAtLocation(linearLayout, Gravity.CENTER,0,0);
    }

    public void fault(View viewParent) {
        int idButton = viewParent.getId();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View faultView = inflater.inflate(R.layout.layout_fault,null);

        // Initialize a new instance of popup window
        mPopupWindow = new PopupWindow(
                faultView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                true
        );
        mPopupWindow.setOutsideTouchable(false);

        // Set an elevation value for popup window
        mPopupWindow.setElevation(5.0f);

        RadioGroup radioGroupCards = faultView.findViewById(R.id.fault_radio_group_cards);
        RadioGroup radioGroupFault = faultView.findViewById(R.id.fault_radio_group_fault);
        RadioGroup radioGroupPosition = faultView.findViewById(R.id.fault_radio_group_position);

        Button buttonConfirm = faultView.findViewById(R.id.fault_confirm);
        buttonConfirm.setOnClickListener(v -> {
            int checkedIdCards = radioGroupCards.getCheckedRadioButtonId();
            int checkedIdfault = radioGroupFault.getCheckedRadioButtonId();
            int checkedIdPosition = radioGroupPosition.getCheckedRadioButtonId();

            // find which radioButton is checked by id
            if (idButton == R.id.fault_team_home_record) {
                if(checkedIdCards == R.id.card_green) {
                    homeGreenCards[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdCards == R.id.card_yellow) {
                    homeYellowCards[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdCards == R.id.card_red) {
                    homeRedCards[CURRENT_QUARTER - 1] =+ 1;
                }

                if(checkedIdfault == R.id.fault_kick) {
                    faultHomeKick[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_backstick) {
                    faultHomeBackstick[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_stick) {
                    faultHomeStick[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_undercutting) {
                    faultHomeUndercutting[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_obstruction) {
                    faultHomeObstruction[CURRENT_QUARTER - 1] =+ 1;
                }

                if(checkedIdPosition == R.id.position1) {
                    faultPosition25Home[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdPosition == R.id.position2) {
                    faultPosition50Home[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdPosition == R.id.position3) {
                    faultPosition75Home[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdPosition == R.id.position4) {
                    faultPosition100Home[CURRENT_QUARTER - 1] =+ 1;
                }


            } else if (idButton == R.id.fault_team_away_record) {
                if(checkedIdCards == R.id.card_green) {
                    awayGreenCards[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdCards == R.id.card_yellow) {
                    awayYellowCards[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdCards == R.id.card_red) {
                    awayRedCards[CURRENT_QUARTER - 1] =+ 1;
                }

                if(checkedIdfault == R.id.fault_kick) {
                    faultAwayKick[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_backstick) {
                    faultAwayBackstick[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_stick) {
                    faultAwayStick[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_undercutting) {
                    faultAwayUndercutting[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdfault == R.id.fault_obstruction) {
                    faultAwayObstruction[CURRENT_QUARTER - 1] =+ 1;
                }

                if(checkedIdPosition == R.id.position1) {
                    faultPosition25Away[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdPosition == R.id.position2) {
                    faultPosition50Away[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdPosition == R.id.position3) {
                    faultPosition75Away[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedIdPosition == R.id.position4) {
                    faultPosition100Away[CURRENT_QUARTER - 1] =+ 1;
                }
            }
            mPopupWindow.dismiss();
        });

        Button buttonCancel = faultView.findViewById(R.id.fault_cancel);
        buttonCancel.setOnClickListener(v -> mPopupWindow.dismiss());

        // Finally, show the popup window at the center location of root relative layout
        mPopupWindow.showAtLocation(linearLayout, Gravity.CENTER,0,0);
    }

    public void updateQuarters(View view) {
        int id = view.getId();

        if (id == R.id.next_quarter) {
            if (CURRENT_QUARTER < 4) {
                CURRENT_QUARTER = CURRENT_QUARTER + 1;
            }
        } else if (id == R.id.previous_quarter) {
            if (CURRENT_QUARTER > 1) {
                CURRENT_QUARTER = CURRENT_QUARTER - 1;
            }
        }
        currentQuarterTextView.setText(mContext.getResources().getString(R.string.current_quarter_text) + Integer.toString(CURRENT_QUARTER));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    public void captureImage() {
        Log.d("START1", "begin");
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(RecordActivity.this,
                    new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        } else {
            Log.d("START2", "begin");

            openCamera();
        }

    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the camera");

        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Log.d("START3", "begin");

        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);

        if (camera_intent.resolveActivity(getPackageManager()) != null) {
            File image_file = null;

            try {
                image_file = getImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (image_file != null) {
                image_uri = FileProvider.getUriForFile(this,
                        FILE_AUTHORITY_PROVIDER, image_file);

                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);

                startActivityForResult(camera_intent, IMG_REQUEST);
            }
        }
    }

    private File getImageFile() throws IOException {
        String time_stamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String image_name = "jpg_" + time_stamp + "_";
        File storage_dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image_file = File.createTempFile(image_name, ".jpg", storage_dir);
        Log.d(TAG, "getImageFile: " + image_file);
        imagePathList.add(image_file.getAbsolutePath());
        return image_file;
    }

}
