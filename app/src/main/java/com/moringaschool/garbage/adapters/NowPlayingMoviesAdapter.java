package com.moringaschool.garbage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.garbage.R;
import com.moringaschool.garbage.models.now_playing_movies.Results;
import com.moringaschool.garbage.util.Constants;
import com.squareup.picasso.Picasso;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NowPlayingMoviesAdapter extends RecyclerView.Adapter<NowPlayingMoviesAdapter.PlayingNowViewHolder> {

    private Context context;
    private List<Results> playingNowList;

    public NowPlayingMoviesAdapter(Context context, List<Results> playingNowList) {
        this.context = context;
        this.playingNowList = playingNowList;
    }

    @NonNull
    @Override
    public PlayingNowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_item,parent, false);
        return new PlayingNowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayingNowViewHolder holder, int position) {
        Picasso.get().load(Constants.IMAGE_URL + playingNowList.get(position).getPosterPath()).into(holder.imageView);
        holder.textView.setText(playingNowList.get(position).getOriginalTitle());


    }

    @Override
    public int getItemCount() {
        return playingNowList.size();
    }

    class PlayingNowViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.movie_new_image)
        ImageView imageView;
        @BindView(R.id.movieOverview)
        TextView textView;
        public PlayingNowViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
