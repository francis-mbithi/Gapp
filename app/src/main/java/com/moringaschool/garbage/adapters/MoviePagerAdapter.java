package com.moringaschool.garbage.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.garbage.models.Result;
import com.moringaschool.garbage.ui.fragments.MovieDetailsFragement;

import java.util.List;


public class MoviePagerAdapter extends FragmentPagerAdapter {

    private List<Result> movies;

    public MoviePagerAdapter(@NonNull FragmentManager fm, int behavior, List<Result> movies) {
        super(fm, behavior);
        this.movies = movies;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return MovieDetailsFragement.newInstance(movies.get(position));
    }

    @Override
    public int getCount() {
        return movies.size();
    }
}
