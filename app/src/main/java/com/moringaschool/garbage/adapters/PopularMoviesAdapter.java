package com.moringaschool.garbage.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.garbage.R;
import com.moringaschool.garbage.models.Result;
import com.moringaschool.garbage.ui.MovieDetailActivity;
import com.moringaschool.garbage.util.Constants;
import com.squareup.picasso.Picasso;


import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.PopularMovieViewHolder> {

    private Context mContext;
    private List<Result> mPopular;

    public PopularMoviesAdapter(Context mContext, List<Result> mPopular) {
        this.mContext = mContext;
        this.mPopular = mPopular;
    }
    
    @NonNull
    @Override
    public PopularMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_list_item,parent,false);
        return new PopularMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularMovieViewHolder holder, int position) {
        holder.mTitle.setText(mPopular.get(position).getTitle());
//        holder.overview.setText(mPopular.get(position).getOverview());
        Picasso.get().load(Constants.IMAGE_URL + mPopular.get(position).getPosterPath()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mPopular.size();
    }

    class PopularMovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.movieTitle)
        TextView mTitle;
//        @BindView(R.id.overView) TextView overview;
//        @BindView(R.id.voteAverage) TextView average;
        @BindView(R.id.movieImage)
        ImageView mImageView;
        public PopularMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();

//            Toast.makeText(mContext, "clicked:"+itemPosition, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("movies", Parcels.wrap(mPopular));
            mContext.startActivity(intent);
        }
    }
}
