package com.example.rappichallenge.controller;

import android.content.Context;

import com.example.rappichallenge.dao.MovieDAO;
import com.example.rappichallenge.model.Container;
import com.example.rappichallenge.model.Result;
import com.example.rappichallenge.utils.HTTPConnectionManager;
import com.example.rappichallenge.utils.ResultListener;

import java.util.List;

/**
 * Created by Juan on 26/09/2018.
 */

public class MoviesController {
    private Context context;

    public MoviesController(Context context) {
        this.context = context;
    }

    public void getMovies(final ResultListener<List<Result>> listener, final String category){
        final MovieDAO movieDAO = new MovieDAO(context);
        if(HTTPConnectionManager.isNetworkingOnline(context)){

            movieDAO.getMovies(new ResultListener<List<Result>>() {
                @Override
                public void finish(List<Result> resultado) {
                    Container container = new Container();
                    container.setResults(resultado);
                    movieDAO.saveMovies(container, category);
                    listener.finish(resultado);

                }
            }, category);
        }
        else{
            List<Result> movies = movieDAO.getMoviesOffline(category).getResults();

            listener.finish(movies);
        }
    }

    public void searchMovies(final ResultListener<List<Result>> listener, final String query){
        MovieDAO movieDAO = new MovieDAO(context);
        movieDAO.searchMovies(new ResultListener<List<Result>>() {
            @Override
            public void finish(List<Result> resultado) {
                listener.finish(resultado);
            }
        }, query);
    }


}
