package com.example.eslamypunis.nvdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by EslamYounis on 7/7/2018.
 */

public class NonReviewRecyclerAdapter extends RecyclerView.Adapter<NonReviewRecyclerAdapter.ViewHolder> {
    Context mContext;

    public NonReviewRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.noreviewrecycleview,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tx.setText("No Reviews Yet");

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tx;
        public ViewHolder(View itemView) {
            super(itemView);
            tx=(TextView)itemView.findViewById(R.id.norevtext);
        }
    }
}
