package com.e.driver.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.e.driver.R;
import com.e.driver.activities.ui.about.AboutFragment;
import com.e.driver.activities.ui.bookings.MyBookingFragments;
import com.e.driver.activities.ui.help.HelpFragment;
import com.e.driver.activities.ui.home.HomeFragment;
import com.e.driver.activities.ui.tablayout.TablayoutFragment;
import com.e.driver.utils.Constants;
import com.e.driver.utils.SamsPrefs;

public class DashBoardNewactivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView mobile;
    private TextView email;
    private Toolbar toolbar;
    String role;
    private MenuItem navHome,navPrime,navBooking,navNewBooking;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = findViewById(R.id.toolbar);
        logout = findViewById(R.id.logout);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");
        role = SamsPrefs.getString(this, Constants.ROLE);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View header = navigationView.getHeaderView(0);
        Menu menues = navigationView.getMenu();

        navHome = menues.findItem(R.id.nav_home);
        navBooking = menues.findItem(R.id.nav_mybooking);
        navNewBooking = menues.findItem(R.id.nav_newbooking);
        navPrime = menues.findItem(R.id.nav_prime_member);


        if (role.equalsIgnoreCase("2")){
            navHome.setVisible(false);
            navPrime.setVisible(false);
            navNewBooking.setVisible(false);
            navBooking.setVisible(true);
            replaceFragment(new TablayoutFragment());

        }else{
            navHome.setVisible(true);
            navPrime.setVisible(true);
            navNewBooking.setVisible(true);
            navBooking.setVisible(true);
            replaceFragment(new HomeFragment());
        }
        mobile = header.findViewById(R.id.navTextViewMobile);
        email = header.findViewById(R.id.nav_textViewEmail);
        mobile.setText(SamsPrefs.getString(getApplicationContext(), Constants.MOBILE_NUMBER));
        email.setText(SamsPrefs.getString(getApplicationContext(), Constants.EMAIL));

        navigationView.setNavigationItemSelectedListener(this);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SamsPrefs.clear(DashBoardNewactivity.this);
                Intent intent = new Intent(DashBoardNewactivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board_newactivity, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            toolbar.setTitle("Home");
            replaceFragment(new HomeFragment());
            // Handle the camera action
        } else if (id == R.id.nav_mybooking) {
            if (role.equalsIgnoreCase("2")) {
                replaceFragment(new TablayoutFragment());
            } else {
                replaceFragment(new MyBookingFragments());

            }
            toolbar.setTitle("My Bookings");

        } else if (id == R.id.nav_newbooking) {
            Intent intent = new Intent(DashBoardNewactivity.this, BookRequestActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.enter, R.anim.exit);


        } else if (id == R.id.nav_prime_member) {
            Intent intent = new Intent(DashBoardNewactivity.this, JoiPrimeMembershipActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.enter, R.anim.exit);


        } else if (id == R.id.nav_rateus) {
            toolbar.setTitle("About Us");
        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
            startActivity(Intent.createChooser(sharingIntent, "Share using"));

        } else if (id == R.id.nav_about) {
            replaceFragment(new AboutFragment());
            toolbar.setTitle("About Us");


        } else if (id == R.id.nav_help) {
            replaceFragment(new HelpFragment());
            toolbar.setTitle("Help");


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, fragment, "NewFragmentTag");
        ft.commit();
    }
}
