package com.example.movies_haeun_hekim4.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="purchase_table")
public class Purchase {
    // properties
    private String movieTitle;
    private int movieId;
    private int quantity;
    @PrimaryKey(autoGenerate = true)
    private int id;

    public Purchase(String movieTitle, int movieId, int quantity) {
        this.movieTitle = movieTitle;
        this.movieId = movieId;
        this.quantity = quantity;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
