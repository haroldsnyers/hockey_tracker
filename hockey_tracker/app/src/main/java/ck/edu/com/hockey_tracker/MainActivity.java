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

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // set default fragment
        loadFragment(new homeFragment());
        newMatchFragment = new newMatchFragment();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Handle navigation view item clicks here.
                int id = menuItem.getItemId();
                if (id == R.id.nav_home) {
                    loadFragment(new homeFragment());
                } else if (id == R.id.nav_new_match) {
                    loadFragment(newMatchFragment);
                } else if (id == R.id.nav_previous_match) {
                    new Download(MainActivity.this, "GETALL").execute();
                } else if (id == R.id.nav_rules) {
                    loadFragment(new Fragment());
                } else if (id == R.id.nav_settings) {
                    loadFragment(new SettingsFragment());
                } else if (id == R.id.nav_info) {
                    loadFragment(new Fragment());
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }

    public void loadFragmentExt() {
        matchFragment matchFragment = ck.edu.com.hockey_tracker.Fragments.matchFragment.newInstance(matchList);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, matchFragment);
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
        boolean visible = false;
        MenuItem menuItemSave = menu.findItem(R.id.action_save);
        MenuItem menuItemCamera = menu.findItem(R.id.action_picture);
        menuItemSave.setVisible(visible);
        menuItemCamera.setVisible(visible);
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
    public void onFragmentInteraction(String language) {
        if (language.equals("French")) {
            setNewLocale(this, LocaleManager.FRENCH);
        } else if (language.equals("English")) {
            setNewLocale(this, LocaleManager.ENGLISH);
        }

    }

    @Override
    public void onSubmit(MatchModel match) {
        matchModel = match;
        new Download(MainActivity.this, "INSERT", matchModel).execute();
    }

    public class Download extends DownloadModel {
        ProgressDialog mProgressDialog;
        Context context;
        private String mode;

        public Download(Context context, String mode, MatchModel matchModel) {
            super(context, mode, matchModel);
            this.context = context;
            this.mode = mode;
        }

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
            if (this.mode.equals("INSERT")) {
                if (answer.equals("Completed")) {
                    Toast.makeText(getApplicationContext(), "Match Added", Toast.LENGTH_SHORT).show();
                    loadFragment(new homeFragment());
                } else {
                    Toast.makeText(getApplicationContext(), "An error occured while adding match", Toast.LENGTH_SHORT).show();
                }
            } else if (this.mode.equals("GETALL")) {
                matchList = answer;
                loadFragmentExt();
            }

        }
    }
}
