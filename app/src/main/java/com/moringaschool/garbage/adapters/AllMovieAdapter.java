package com.moringaschool.garbage.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

public class AllMovieAdapter extends RecyclerView.Adapter<AllMovieAdapter.AllMovieViewHolder> {

    private Context mContext;
    private List<Result> mPopular;

    public AllMovieAdapter(Context mContext, List<Result> mPopular) {
        this.mContext = mContext;
        this.mPopular = mPopular;
    }

    @NonNull
    @Override
    public AllMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_item, parent, false);
        return new AllMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMovieViewHolder holder, int position) {
        holder.mOverview.setText(mPopular.get(position).getOverview());
        Picasso.get().load(Constants.IMAGE_URL + mPopular.get(position).getPosterPath()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mPopular.size();
    }

    class AllMovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.movieOverview)
        TextView mOverview;
        @BindView(R.id.movie_new_image)
        ImageView mImageView;
        public AllMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("movies", Parcels.wrap(mPopular));
            mContext.startActivity(intent);
        }
    }
}
