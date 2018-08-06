package com.example.eslamypunis.nvdrawer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class FavouritsActivity extends AppCompatActivity {
    ListView ls;
    ArrayList<String> moviesName;
    ArrayList<String>moviesPost;
    ArrayList<String>moviesRates;
    android.support.v7.widget.Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourits);
        ls=(ListView)findViewById(R.id.favouriteList);
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.tool);
        toolbar.setTitle("Favourites");
        setSupportActionBar(toolbar);
        moviesName=new ArrayList<>();
        moviesPost=new ArrayList<>();
        moviesRates=new ArrayList<>();
        Sqlite db=new Sqlite(this);
        SQLiteDatabase sql=db.getReadableDatabase();
        Cursor c=sql.rawQuery("select * from "+Sqlite.Table_name,null);
        if (c.moveToFirst())
        {
            do
            {
                String movie_name=c.getString(c.getColumnIndex(Sqlite.movie_title));
                String movie_image=c.getString(c.getColumnIndex(Sqlite.movie_poster));
                String movie_rate=c.getString(c.getColumnIndex(Sqlite.movie_avg));

                moviesName.add(movie_name);
                moviesPost.add(movie_image);
                moviesRates.add(movie_rate);
            }while (c.moveToNext());
            favouriteAdapter adp=new favouriteAdapter(moviesName,moviesPost,moviesRates,FavouritsActivity.this);
            ls.setAdapter(adp);
        }


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbaritems,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.back)
            startActivity(new Intent(this,MainActivity.class));
        return super.onOptionsItemSelected(item);
    }
}
