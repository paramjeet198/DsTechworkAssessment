package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.api.ApiClient;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate( getLayoutInflater());
        setContentView(binding.getRoot());


        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

//        List<User> users = new ArrayList<>();
//        users.add(new User("Paramjeet","param199","paramjeet@gmail.com","123456789","fitolympia"));
//        users.add(new User("ABcd","param199","paramjeet@gmail.com","123456789","fitolympia"));

         adapter = new UserAdapter(new ArrayList<>());
        binding.recyclerView.setAdapter(adapter);


        ApiClient client = new ApiClient();
        ApiService apiService = client.apiService;
        callApi(apiService);

    }


    public void callApi(ApiService apiService){
        Call<List<User>> call = apiService.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d("Testing...","Size: " + response.body().size());

                List<User> users = response.body();
                adapter.setApiData(users);

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("Testing...","Size: " + t.getMessage());
            }
        });
    }

}