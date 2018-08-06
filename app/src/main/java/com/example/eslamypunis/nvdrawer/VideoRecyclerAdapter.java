package com.example.eslamypunis.nvdrawer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by EslamYounis on 7/3/2018.
 */

public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.ViewHolder> {
    ArrayList<String> videoImageUrl=new ArrayList<>();
    ArrayList<String> videoNames=new ArrayList<>();
    ArrayList<String> trailers=new ArrayList<>();
    Context context;

    public VideoRecyclerAdapter(ArrayList<String> videoImageUrl, ArrayList<String> videoNames,  ArrayList<String>trailers,Context context) {
        this.videoImageUrl = videoImageUrl;
        this.videoNames = videoNames;
        this.trailers=trailers;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclevideoitem,parent,false);

        return new ViewHolder(v);    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Picasso.with(context).load(videoImageUrl.get(position)).into(holder.videoImage);
        holder.videoText.setText(videoNames.get(position));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, videoNames.get(position), Toast.LENGTH_SHORT).show();
                Intent send=new Intent(Intent.ACTION_VIEW, Uri.parse(trailers.get(position)));
                context.startActivity(send);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoImageUrl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView videoImage;
        TextView videoText;
        LinearLayout layout;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            videoImage=itemView.findViewById(R.id.videoimage);
            videoText=itemView.findViewById(R.id.videoname);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.parent);
        }
    }
}
