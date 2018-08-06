package com.example.eslamypunis.nvdrawer;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.design.internal.NavigationMenu;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class ViewInfo extends AppCompatActivity {
    TextView movie_title, movie_date, movie_overview, movie_rate,movie_budget,movie_runtime,movie_status;
    String base_url="https://api.themoviedb.org/3/movie/";
    String remain_url="?api_key=e76112b72c6c245384a5ecfd814a3ec2&append_to_response=credits";
    String remain_video_url="?api_key=e76112b72c6c245384a5ecfd814a3ec2&append_to_response=videos";
   String image_base_url="https://image.tmdb.org/t/p/w1280/";
    String poster_base_url="https://image.tmdb.org/t/p/w342/";
    String profile_base_url="https://image.tmdb.org/t/p/w92/";
    String full_profile_path="";
    ArrayList<String> castImageUrl=new ArrayList<>();
    RatingBar ratingBar;
    ImageView movie_backdrop,movie_poster,demo;
   String full_backdrop_path="";
   String full_poster_path="";
   String base_url_share="https://www.themoviedb.org/movie/";
   String remain_url_share="";
   String full_url_share="";
   String base_video_url="https://www.youtube.com/watch?v=";
   String full_video_youtube_url="";
   String remain_video_req="";
   String full_video_url="";
   ArrayList<String>trailers=new ArrayList<>();
   String videoThumbnailBaseUrl="https://img.youtube.com/vi/";
   String fullVideoThumbnialUrl="";
   String remain_backdrop_url="/images?api_key=e76112b72c6c245384a5ecfd814a3ec2";
    String full_backdrop_url="";
    String ful_backdrop_path="";
    ArrayList<String>videosThumbnial=new ArrayList<>();
    ArrayList<String>videosName =new ArrayList<>();
    ArrayList<String>castNames=new ArrayList<>();
    ArrayList<String>backdropsImages=new ArrayList<>();
    ArrayList<String>authorNames=new ArrayList<>();
    ArrayList<String>reviewsContent=new ArrayList<>();
    FabSpeedDial fabSpeedDial;
    RelativeLayout relativeLayout;
    ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info);


        ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        movie_backdrop = (ImageView) findViewById(R.id.moviebackdrop);
        movie_poster = (ImageView) findViewById(R.id.movieposter);
        movie_title = (TextView) findViewById(R.id.movietitle);
        movie_overview = (TextView) findViewById(R.id.movieoverview);
        movie_date = (TextView) findViewById(R.id.moviedate);
        movie_rate = (TextView) findViewById(R.id.movierate);
        movie_budget = (TextView) findViewById(R.id.moviebudget);
        movie_runtime = (TextView) findViewById(R.id.movieruntime);
        movie_status=(TextView)findViewById(R.id.moviestatus);
        fabSpeedDial=(FabSpeedDial)findViewById(R.id.fabsetting);
        relativeLayout=(RelativeLayout)findViewById(R.id.gotoreviews);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.shareitem)
                {
                    shareMovie();
                }
                else if(menuItem.getItemId()==R.id.favitem)
                {
                    Sqlite db=new Sqlite(ViewInfo.this);
                    SQLiteDatabase sql=db.getWritableDatabase();
                    ContentValues contentValues=new ContentValues();
                    String add_title=movie_title.getText().toString();
                    String add_overview=movie_overview.getText().toString();
                    String add_rate=movie_rate.getText().toString();
                    String add_release=movie_date.getText().toString();
                    contentValues.put(Sqlite.movie_poster,full_poster_path);
                    contentValues.put(Sqlite.movie_title,add_title);
                    contentValues.put(Sqlite.movie_overview,add_overview);
                    contentValues.put(Sqlite.movie_avg,add_rate);
                    contentValues.put(Sqlite.movie_date,add_release);
                    sql.insert(Sqlite.Table_name,null,contentValues);
                    Toast.makeText(ViewInfo.this, "Added to your Favourites", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });



        Intent i = getIntent();
        final String title = i.getExtras().getString("movietitle");
        String id = i.getExtras().getString("movieid");
        remain_url_share = id + "?";
        full_url_share=base_url_share+remain_url_share;
        String total_url = base_url + id + remain_url;
        RequestQueue q = Volley.newRequestQueue(ViewInfo.this);
        StringRequest request = new StringRequest(Request.Method.GET, total_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String getOverView = object.getString("overview");
                    String getBackDropPath = object.getString("backdrop_path");
                    String getPosterPath = object.getString("poster_path");
                    String getRate = object.getString("vote_average");
                    String getRunTime = object.getString("runtime");
                    String getStatus=object.getString("status");
                    JSONObject getCredits = object.getJSONObject("credits");
                    JSONArray getCast = getCredits.getJSONArray("cast");
                    for (int i = 0; i < 7; i++) {
                        JSONObject castobject = getCast.getJSONObject(i);
                        String castImages = castobject.getString("profile_path");
                        String getcastNames=castobject.getString("name");
                        full_profile_path = profile_base_url + castImages;
                        castImageUrl.add(full_profile_path);
                        castNames.add(getcastNames);

                    }

                    Log.d("getImages", castImageUrl.toString());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(ViewInfo.this, LinearLayoutManager.HORIZONTAL, false);
                    RecyclerView recyclerView = findViewById(R.id.recycler);
                    recyclerView.setLayoutManager(layoutManager);
                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(castImageUrl,castNames ,ViewInfo.this);
                    recyclerView.setAdapter(recyclerViewAdapter);


                    String getBudget = object.getString("budget");
                    String getDate = object.getString("release_date");
                    full_backdrop_path = image_base_url + getBackDropPath;
                    full_poster_path = poster_base_url + getPosterPath;
                    Picasso.with(ViewInfo.this).load(full_backdrop_path).into(movie_backdrop);
                    Picasso.with(ViewInfo.this).load(full_poster_path).into(movie_poster);
                    movie_title.setText(title);
                    movie_overview.setText(getOverView);
                    movie_rate.setText(getRate);
                    ratingBar.setRating(Float.parseFloat(getRate) / 2);
                    movie_runtime.setText(getRunTime+" Min");
                    movie_date.setText(getDate);
                    movie_budget.setText(getBudget+" M");
                    movie_status.setText(getStatus);
                    progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

            }
        });

        q.add(request);
        full_video_url=base_url+id+remain_video_url;
        RequestQueue queue=Volley.newRequestQueue(this);
        StringRequest req=new StringRequest(Request.Method.GET, full_video_url, new Response.Listener<String>() {
            @Override


            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    JSONObject videosObject=object.getJSONObject("videos");
                    JSONArray videosArray=videosObject.getJSONArray("results");
                    for (int i=0;i<videosArray.length();i++)
                    {
                        JSONObject obj=videosArray.getJSONObject(i);
                        String videoKey=obj.getString("key");
                        String videoName=obj.getString("name");
                        full_video_youtube_url=base_video_url+videoKey;
                        fullVideoThumbnialUrl=videoThumbnailBaseUrl+videoKey+"/0.jpg";
                        videosThumbnial.add(fullVideoThumbnialUrl);
                        trailers.add(full_video_youtube_url);
                        videosName.add(videoName);

                    }
                    Log.d("Videos",trailers.toString());
                    LinearLayoutManager manager = new LinearLayoutManager(ViewInfo.this, LinearLayoutManager.HORIZONTAL, false);
                    RecyclerView recyclerView = findViewById(R.id.videosrecycler);
                    recyclerView.setLayoutManager(manager);
                    VideoRecyclerAdapter videoRecyclerAdapter = new VideoRecyclerAdapter(videosThumbnial,videosName,trailers,ViewInfo.this);
                    recyclerView.setAdapter(videoRecyclerAdapter);







                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(req);


         full_backdrop_url=base_url+id+remain_backdrop_url;
        RequestQueue que=Volley.newRequestQueue(this);
        StringRequest bdReq=new StringRequest(Request.Method.GET, full_backdrop_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    JSONArray backdrops=object.getJSONArray("backdrops");
                    for(int i=0;i<backdrops.length();i++)
                    {
                        JSONObject obj=backdrops.getJSONObject(i);
                        String getBackdropPath=obj.getString("file_path");
                        full_backdrop_path=image_base_url+getBackdropPath;
                        backdropsImages.add(full_backdrop_path);
                    }
                    Log.d("Backdrops",backdropsImages.toString());
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ViewInfo.this, LinearLayoutManager.HORIZONTAL, false);
                    RecyclerView recyclerView = findViewById(R.id.imagesrecycler);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    BackdropAdapter adp=new BackdropAdapter(backdropsImages,ViewInfo.this);
                    recyclerView.setAdapter(adp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        que.add(bdReq);



        String reviews_remain_url="/reviews?api_key=e76112b72c6c245384a5ecfd814a3ec2&language=en-US&page=1";
        String full_reviews_url=base_url+id+reviews_remain_url;
        RequestQueue queReviews=Volley.newRequestQueue(this);
        StringRequest reqReviews=new StringRequest(Request.Method.GET, full_reviews_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    JSONArray reviews=object.getJSONArray("results");
                    for (int i=0;i<reviews.length();i++)
                    {
                        JSONObject obj=reviews.getJSONObject(i);
                        String author_name=obj.getString("author");
                        String author_content=obj.getString("content");
                        authorNames.add(author_name);
                        reviewsContent.add(author_content);
                    }
                    Log.d("Authors",authorNames.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queReviews.add(reqReviews);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(ViewInfo.this,ViewAllReviews.class);
                i.putStringArrayListExtra("GETAuthors",authorNames);
                i.putStringArrayListExtra("GETRev",reviewsContent);
                startActivity(i);
            }
        });

    }

    public void shareMovie()
    {
        Intent i=new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT,full_url_share);
        i.setType("text/plain");
        startActivity(i);
    }


}
