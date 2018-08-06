package com.example.eslamypunis.nvdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by EslamYounis on 7/6/2018.
 */

public class SearchAdapter extends BaseAdapter {
    ArrayList<String> title;
    ArrayList<String>posterpath;
    ArrayList<String>rate;
    Context mContext;

    public SearchAdapter(ArrayList<String> title, ArrayList<String> posterpath, ArrayList<String> rate, Context mContext) {
        this.title = title;
        this.posterpath = posterpath;
        this.rate = rate;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return title.size();
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
        View v= LayoutInflater.from(mContext).inflate(R.layout.searchrow,null);
        TextView tx=(TextView)v.findViewById(R.id.searchText);
        ImageView im=(ImageView)v.findViewById(R.id.search_image);
        TextView tx2=(TextView)v.findViewById(R.id.searchrate);
        tx2.setText(rate.get(i));
        tx.setText(title.get(i));
        Picasso.with(mContext).load(posterpath.get(i)).into(im);
        return v;
    }
}
