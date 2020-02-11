package com.moringaschool.garbage.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.moringaschool.garbage.R;
import com.moringaschool.garbage.adapters.MoviePagerAdapter;
import com.moringaschool.garbage.models.Result;

import org.parceler.Parcels;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    List<Result> movies;
    private MoviePagerAdapter moviePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Movie Details");

        movies = Parcels.unwrap(getIntent().getParcelableExtra("movies"));
        int startingPosition = getIntent().getIntExtra("position", 0);
        moviePagerAdapter = new MoviePagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,movies);
        mViewPager.setAdapter(moviePagerAdapter);
        mViewPager.setCurrentItem(startingPosition);
    }
}
