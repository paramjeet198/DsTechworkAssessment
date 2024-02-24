package com.example.myapplication.apitask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.apitask.api.ApiClient;
import com.example.myapplication.apitask.api.ApiService;
import com.example.myapplication.apitask.model.User;
import com.example.myapplication.databinding.ActivityApiTaskBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiTaskActivity extends AppCompatActivity {

    private ActivityApiTaskBinding binding;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApiTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new UserAdapter(new ArrayList<>());
        binding.recyclerView.setAdapter(adapter);


        ApiClient client = ApiClient.getInstance();
        ApiService apiService = client.getApiService();
        callApi(apiService);


    }


    public void callApi(ApiService apiService) {
        Call<List<User>> call = apiService.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d("Testing...", "Size: " + response.body().size());

                List<User> users = response.body();
                adapter.setApiData(users);

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("Testing...", "Size: " + t.getMessage());
            }
        });
    }
}