package com.moringaschool.garbage.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moringaschool.garbage.R;
import com.moringaschool.garbage.models.Result;
import com.moringaschool.garbage.util.Constants;
import com.squareup.picasso.Picasso;


import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragement extends Fragment {

    @BindView(R.id.movieImage)
    ImageView mImage;
    @BindView(R.id.movieTitle)
    TextView mTitle;
    @BindView(R.id.movieDescription) TextView mDescription;
    private Result movies;

    public MovieDetailsFragement() {
        // Required empty public constructor
    }

    public static MovieDetailsFragement newInstance(Result movies){
        MovieDetailsFragement movieDetailsFragement = new MovieDetailsFragement();
        Bundle args = new Bundle();
        args.putParcelable("movies", Parcels.wrap(movies));
        movieDetailsFragement.setArguments(args);
        return movieDetailsFragement;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movies = Parcels.unwrap(getArguments().getParcelable("movies"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(Constants.IMAGE_URL + movies.getPosterPath()).into(mImage);
        mTitle.setText(movies.getTitle());
        mDescription.setText(movies.getOverview());

        return view;
    }

}
