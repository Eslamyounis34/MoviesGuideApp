package com.example.eslamypunis.nvdrawer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Eslam Yuonis on 5/4/2018.
 */

public class CustomAdapter  extends BaseAdapter {
    Activity ac;
    ArrayList<String>movieCast;

    public CustomAdapter(Activity ac, ArrayList<String> movieCast) {
        this.ac = ac;
        this.movieCast = movieCast;
    }

    @Override
    public int getCount() {
        return movieCast.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=ac.getLayoutInflater();
        View v=inflater.inflate(R.layout.castrow,null);
        ImageView imageView=(ImageView)v.findViewById(R.id.castimage);
        Picasso.with(ac).load(movieCast.get(i)).into(imageView);

        return v;
    }
}
