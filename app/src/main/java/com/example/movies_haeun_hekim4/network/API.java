package com.example.movies_haeun_hekim4.network;

import com.example.movies_haeun_hekim4.models.MovieResponseObject;


import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    //TODO: base url of the api
    String BASE_URL =  "https://api.themoviedb.org/3/movie/";

    @GET("now_playing?api_key=056dce9780e088e6fc94fe472e577d09&language=en-US&page=1&region=CA")
    Call<MovieResponseObject> getAllMovies();
}