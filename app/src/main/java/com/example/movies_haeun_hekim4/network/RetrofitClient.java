package com.example.movies_haeun_hekim4.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // an instance of the api
    private API api;

    // constructor
    public RetrofitClient() {
        // instantiate a  concrete instance of the api interface
        // Retrofit to create the concrete instance of the api interface

        // Retrofit provides a Builder pattern
        // create an instance of the parser
        Gson gson = new GsonBuilder().create();

        // create an instance of retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // create a concrete instance of the api interface
        this.api = retrofit.create(API.class);

    }

    // getter for returning the api
    public API getAPI() {
        return this.api;
    }

    // turn this class into a singleton
    private static RetrofitClient instance = null;
    public static synchronized RetrofitClient getInstance(){
        if ( instance == null){
            instance = new RetrofitClient();
        }
        return instance;
    }

}