package ck.edu.com.hockey_tracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import ck.edu.com.hockey_tracker.Data.DownloadModel;
import ck.edu.com.hockey_tracker.Data.MatchModel;
import ck.edu.com.hockey_tracker.Fragments.SettingsFragment;
import ck.edu.com.hockey_tracker.Fragments.homeFragment;
import ck.edu.com.hockey_tracker.Fragments.matchFragment;
import ck.edu.com.hockey_tracker.Fragments.newMatchFragment;
import ck.edu.com.hockey_tracker.Preferences.BaseActivity;
import ck.edu.com.hockey_tracker.Preferences.LocaleManager;

public class MainActivity extends BaseActivity
        implements
            newMatchFragment.OnFragmentInteractionListener,
            SettingsFragment.OnFragmentInteractionListenerSettings {

    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageView;
    Toolbar toolbar;
    View header;

    newMatchFragment newMatchFragment;

    ArrayList<MatchModel> matchModelArrayList = new ArrayList<>();
    String matchList;
    MatchModel matchModel;

    String mhomeTeam;
    String mawayTeam;
    String mdate;
    String mlocation;

    int currentFrag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // frameLayout = findViewById(R.id.frame);

        header = navigationView.getHeaderView(0);
        imageView = header.findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        if (savedInstanceState != null) {
            currentFrag = savedInstanceState.getInt("CurrentFrag");
            Log.d("CURRENT", String.valueOf(currentFrag));
        }

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // set default fragment
        newMatchFragment = new newMatchFragment();

        if (currentFrag == 0) {
            loadFragment(new homeFragment(), 0);
        } else if (currentFrag == 1) {
            loadFragment(newMatchFragment, 1);
        } else if (currentFrag == 2) {
            new Download(MainActivity.this, "GETALL").execute();
        } else if (currentFrag == 3) {
            loadFragment(new Fragment(), 3);
        } else if (currentFrag == 4) {
            loadFragment(new SettingsFragment(), 4);
        } else {
            loadFragment(new Fragment(), 5);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Handle navigation view item clicks here.
                int id = menuItem.getItemId();
                if (id == R.id.nav_home) {
                    loadFragment(new homeFragment(), 0);
                } else if (id == R.id.nav_new_match) {
                    loadFragment(newMatchFragment, 1);
                } else if (id == R.id.nav_previous_match) {
                    new Download(MainActivity.this, "GETALL").execute();
                } else if (id == R.id.nav_rules) {
                    loadFragment(new Fragment(), 3);
                } else if (id == R.id.nav_settings) {
                    loadFragment(new SettingsFragment(), 4);
                } else if (id == R.id.nav_info) {
                    loadFragment(new Fragment(), 5);
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        Log.d("CURRENTFRAG", String.valueOf(currentFrag));

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void loadFragment(Fragment fragment, int currentFragment) {
        currentFrag = currentFragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }

    public void loadFragmentExt(int currentFragment) {
        currentFrag = currentFragment;
        matchFragment matchFragmentm = matchFragment.newInstance(matchList);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, matchFragmentm);
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItemSave = menu.findItem(R.id.action_save);
        MenuItem menuItemCamera = menu.findItem(R.id.action_picture);
        MenuItem menuItemSettings = menu.findItem(R.id.action_settings);

        menuItemCamera.setVisible(false);
        menuItemCamera.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        if (currentFrag == 1) {
            menuItemSave.setVisible(true);
            menuItemSettings.setVisible(false);
        } else {
            menuItemSave.setVisible(false);
            menuItemSettings.setVisible(true);
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_picture) {
            return true;
        } else if (id == R.id.action_save) {
            newMatchFragment.newMatch();
            Context context = MainActivity.this;
            Class destinationActivity = RecordActivity.class;
            Intent startChildActivityintent = new Intent(context, destinationActivity);
            // getting text entered and passing along as an extra under the name of EXTRA_TEXT
            startChildActivityintent.putExtra("HOME", mhomeTeam);
            startChildActivityintent.putExtra("AWAY", mawayTeam);
            startChildActivityintent.putExtra("DATE", mdate);
            startChildActivityintent.putExtra("LOCATION", mlocation);
            startActivity(startChildActivityintent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNewLocale(AppCompatActivity mContext, @LocaleManager.LocaleDef String language) {
        LocaleManager.setNewLocale(this, language);
        Intent intent = mContext.getIntent();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("CurrentFrag", currentFrag);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        currentFrag = savedInstanceState.getInt("CurrentFrag");
    }

    @Override
    public void onFragmentInteraction(String language) {
        if (language.equals("French")) {
            setNewLocale(this, LocaleManager.FRENCH);
        } else if (language.equals("English")) {
            setNewLocale(this, LocaleManager.ENGLISH);
        }

    }

    @Override
    public void onSubmit(String homeTeam, String awayTeam, String date, String location) {
        mhomeTeam = homeTeam;
        mawayTeam = awayTeam;
        mdate = date;
        mlocation = location;

    }

    public class Download extends DownloadModel {
        ProgressDialog mProgressDialog;
        Context context;
        private String mode;

        public Download(Context context, String mode) {
            super(context, mode);
            this.context = context;
            this.mode = mode;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(this.context, "",
                    "Please wait, getting database...");
        }

        protected void onPostExecute(String answer) {
            mProgressDialog.dismiss();
            if (this.mode.equals("GETALL")) {
                matchList = answer;
                loadFragmentExt(2);
            }

        }
    }
}
