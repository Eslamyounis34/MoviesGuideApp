package com.example.eslamypunis.nvdrawer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlaying extends Fragment {

    ArrayList<String> movie_name=new ArrayList<>();
    ArrayList<String>movie_poster=new ArrayList<>();
    String base_url;
    GridView gr;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=inflater.inflate(R.layout.fragment_now_playing, container, false);
        base_url="https://image.tmdb.org/t/p/w500/";
        gr=(GridView)v.findViewById(R.id.thirdG);
        RequestQueue q= Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest request=new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/now_playing?api_key=e76112b72c6c245384a5ecfd814a3ec2&language=en-US&page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    final JSONArray pop=object.getJSONArray("results");
                    for (int i=0;i<pop.length();i++)
                    {
                        JSONObject data=pop.getJSONObject(i);
                        String title =data.getString("title");
                        String overview =data.getString("overview");
                        String poster_path=data.getString("poster_path");
                        String poster=base_url+poster_path;
                        movie_poster.add(poster);
                        movie_name.add(title);
                    }
                    custom_adapter adp=new custom_adapter(movie_name,movie_poster,getActivity());
                    gr.setAdapter(adp);
                    gr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent=new Intent(getActivity(),ViewInfo.class);
                            for(int j=0;j<pop.length();j++)
                            {
                                try {
                                    JSONObject obj=pop.getJSONObject(i);
                                    String getTitle=obj.getString("title");
                                    intent.putExtra("movietitle",getTitle);
                                    String getID=obj.getString("id");
                                    intent.putExtra("movieid",getID);

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
        return  v;


    }

}
