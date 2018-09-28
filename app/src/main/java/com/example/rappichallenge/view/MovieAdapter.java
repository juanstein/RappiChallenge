package com.example.rappichallenge.view;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rappichallenge.R;
import com.example.rappichallenge.model.Result;

import java.util.List;

/**
 * Created by Juan on 27/09/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    // Store a member variable for the contacts
    private List<Result> movieList;
    // Store the context for easy access
    private Context mContext;


    private MovieCellInteractionListener movieCellInteractionListener;

    public MovieAdapter(List<Result> movieList, Context mContext, MovieCellInteractionListener movieCellInteractionListener) {
        this.movieList = movieList;
        this.mContext = mContext;
        this.movieCellInteractionListener =  movieCellInteractionListener;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setMovieList(List<Result> movieList) {
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_cell, parent, false);

        MovieViewHolder movieViewHolder = new MovieViewHolder(itemView);
        return movieViewHolder;
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Result movie = movieList.get(position);
        MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
        movieViewHolder.bindProduct(movie, mContext);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitle;

        private ImageView imageViewMovie;

        private View movieView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.cell_title);
            imageViewMovie = (ImageView) itemView.findViewById(R.id.cell_image_view);
            movieView = itemView;


        }

        public void bindProduct(final Result movie, final Context context){

            textViewTitle.setText(movie.getTitle());


            Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + movie.getPosterPath()).into(imageViewMovie);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieCellInteractionListener.movieCellInteraction(movie);
                }
            });
        }
    }
    public interface MovieCellInteractionListener {
        // TODO: Update argument type and name
        void movieCellInteraction(Result result);
    }

}
