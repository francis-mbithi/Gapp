package com.moringaschool.garbage.ui.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.garbage.R;
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
public class FragmentPopularMovies extends Fragment {


    private RecyclerView recyclerView;
    private PopularMoviesAdapter moviesAdapter;
    private MenuItem menuItem;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public FragmentPopularMovies() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_popular_movies, container, false);
        recyclerView = rootView.findViewById(R.id.movieRecyclerView);
        setHasOptionsMenu(true);
        getPopularMovies();
        return  rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();
    }

    private void addToSharedPreference(String query){
        mEditor.putString(Constants.MOVIE_SEARCHED, query).apply();
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
        moviesAdapter = new PopularMoviesAdapter(getContext(), movies);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(moviesAdapter);
    }

    //search for movie
    public void searchMovie(String movie){
        MovieDBAPI client = RetrofitClient.getClient().create(MovieDBAPI.class);

        Call<Popular> call = client.searchMovie(Constants.MOVIE_DB_API, movie);
        call.enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(Call<Popular> call, Response<Popular> response) {
                try {
                    List<Result> movies = response.body().getResults();
                        getCurrentMovies(movies);
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<Popular> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menuItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreference(query);
                searchMovie(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
}
