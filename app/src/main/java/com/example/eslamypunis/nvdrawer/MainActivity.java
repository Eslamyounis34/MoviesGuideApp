package com.example.eslamypunis.nvdrawer;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NowPlaying play=new NowPlaying();
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.laout_for_fragment,play,play.getTag()).addToBackStack("F").commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item=menu.findItem(R.id.Search);
        SearchView sv=(SearchView)item.getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String query=s;
                Intent i=new Intent(MainActivity.this,SearchMovies.class);
                i.putExtra("WORD",query);
                startActivity(i);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the came camerafragment cam=new camerafragment();
            toolbar.setTitle("Popular");
            camerafragment cam=new camerafragment();
            android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.laout_for_fragment,cam,cam.getTag()).commit();

        } else if (id == R.id.nav_now_playing) {
            toolbar.setTitle("Now Playing");
            NowPlaying play=new NowPlaying();
            android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.laout_for_fragment,play,play.getTag()).commit();

        } else if (id == R.id.nav_top_rated) {
            toolbar.setTitle("Top Rated");
            TopRated top=new TopRated();
            android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.laout_for_fragment,top,top.getTag()).commit();

        }  else if (id == R.id.Booking) {
            startActivity(new Intent(this,BookingMovies.class));

        } else if (id == R.id.favourites) {
            startActivity(new Intent(MainActivity.this,FavouritsActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
