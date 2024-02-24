package com.example.myapplication.api;

import com.example.myapplication.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/users")
    Call<List<User>> getUsers();
}
