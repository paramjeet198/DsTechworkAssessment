package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.apitask.ApiTaskActivity;
import com.example.myapplication.apitask.api.ApiClient;
import com.example.myapplication.apitask.api.ApiService;
import com.example.myapplication.apitask.UserAdapter;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.apitask.model.User;
import com.example.myapplication.videotask.VideoTaskActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.apiTask.setOnClickListener(v -> {
            startActivity(new Intent(this, ApiTaskActivity.class));
        });


        binding.videoTask.setOnClickListener(v -> {
            startActivity(new Intent(this, VideoTaskActivity.class));
        });

    }


}