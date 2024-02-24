package com.example.myapplication.videotask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.apitask.model.User;
import com.example.myapplication.databinding.UserItemBinding;
import com.example.myapplication.databinding.VideoItemBinding;
import com.example.myapplication.videotask.model.Video;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<Video> videos = new ArrayList<>();
    private Context context;

    public VideoAdapter(List<Video> users, Context conext) {
        this.videos = users;
        this.context = conext;
    }

    public void setData(List<Video> users) {
        this.videos = users;
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
        Video item = videos.get(position);
        holder.binding.videoTitle.setText(item.getTitle());

        setupPlayer(item.getUrl(), context, holder.binding.playerview);

    }

    void setupPlayer(String url, Context context, PlayerView playerView) {
        ExoPlayer player = new ExoPlayer.Builder(context).build();
        playerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(url);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.getPlayWhenReady();
    }

    public void releasePlayer(ExoPlayer player) {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public void onViewRecycled(@NonNull VideoViewHolder holder) {

        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        VideoItemBinding binding;

        public VideoViewHolder(@NonNull VideoItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
