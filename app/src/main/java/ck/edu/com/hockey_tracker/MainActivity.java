package ck.edu.com.hockey_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import ck.edu.com.hockey_tracker.Fragments.homeFragment;
import ck.edu.com.hockey_tracker.Fragments.matchFragment;
import ck.edu.com.hockey_tracker.Fragments.newMatchFragment;
import ck.edu.com.hockey_tracker.dummy.DummyContent;

public class MainActivity extends BaseActivity
        implements
            matchFragment.OnListFragmentInteractionListener,
            SettingsFragment.OnFragmentInteractionListenerSettings {

    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageView;
    Toolbar toolbar;
    View header;

    ck.edu.com.hockey_tracker.Fragments.newMatchFragment newMatchFragment;

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
                    loadFragment(new matchFragment());
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
        }

        return super.onOptionsItemSelected(item);
    }

    private void setNewLocale(AppCompatActivity mContext, @LocaleManager.LocaleDef String language) {
        LocaleManager.setNewLocale(this, language);
        Intent intent = mContext.getIntent();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onFragmentInteraction(String language) {
        if (language.equals("French")) {
            setNewLocale(this, LocaleManager.FRENCH);
        } else if (language.equals("English")) {
            setNewLocale(this, LocaleManager.ENGLISH);
        }

    }
}
