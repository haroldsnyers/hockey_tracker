package ck.edu.com.hockey_tracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ck.edu.com.hockey_tracker.Data.DownloadModel;
import ck.edu.com.hockey_tracker.Fragments.Detailfragment;
import ck.edu.com.hockey_tracker.Fragments.ImageViewFragment;
import ck.edu.com.hockey_tracker.Fragments.dummy.DummyContent;

public class DetailActivity extends AppCompatActivity implements
        Detailfragment.OnFragmentInteractionListener,
        ImageViewFragment.OnListFragmentInteractionListener {

    String quarterList;
    String homeName;
    String awayName;
    String date;
    String location;
    String imagePaths;
    int idMatch;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    new Download(DetailActivity.this, "GETMATCH", idMatch).execute();
                    return true;
                case R.id.navigation_dashboard:
                    loadFragmentImage();
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentThatStartedThisActivity = getIntent();

        // we shouldn't assume that every intent has a data in it thus getting the extra
        // with an if statement
        // checks if intent has an extra data called EXTRA_TEXT

        if (intentThatStartedThisActivity.hasExtra("HOMENAME")) {
            String queryEntered = intentThatStartedThisActivity.getStringExtra("HOME");
            homeName = queryEntered;
        }
        if (intentThatStartedThisActivity.hasExtra("AWAYNAME")) {
            String queryEntered = intentThatStartedThisActivity.getStringExtra("AWAY");
            awayName = queryEntered;
        }
        if (intentThatStartedThisActivity.hasExtra("DATE")) {
            String queryEntered = intentThatStartedThisActivity.getStringExtra("DATE");
            date = queryEntered;
        }
        if (intentThatStartedThisActivity.hasExtra("LOCATION")) {
            String queryEntered = intentThatStartedThisActivity.getStringExtra("LOCATION");
            location = queryEntered;
        }
        if (intentThatStartedThisActivity.hasExtra("IMAGES")) {
            String queryEntered = intentThatStartedThisActivity.getStringExtra("IMAGES");
            imagePaths = queryEntered;
            Log.d("IMAGES", imagePaths);
        }
        if (intentThatStartedThisActivity.hasExtra("ID")) {
            String queryEntered = intentThatStartedThisActivity.getStringExtra("ID");
            idMatch = Integer.parseInt(queryEntered);
            Log.d("TESTID", String.valueOf(idMatch));
        }

        new Download(DetailActivity.this, "GETMATCH", idMatch).execute();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();

        if (id == R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadFragmentDetail() {
        Detailfragment detailfragment = Detailfragment.newInstance(quarterList, homeName, awayName);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameDetail, detailfragment);
        transaction.commit();
    }

    public void loadFragmentImage() {
        ImageViewFragment imageFragment = ImageViewFragment.newInstance(1);

        ArrayList<String> list =
                Stream.of(imagePaths.split(","))
                        .collect(Collectors.toCollection(ArrayList<String>::new));
        for (int i=0; i < list.size(); i++){
            Log.d("IMAGESSTORAGE", list.get(i));
            Log.d("IMAGESSTORAGEDDDD", list.get(i).substring(0, 1));
            if (list.get(i).substring(0, 1).equals("[")){
                String newString = list.get(i).substring(1);
                Log.d("IMAGESSTORAGEDDDD", newString);
                list.set(i, newString);
            }
            if (list.get(i).substring(0, 1).equals(" ")) {
                String newString = list.get(i).substring(1);
                list.set(i, newString);
            }
            if (list.get(i).substring(list.get(i).length() - 1).equals("]")) {
                String newString = list.get(i).substring(0, list.get(i).length()-1);
                list.set(i, newString);
            }
            Log.d("IMAGESSTORAGESSSSS", list.get(i));
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("data", list);
        imageFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameDetail, imageFragment);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    public class Download extends DownloadModel {
        ProgressDialog mProgressDialog;
        Context context;
        private String mode;
        int idMatch;

        public Download(Context context, String mode, int idmatch) {
            super(context, mode, idmatch);
            this.context = context;
            this.mode = mode;
            this.idMatch = idmatch;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(this.context, "",
                    "Please wait, getting database...");
        }

        protected void onPostExecute(String answer) {
            mProgressDialog.dismiss();
            if (this.mode.equals("GETMATCH")) {
                quarterList = answer;
                loadFragmentDetail();
            }

        }
    }
}
