package com.huebeiro.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huebeiro.stockontrol.R;

import java.util.ArrayList;

/**
 * Author: adilson
 * Date: 19/06/17
 */

public class MenuAdapter
    extends RecyclerView.Adapter<MenuAdapter.StepsHolder>  {

    private View.OnClickListener mOnClickListener;
    private ArrayList<MenuItem> mItems;

    public MenuAdapter(ArrayList<MenuItem> items, View.OnClickListener onClickListener){
        mItems = items;
        mOnClickListener = onClickListener;
    }


    @Override
    public StepsHolder onCreateViewHolder(ViewGroup parent,
                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_menu, parent, false);
        view.setOnClickListener(mOnClickListener);
        return new StepsHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsHolder holder, int position) {
        MenuItem item = mItems.get(position);
        holder.textLabel.setText(item.getLabel());
        holder.imageItem.setImageResource(item.getIconId());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class StepsHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {

        TextView textLabel;
        ImageView imageItem;

        public StepsHolder(View itemView){
            super(itemView);
            textLabel = (TextView) itemView.findViewById(R.id.textLabel);
            imageItem = (ImageView) itemView.findViewById(R.id.imageMenu);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
