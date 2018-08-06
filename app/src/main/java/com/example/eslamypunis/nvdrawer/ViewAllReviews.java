package com.example.eslamypunis.nvdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class ViewAllReviews extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_reviews);
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.toolreviewlist);
        toolbar.setTitle("All Reviews");
        setSupportActionBar(toolbar);
        Intent i=getIntent();
        ArrayList<String> getAuthors=i.getStringArrayListExtra("GETAuthors");
        ArrayList<String> getRev=i.getStringArrayListExtra("GETRev");
        if(getAuthors.isEmpty())
        {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewAllReviews.this, LinearLayoutManager.VERTICAL, false);
            NonReviewRecyclerAdapter noreview=new NonReviewRecyclerAdapter(ViewAllReviews.this);
            RecyclerView recyclerView = findViewById(R.id.reviesrecycler);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(noreview);

        }
        else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewAllReviews.this, LinearLayoutManager.VERTICAL, false);
            RecyclerView recyclerView = findViewById(R.id.reviesrecycler);
            ReviewsRecyclerAdapter reviewsRecyclerAdapter = new ReviewsRecyclerAdapter(getAuthors, getRev, ViewAllReviews.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(reviewsRecyclerAdapter);
        }


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbaritems,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.back)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
