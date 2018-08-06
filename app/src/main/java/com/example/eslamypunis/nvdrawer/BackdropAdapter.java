package com.example.eslamypunis.nvdrawer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by EslamYounis on 7/5/2018.
 */

public class BackdropAdapter  extends RecyclerView.Adapter<BackdropAdapter.viewHolder>{
    ArrayList<String> backdropurls=new ArrayList<>();
    Context mContext;

    public BackdropAdapter(ArrayList<String> backdropurls, Context mContext) {
        this.backdropurls = backdropurls;
        this.mContext = mContext;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.backdropitem,parent,false);

        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, final int position) {
        Picasso.with(mContext).load(backdropurls.get(position)).into(holder.image);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mContext,FullScreenImage.class);
                i.putExtra("ImageUri",backdropurls.get(position));
                mContext.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return backdropurls.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        RelativeLayout relativeLayout;
        public viewHolder(View itemView) {
            super(itemView);
            image =itemView.findViewById(R.id.backdropimage);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.backdroprelative);

        }
    }
}
