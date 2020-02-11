package com.moringaschool.garbage.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.garbage.R;
import com.moringaschool.garbage.adapters.AllMovieAdapter;
import com.moringaschool.garbage.adapters.NowPlayingMoviesAdapter;
import com.moringaschool.garbage.adapters.PopularMoviesAdapter;
import com.moringaschool.garbage.models.Popular;
import com.moringaschool.garbage.models.Result;
import com.moringaschool.garbage.services.MovieDBAPI;
import com.moringaschool.garbage.services.RetrofitClient;
import com.moringaschool.garbage.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllMoviesFragment extends Fragment {

//    private RecyclerView recyclerView;
    private NowPlayingMoviesAdapter nowPlayingMoviesAdapter;
    private RecyclerView recyclerView;
    private PopularMoviesAdapter moviesAdapter;
    private AllMovieAdapter adapter;

    public AllMoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_movies, container, false);
        recyclerView = rootView.findViewById(R.id.all_movies_recyclerview);
//        getPlayingNow();
        getPopularMovies();
        return  rootView;
    }

    public void getPopularMovies(){
        MovieDBAPI client = RetrofitClient.getClient().create(MovieDBAPI.class);
        Call<Popular> call = client.getPopularMovies(Constants.MOVIE_DB_API);
        call.enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(Call<Popular> call, Response<Popular> response) {
                List<Result> movies = response.body().getResults();
                getCurrentMovies(movies);
//                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Popular> call, Throwable t) {

            }
        });
    }


    public void getCurrentMovies(List<Result> movies){
        adapter = new AllMovieAdapter(getContext(), movies);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
