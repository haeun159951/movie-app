package com.example.movies_haeun_hekim4.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

//   {
//           "adult": false,
//           "backdrop_path": "/p1F51Lvj3sMopG948F5HsBbl43C.jpg",
//           "genre_ids": [
//           28,
//           12,
//           14
//           ],
//           "id": 616037,
//           "original_title": "Thor: Love and Thunder",
//           "overview": "After his retirement is interrupted by Gorr the God Butcher, a galactic killer who seeks the extinction of the gods, Thor enlists the help of King Valkyrie, Korg, and ex-girlfriend Jane Foster, who now inexplicably wields Mjolnir as the Mighty Thor. Together they embark upon a harrowing cosmic adventure to uncover the mystery of the God Butcher’s vengeance and stop him before it’s too late.",
//           "poster_path": "/pIkRyD18kl4FhoCNQuWxWu5cBLM.jpg",
//           "release_date": "2022-07-08",
//           "title": "Thor: Love and Thunder",
//           "vote_average": 6.7,
//           },
//4. Each row of the RecyclerView must show the movie image, name, release date,
//        average voter rating (as a percent), overview, and a BUY TICKETS button.
public class Movie implements Serializable {
    //properties
    @SerializedName("backdrop_path")
    private String imageURL;
    @SerializedName("title")
    private String name;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("vote_average")
    private double voteAverage;
    private String overview;
    private int id;

    //getter
    public int getId() {
        return id;
    }

    public String getImageURL() {
        String url = "https://image.tmdb.org/t/p/w500" + imageURL;
        return url;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imageURL='" + imageURL + '\'' +
                ", name='" + name + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", voteAverage=" + voteAverage +
                ", overview='" + overview + '\'' +
                ", id=" + id +
                '}';
    }
}
