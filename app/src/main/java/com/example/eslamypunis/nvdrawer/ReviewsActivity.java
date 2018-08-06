package com.example.eslamypunis.nvdrawer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.renderscript.Sampler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

public class ReviewsActivity extends AppCompatActivity {
    TextView author,review;
    ImageView share;
    android.support.v7.widget.Toolbar toolbar;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        author=(TextView)findViewById(R.id.authorNameact);
        review=(TextView)findViewById(R.id.reviewcontentact);
        share=(ImageView)findViewById(R.id.sharereviewbt);
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.toolreview);
        toolbar.setTitle("Reviews");
        setSupportActionBar(toolbar);
        Bundle b=getIntent().getExtras();
        final String authorName=b.getString("AUTHOR");
        toolbar.setTitle(authorName+" Review");

        final String reviewContent=b.getString("REVIEW");
        author.setText(authorName);
        review.setText(reviewContent);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String total_info=authorName+"\n"+reviewContent;
                Intent i = new Intent();
                i.setAction(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_TEXT,total_info);
                i.setType("text/plain");
                startActivity(i);

            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbaritems,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.back)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
