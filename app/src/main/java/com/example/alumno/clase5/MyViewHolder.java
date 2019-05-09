package com.example.alumno.clase5;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

    private MyOnItemClick listener;
    public TextView tvTitle;
    public TextView tvDescription;
    public TextView tvLink;
    public ImageView viewImage;
    private int position;

    public MyViewHolder(View v,MyOnItemClick listener){
        super(v);
        v.setOnClickListener(this);
        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        tvDescription = (TextView) v.findViewById(R.id.tvDescription);
        tvLink = (TextView) v.findViewById(R.id.tvLink);
        viewImage = (ImageView) v.findViewById(R.id.viewImage);
        this.listener = listener;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(position);

    }
}