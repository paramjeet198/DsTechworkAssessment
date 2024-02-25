package com.example.myapplication.videotask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import android.content.Context;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityPlayVideoBinding;
import com.example.myapplication.videotask.model.Video;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PlayVideoActivity extends AppCompatActivity {

    public static final String TAG = "PlayVideoActivity";
    private ActivityPlayVideoBinding binding;
    private ExoPlayer player;
    List<Video> videos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Log.d(TAG, "onCreate");

        Gson gson = new Gson();
        String jsonString = getIntent().getStringExtra("videoListJson");

        Type listType = new TypeToken<List<Video>>() {
        }.getType();
        videos = gson.fromJson(jsonString, listType);

        initPlayer();
    }

    void initPlayer() {
        Log.d(TAG, "initPlayer: " + player);

        if (player == null) {
            player = new ExoPlayer.Builder(this).build();
            binding.playerview.setPlayer(player);
            setupPlayer(videos);
        }

    }

    void setupPlayer(List<Video> videos) {
        List<MediaItem> mediaItems = new ArrayList<>();
        for (Video video : videos) {
            MediaItem mediaItem = MediaItem.fromUri(Uri.parse(video.getUri()));
            mediaItems.add(mediaItem);
        }
//        binding.title.setText("Title: "+ albumTitle);
        player.setMediaItems(mediaItems);
        player.prepare();
        player.getPlayWhenReady();
    }

    public void releasePlayer(ExoPlayer player) {
        if (player != null) {
//            player.removeListener();
            player.release();
            player = null;
        }

    }

    @Override
    protected void onStart() {
        initPlayer();
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        initPlayer();
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        releasePlayer(player);
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        releasePlayer(player);
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        releasePlayer(player);
        super.onDestroy();
    }
}