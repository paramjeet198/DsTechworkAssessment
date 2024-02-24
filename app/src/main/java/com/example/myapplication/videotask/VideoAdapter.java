package com.example.myapplication.videotask;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.apitask.model.User;
import com.example.myapplication.databinding.UserItemBinding;
import com.example.myapplication.databinding.VideoItemBinding;
import com.example.myapplication.videotask.model.Video;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<Video> users = new ArrayList<>();

    public VideoAdapter(List<Video> users) {
        this.users = users;
    }

    public void setData(List<Video> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VideoItemBinding binding = VideoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new VideoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Video item = users.get(position);
        holder.binding.videoTitle.setText(item.getTitle());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        VideoItemBinding binding;

        public VideoViewHolder(@NonNull VideoItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
