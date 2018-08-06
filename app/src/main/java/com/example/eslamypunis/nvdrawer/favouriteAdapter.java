package com.example.eslamypunis.nvdrawer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by eslamyounis34 on 10/6/2017.
 */

public class favouriteAdapter extends BaseAdapter {
    ArrayList<String>moviesTitels;
    ArrayList<String>moviesPosters;
    ArrayList<String>moviesRates;

    Activity ac;

    public favouriteAdapter(ArrayList<String> moviesTitels, ArrayList<String> moviesPosters, ArrayList<String> moviesRates,Activity ac) {
        this.moviesTitels = moviesTitels;
        this.moviesPosters = moviesPosters;
        this.moviesRates=moviesRates;
        this.ac = ac;
    }

    @Override
    public int getCount() {
        return moviesTitels.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
     LayoutInflater  inflater=ac.getLayoutInflater();
        View v=inflater.inflate(R.layout.favouritelistrow,null);
        TextView tx=(TextView)v.findViewById(R.id.favourite_movie_text);
        TextView tx2=(TextView)v.findViewById(R.id.favsearchrate);
        tx2.setText(moviesRates.get(position));
        tx.setText(moviesTitels.get(position));
        ImageView im=(ImageView)v.findViewById(R.id.favourite_movie_image);
        Picasso.with(ac).load(moviesPosters.get(position)).into(im);
        return v;
    }
}
