package com.example.eslamypunis.nvdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchMovies extends AppCompatActivity {
    ListView ls;
   String base_url="https://api.themoviedb.org/3/search/movie?query=";
   String   key="&api_key=e76112b72c6c245384a5ecfd814a3ec2&page=1";
   String    image_base_url="https://image.tmdb.org/t/p/w500/";
    android.support.v7.widget.Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        ls=(ListView)findViewById(R.id.search_list);
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.toolsearch);
        toolbar.setTitle("Results");
        setSupportActionBar(toolbar);

        Intent i=getIntent();
        String search_word=i.getExtras().getString("WORD");
        String search_url=base_url+search_word+key;
        RequestQueue q= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, search_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    final JSONArray res = object.getJSONArray("results");
                    ArrayList<String> search_title=new ArrayList<>();
                    ArrayList<String> search_poster=new ArrayList<>();
                    ArrayList<String> search_rate=new ArrayList<>();
                    for (int i = 0; i < res.length(); i++) {
                        JSONObject obj = res.getJSONObject(i);
                        String title = obj.getString("title");
                        String poster_path = obj.getString("poster_path");
                        String rate=obj.getString("vote_average");
                        String id=obj.getString("id");
                        String poster = image_base_url + poster_path;
                        search_title.add(title);
                        search_rate.add(rate);
                        search_poster.add(poster);
                    }
                    SearchAdapter searchAdapter=new SearchAdapter(search_title,search_poster,search_rate,SearchMovies.this);
                    ls.setAdapter(searchAdapter);
                    ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent=new Intent(getApplicationContext(),ViewInfo.class);
                            for (int j=0;j<res.length();j++)
                            {
                                try {
                                    JSONObject obj=res.getJSONObject(i);
                                    String get_title=obj.getString("title");
                                    String get_id=obj.getString("id");
                                    intent.putExtra("movietitle",get_title);
                                    intent.putExtra("movieid",get_id);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            startActivity(intent);

                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        q.add(request);

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
