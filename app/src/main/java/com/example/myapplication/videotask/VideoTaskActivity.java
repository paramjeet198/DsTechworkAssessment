package com.example.myapplication.videotask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Size;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityVideoTaskBinding;
import com.example.myapplication.videotask.model.Video;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class VideoTaskActivity extends AppCompatActivity {
    public static final String TAG = "VideoTaskActivity";

    private ActivityVideoTaskBinding binding;
    String url1 = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4";
    String url2 = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] permissions = new String[]{Manifest.permission.READ_MEDIA_VIDEO};
        ActivityCompat.requestPermissions(this, permissions, 1);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

//        List<VideoInfo> videoInfos = new ArrayList<>();
//        videoInfos.add(new VideoInfo(url1, "URL 1"));
//        videoInfos.add(new VideoInfo(url2, "URL 2"));

        List<Video> videos = scanStorageForVideos();

        Log.e(TAG, "videoInfos: " + videos.size());
        VideoAdapter adapter = new VideoAdapter(videos, this);
        binding.recyclerView.setAdapter(adapter);


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                scanStorageForVideos();
            } else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private List<Video> scanStorageForVideos() {
        List<Video> videoList = new ArrayList<>();

        Uri collection;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            collection = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }

        String[] projection = new String[]{
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.SIZE
        };
        String selection = MediaStore.Video.Media.DURATION + " >= ?";
        String[] selectionArgs = new String[]{String.valueOf(TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES))
        };
        String sortOrder = MediaStore.Video.Media.DISPLAY_NAME + " ASC";

        //try-with-resource
        try (Cursor cursor = getApplicationContext().getContentResolver().query(collection, projection, selection, selectionArgs, sortOrder)) {
            // Cache column indices.
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            int nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
            int durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION);
            int sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);

            while (cursor.moveToNext()) {
                // Get values of columns for a given video.
                long id = cursor.getLong(idColumn);
                String name = cursor.getString(nameColumn);
                int duration = cursor.getInt(durationColumn);
                int size = cursor.getInt(sizeColumn);

                Uri contentUri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id);

                // Stores column values and the contentUri in a local object that represents the media file.
                Bitmap thumbnail = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    try {
                        thumbnail = getApplicationContext().getContentResolver().loadThumbnail(contentUri, new Size(640, 480), null);
                    } catch (IOException e) {
                        Log.e(TAG, e.toString());
                    }
                }
                videoList.add(new Video(contentUri.toString(), name, duration, size, thumbnail));
            }
        }

        return videoList;
    }

}