package com.example.eslamypunis.nvdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Eslam Younis on 6/26/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    ArrayList<String> castImageUrl=new ArrayList<>();
    ArrayList<String> castNames=new ArrayList<>();
    Context context;

    public RecyclerViewAdapter(ArrayList<String> castImageUrl, ArrayList<String> castNames,Context context) {
        this.castImageUrl = castImageUrl;
        this.castNames=castNames;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem,parent,false);

        return new ViewHolder(v);    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(castImageUrl.get(position)).into(holder.image);
        holder.text.setText(castNames.get(position));

    }

    @Override
    public int getItemCount() {
        return castImageUrl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        CircleImageView image;
        TextView text;


        public ViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.castimage);
            text=itemView.findViewById(R.id.castname);
        }
    }
}
