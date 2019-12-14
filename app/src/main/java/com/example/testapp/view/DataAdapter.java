package com.example.testapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testapp.R;
import com.example.testapp.model.Row;

import java.util.ArrayList;
import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder>{
     List<Row> networkData;
     Context context;

    public DataAdapter(ArrayList<Row> networkData, Context context) {
        this.networkData = networkData;
        this.context = context;
    }

    @NonNull
    @Override
    public DataAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.card_item,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.DataViewHolder holder, int position) {

                holder.Title.setText(networkData.get(position).getTitle());
                holder.Description.setText(networkData.get(position).getDescription());
                Glide.with(context).load(networkData.get(position).getImageHref())
                        .into(holder.ImageIcon);

    }

    @Override
    public int getItemCount() {
    return networkData.size();
    }
    class DataViewHolder extends  RecyclerView.ViewHolder{
        ImageView ImageIcon;
        TextView Title;
        TextView Description;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageIcon=itemView.findViewById(R.id.pic);
            Title=itemView.findViewById(R.id.title);
            Description=itemView.findViewById(R.id.description);
        }
    }
}
