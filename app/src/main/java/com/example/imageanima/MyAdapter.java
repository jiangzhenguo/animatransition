package com.example.imageanima;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by jhon on 2016/3/27.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public ArrayList<String> list;
    public Context context;
    private LayoutInflater mInflater;
    public MyAdapter(Context context,ArrayList<String> list){
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position)).into(((MyViewHolder)holder).image_pic);
        ((MyViewHolder) holder).image_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VIewPagerActivity.launch(context,list,position,((MyViewHolder) holder).image_pic);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image_pic;
        public MyViewHolder(View itemView) {
            super(itemView);
            image_pic = (ImageView)itemView.findViewById(R.id.image_pic);
        }
    }
}
