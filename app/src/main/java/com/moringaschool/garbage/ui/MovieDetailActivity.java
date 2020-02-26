package com.moringaschool.garbage.ui;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

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

public class MovieDetailActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    List<Result> movies;
    private MoviePagerAdapter moviePagerAdapter;
    GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        gestureDetector = new GestureDetector(MovieDetailActivity.this, (GestureDetector.OnGestureListener) MovieDetailActivity.this);


        if(getResources().getDisplayMetrics().widthPixels>getResources().getDisplayMetrics().
                heightPixels)
        {
            Toast.makeText(this,"Screen switched to Landscape mode",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
        }




        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Movie Details");

        movies = Parcels.unwrap(getIntent().getParcelableExtra("movies"));
        int startingPosition = getIntent().getIntExtra("position", 0);
        moviePagerAdapter = new MoviePagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,movies);
        mViewPager.setAdapter(moviePagerAdapter);
        mViewPager.setCurrentItem(startingPosition);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
