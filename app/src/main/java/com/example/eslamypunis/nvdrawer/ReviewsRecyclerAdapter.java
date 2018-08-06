package com.example.eslamypunis.nvdrawer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by EslamYounis on 7/7/2018.
 */

public class ReviewsRecyclerAdapter extends RecyclerView.Adapter<ReviewsRecyclerAdapter.ViewHolder>{
    ArrayList<String>authors=new ArrayList<>();
    ArrayList<String>reviews=new ArrayList<>();
    Context mContext;

    public ReviewsRecyclerAdapter(ArrayList<String> authors, ArrayList<String> reviews, Context mContext) {
        this.authors = authors;
        this.reviews = reviews;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.reviewsingeitem,parent,false);
        return new  ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


            holder.author_name.setText(authors.get(position));
            holder.review_content.setText(reviews.get(position));
            holder.open_review.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(mContext,ReviewsActivity.class);
                    i.putExtra("AUTHOR",authors.get(position));
                    i.putExtra("REVIEW",reviews.get(position));
                    mContext.startActivity(i);
                }
            });


    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView author_name;
        TextView review_content;
        ImageView open_review;

        public ViewHolder(View itemView) {
            super(itemView);
            author_name=(TextView)itemView.findViewById(R.id.authorName);
            review_content=(TextView)itemView.findViewById(R.id.reviewcontent);
            open_review=(ImageView)itemView.findViewById(R.id.openReviewbt);
        }
    }
}
