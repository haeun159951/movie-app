package com.example.movies_haeun_hekim4.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies_haeun_hekim4.databinding.CustomRowLayoutBinding;
import com.example.movies_haeun_hekim4.listeners.OnRowItemClickListener;
import com.example.movies_haeun_hekim4.models.Movie;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ItemViewHolder> {
    private final Context context;
    private final ArrayList<Movie> itemArrayList;
    CustomRowLayoutBinding binding;

    private final OnRowItemClickListener clickListener;

    public MovieAdapter(Context context, ArrayList<Movie> items, OnRowItemClickListener clickListener) {
        this.itemArrayList = items;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(CustomRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Movie currentItem = itemArrayList.get(position);
        holder.bind(context, currentItem, clickListener);
    }

    @Override
    public int getItemCount() {
        Log.d("MovieAdapter", "getItemCount: Number of movies: " + this.itemArrayList.size());
        return this.itemArrayList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        CustomRowLayoutBinding itemBinding;

        public ItemViewHolder(CustomRowLayoutBinding binding) {
            super(binding.getRoot());
            this.itemBinding = binding;
        }

        public void bind(Context context, Movie currentItem, OnRowItemClickListener clickListener) {
            // TODO: Update the UI for the row

            Glide.with(context).load(currentItem.getImageURL()).into(itemBinding.ivMoviePhoto);
            itemBinding.tvMovieName.setText(currentItem.getName());
            itemBinding.tvRating.setText(String.format("%.0f", currentItem.getVoteAverage() * 10) + "%");
            itemBinding.tvReleaseDate.setText("Released: " + currentItem.getReleaseDate());
            itemBinding.tvOverview.setText(currentItem.getOverview());

            // click detection
            itemBinding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.OnItemClickListener(currentItem);
                }
            });
        }
    }
}