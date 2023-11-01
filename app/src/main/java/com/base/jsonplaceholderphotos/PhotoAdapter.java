package com.base.jsonplaceholderphotos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>{
    private List<PhotoEntity> photos = new ArrayList<>();
    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public PhotoViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tv_title);
            thumbnail = view.findViewById(R.id.iv_photo);
        }
    }
    @Override
    public PhotoAdapter.PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PhotoAdapter.PhotoViewHolder holder, int position) {
        PhotoEntity photo = photos.get(position);
        holder.title.setText(photo.title);
        Picasso.get()
                .load(photo.thumbnailUrl)
                .error(R.drawable.ic_launcher_background)
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void setPhotos(List<PhotoEntity> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }
}
