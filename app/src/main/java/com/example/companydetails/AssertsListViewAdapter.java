package com.example.companydetails;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AssertsListViewAdapter extends RecyclerView.Adapter<AssertsListViewAdapter.ViewHolder> {

    public ArrayList<AssertsData> listdata;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView name;
        public TextView status;
        private ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.status = (TextView) itemView.findViewById(R.id.status);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.listx_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AssertsListViewAdapter.ViewHolder myViewHolder, int position) {
        myViewHolder.status.setText(listdata.get(position).getStatus());
        myViewHolder.name.setText(listdata.get(position).getName());
        //myViewHolder.imageView.setImageResource(listdata[position].getImgURL());
    }


    @Override
    public int getItemCount() {
        if(listdata !=  null)
            return listdata.size();
        else
            return 0;
    }

}
