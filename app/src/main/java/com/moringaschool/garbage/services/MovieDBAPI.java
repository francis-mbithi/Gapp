package com.moringaschool.garbage.services;



import com.moringaschool.garbage.models.Popular;
import com.moringaschool.garbage.models.Upcoming;
import com.moringaschool.garbage.models.now_playing_movies.NowPlaying;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDBAPI {
    @GET("movie/popular")
    Call<Popular> getPopularMovies(@Query("api_key") String myApiKey);

    @GET("movie/upcoming")
    Call<Upcoming> getUpcomingMovies(@Query("api_key") String myApiKey);

    @GET("movie/now_playing")
    Call<NowPlaying> getNowPlayingMovies(@Query("api_key") String myApiKey);

    @GET("search/movie")
    Call<Popular> searchMovie(
            @Query("api_key") String myApiKey,
            @Query("query") String movieName
    );
}