package com.example.myapplication.videotask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.databinding.VideoItemBinding;
import com.example.myapplication.videotask.model.Video;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<Video> videos = new ArrayList<>();
    private Context context;
    private Gson gson;

    public VideoAdapter(List<Video> users, Context conext) {
        this.videos = users;
        this.context = conext;
        gson = new Gson();
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
        holder.binding.videoTitle.setText(item.getName());
        holder.binding.videoDuration.setText(convertMillisToMinSec(item.getDuration()));

        Glide.with(context)
                .load(item.getThumbnail())
                .into(holder.binding.thumbnail);
        Log.d("VideoUri:", item.getUri().toString());

        holder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(context, PlayVideoActivity.class);
            intent.putExtra("videoListJson", gson.toJson(videos.subList(position, videos.size())));
            context.startActivity(intent);
        });

//        setupPlayer(item.getUri(), context, holder.binding.playerview);

    }

    @SuppressLint("DefaultLocale")
    public static String convertMillisToMinSec(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        seconds %= 60;

        String format = "%d:%02d";
        return String.format(format, minutes, seconds);
    }


    @Override
    public int getItemCount() {
        return videos.size();
//        return 10;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        VideoItemBinding binding;

        public VideoViewHolder(@NonNull VideoItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
