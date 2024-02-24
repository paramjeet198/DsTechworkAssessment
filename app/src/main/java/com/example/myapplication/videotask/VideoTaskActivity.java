package com.example.myapplication.videotask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityVideoTaskBinding;
import com.example.myapplication.videotask.model.Video;

import java.util.ArrayList;
import java.util.List;

public class VideoTaskActivity extends AppCompatActivity {

    private ActivityVideoTaskBinding binding;
    String url1 = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4";
    String url2 = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<Video> videos = new ArrayList<>();
        videos.add(new Video(url1,"URL 1"));
        videos.add(new Video(url2,"URL 2"));

        VideoAdapter adapter = new VideoAdapter(videos,this);
        binding.recyclerView.setAdapter(adapter);

    }
}