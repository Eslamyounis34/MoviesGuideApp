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
 * Created by Eslam Ypunis on 07/01/2018.
 */

public class custom_adapter extends BaseAdapter {
    ArrayList<String> movies_name;
    ArrayList<String>movies_poster;
    Activity ac;

    public custom_adapter(ArrayList<String> movies_name, ArrayList<String> movies_poster, Activity ac) {
        this.movies_name = movies_name;
        this.movies_poster = movies_poster;
        this.ac = ac;
    }
    @Override
    public int getCount() {
        return movies_name.size();
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
        View v=inflater.inflate(R.layout.row,null);
        TextView tx=(TextView)v.findViewById(R.id.rowtext);
        ImageView im=(ImageView)v.findViewById(R.id.rowimage);
        tx.setText(movies_name.get(i));
        Picasso.with(ac).load(movies_poster.get(i)).into(im);
        return v;    }
}
