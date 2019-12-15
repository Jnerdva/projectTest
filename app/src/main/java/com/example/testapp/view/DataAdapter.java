package com.example.testapp.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.testapp.R;
import com.example.testapp.model.Row;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder>{
     private List<Row> networkData;
     private Context context;
     private RequestOptions requestOptions;

    public DataAdapter(ArrayList<Row> networkData, Context context) {
        this.networkData = networkData;
        this.context = context;
        requestOptions= new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
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
                        .addListener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                Log.d("image","loading Failed");
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        })
                        .apply(requestOptions)
                        .override(50,50)
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
