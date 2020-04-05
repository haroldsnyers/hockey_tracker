package ck.edu.com.hockey_tracker;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class RecordActivity extends AppCompatActivity {

    private int CURRENT_QUARTER = 1;
    private TextView currentQuarterTextView;

    private Context mContext;

    private LinearLayout linearLayout;

    private PopupWindow mPopupWindow;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentQuarterTextView = findViewById(R.id.current_quarter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItemSave = menu.findItem(R.id.action_save);
        MenuItem menuItemCamera = menu.findItem(R.id.action_picture);

        menuItemSave.setVisible(true);
        menuItemCamera.setVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();

        if (id == R.id.home) {
            this.finish();
        } else if (id == R.id.action_save){
            return true;
        } else if (id == R.id.action_picture) {

        }

        return super.onOptionsItemSelected(item);
    }

    public void safeMatch() {

    }

    public void stroke(View viewParent) {
        int idButton = viewParent.getId();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.layout_stroke,null);

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
                customView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // Set an elevation value for popup window
        // Call requires API level 21
        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.stroke_radio_group_cards);
        Switch switchConverted = (Switch) findViewById(R.id.switch_converted);

        Button buttonConfirm = (Button)findViewById(R.id.stroke_confirm);
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
                } else {
                    strokeNotConvertedAway[CURRENT_QUARTER - 1] =+ 1;
                }
            }
            mPopupWindow.dismiss();
        });

        Button buttonCancel = (Button) findViewById(R.id.shot_cancel);
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
        View customView = inflater.inflate(R.layout.layout_shot,null);

        // Initialize a new instance of popup window
        mPopupWindow = new PopupWindow(
                customView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // Set an elevation value for popup window
        // Call requires API level 21
        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.shot_radio_group);

        Button buttonConfirm = (Button)findViewById(R.id.shot_confirm);
        buttonConfirm.setOnClickListener(v -> {
            int checkedId = radioGroup.getCheckedRadioButtonId();
            shotsHome[CURRENT_QUARTER - 1] =+ 1;
            // find which radioButton is checked by id
            if (idButton == R.id.stroke_team_home_record) {
                if(checkedId == R.id.shot_goal) {
                    goalsHome[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.shot_missed_outside) {
                    shotsHomeMissed[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.shot_missed_keeper) {
                    shotsHomeMissedKeeper[CURRENT_QUARTER - 1] =+ 1;
                }
            } else if (idButton == R.id.stroke_team_away_record) {
                if(checkedId == R.id.shot_goal) {
                    goalsAway[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.shot_missed_outside) {
                    shotsAwayMissed[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.shot_missed_keeper) {
                    shotsAwayMissedKeeper[CURRENT_QUARTER - 1] =+ 1;
                }
            }
            mPopupWindow.dismiss();
        });

        Button buttonCancel = (Button) findViewById(R.id.shot_cancel);
        buttonCancel.setOnClickListener(v -> mPopupWindow.dismiss());

        // Finally, show the popup window at the center location of root relative layout
        mPopupWindow.showAtLocation(linearLayout, Gravity.CENTER,0,0);
    }

    public void pc(View viewParent) {
        int idButton = viewParent.getId();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.layout_stroke,null);

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
                customView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // Set an elevation value for popup window
        // Call requires API level 21
        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.stroke_radio_group_cards);
        Switch switchConverted = (Switch) findViewById(R.id.switch_converted);

        Button buttonConfirm = (Button)findViewById(R.id.stroke_confirm);
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
                } else {
                    strokeNotConvertedAway[CURRENT_QUARTER - 1] =+ 1;
                }
            }
            mPopupWindow.dismiss();
        });

        Button buttonCancel = (Button) findViewById(R.id.shot_cancel);
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

    public void outside(View viewParent) {
        int idButton = viewParent.getId();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.layout_shot,null);

        // Initialize a new instance of popup window
        mPopupWindow = new PopupWindow(
                customView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // Set an elevation value for popup window
        // Call requires API level 21
        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.shot_radio_group);

        Button buttonConfirm = (Button)findViewById(R.id.shot_confirm);
        buttonConfirm.setOnClickListener(v -> {
            int checkedId = radioGroup.getCheckedRadioButtonId();
            shotsHome[CURRENT_QUARTER - 1] =+ 1;
            // find which radioButton is checked by id
            if (idButton == R.id.stroke_team_home_record) {
                if(checkedId == R.id.shot_goal) {
                    goalsHome[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.shot_missed_outside) {
                    shotsHomeMissed[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.shot_missed_keeper) {
                    shotsHomeMissedKeeper[CURRENT_QUARTER - 1] =+ 1;
                }
            } else if (idButton == R.id.stroke_team_away_record) {
                if(checkedId == R.id.shot_goal) {
                    goalsAway[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.shot_missed_outside) {
                    shotsAwayMissed[CURRENT_QUARTER - 1] =+ 1;
                } else if(checkedId == R.id.shot_missed_keeper) {
                    shotsAwayMissedKeeper[CURRENT_QUARTER - 1] =+ 1;
                }
            }
            mPopupWindow.dismiss();
        });

        Button buttonCancel = (Button) findViewById(R.id.shot_cancel);
        buttonCancel.setOnClickListener(v -> mPopupWindow.dismiss());

        // Finally, show the popup window at the center location of root relative layout
        mPopupWindow.showAtLocation(linearLayout, Gravity.CENTER,0,0);
    }

    public void fault(View viewParent) {
        int idButton = viewParent.getId();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.layout_stroke,null);

        // Initialize a new instance of popup window
        mPopupWindow = new PopupWindow(
                customView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // Set an elevation value for popup window
        // Call requires API level 21
        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.stroke_radio_group_cards);
        Switch switchConverted = (Switch) findViewById(R.id.switch_converted);

        Button buttonConfirm = (Button)findViewById(R.id.stroke_confirm);
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
                } else {
                    strokeNotConvertedAway[CURRENT_QUARTER - 1] =+ 1;
                }
            }
            mPopupWindow.dismiss();
        });

        Button buttonCancel = (Button) findViewById(R.id.shot_cancel);
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
        currentQuarterTextView.setText(R.string.current_quarter_text + CURRENT_QUARTER);
    }


}
