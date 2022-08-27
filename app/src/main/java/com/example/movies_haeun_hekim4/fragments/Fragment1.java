package com.example.movies_haeun_hekim4.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.movies_haeun_hekim4.R;
import com.example.movies_haeun_hekim4.adapter.MovieAdapter;
import com.example.movies_haeun_hekim4.databinding.Fragment1Binding;
import com.example.movies_haeun_hekim4.db.MyDatabase;
import com.example.movies_haeun_hekim4.listeners.OnRowItemClickListener;
import com.example.movies_haeun_hekim4.models.Movie;
import com.example.movies_haeun_hekim4.models.MovieResponseObject;
import com.example.movies_haeun_hekim4.models.Purchase;
import com.example.movies_haeun_hekim4.network.API;
import com.example.movies_haeun_hekim4.network.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment1 extends Fragment implements OnRowItemClickListener {
    // binding
    private Fragment1Binding binding;
    // adapter
    private MovieAdapter adapter;
    // api
    private API api;
    // recyclerview
    private final ArrayList<Movie> movieList = new ArrayList<>();
    // the list of movies returned by the apI
    private List<Movie> moviesFromAPI = new ArrayList<>();
    //database
    private MyDatabase db;
    // purchase
    private Purchase currPurchase;

    // constructor
    public Fragment1() {
        super(R.layout.fragment_1);
    }

    // lifecycle functions - required for configuring view bindings
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Fragment1Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        adapter = new MovieAdapter(view.getContext(), movieList, this::OnItemClickListener);
        binding.rvMovies.setAdapter(adapter);
        binding.rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvMovies.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        //database
        this.db = MyDatabase.getDatabase(getContext());
        this.api = RetrofitClient.getInstance().getAPI();
        Call<MovieResponseObject> request = this.api.getAllMovies();
        request.enqueue(new Callback<MovieResponseObject>() {
            @Override
            public void onResponse(Call<MovieResponseObject> call, Response<MovieResponseObject> response) {
                if (response.isSuccessful() == false) {
                    Log.d("TAG", "Response error: " + response.code());
                    return;
                }
                MovieResponseObject obj = response.body();
                moviesFromAPI = obj.getResults();
                movieList.clear();
                movieList.addAll(moviesFromAPI);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResponseObject> call, Throwable t) {
                Log.d("ABC", "The request failed" + t.getMessage());
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void OnItemClickListener(Movie currentItem) {

        String currName = currentItem.getName();
        int currId = currentItem.getId();

        currPurchase = new Purchase(currName, currId, 1);
        if (this.db.purchaseDAO().getTicketById(currId) == null) {
            db.purchaseDAO().insertTicket(currPurchase);
        } else {
            db.purchaseDAO().updateQuantity(currId);
        }

        Snackbar.make(binding.getRoot(), "Ticket Purchased", Snackbar.LENGTH_SHORT).show();
    }
}