package com.example.rappichallenge.dao;

import android.content.Context;
import android.preference.PreferenceManager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.rappichallenge.model.Container;
import com.example.rappichallenge.model.Result;
import com.example.rappichallenge.utils.ResultListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 26/09/2018.
 */

public class MovieDAO {

    private Context context;

    public MovieDAO(Context context) {
        this.context = context;
    }

    private final Gson gson = new Gson();

    public void getMovies(final ResultListener<List<Result>> listener, String category) {
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/" + category + "?api_key=be7d6f98c96b7defab89eaf466df8691&language=en-US&page=1").build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                Container container = gson.fromJson(response, Container.class);
                listener.finish(container.getResults());
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }

    public void saveMovies(Container movieContainer, String category){

        String containerJson = gson.toJson(movieContainer);

        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(category,containerJson).apply();
    }
    public Container getMoviesOffline(String category){

        Gson gson = new Gson();
        String containerJson = PreferenceManager.
                getDefaultSharedPreferences(context).getString(category,"");


        Container container = gson.fromJson(containerJson, Container.class);

        if(container!= null){

            return container;
        }
        else {
            container = new Container();
            List<Result> resultList = new ArrayList<>();
            container.setResults(resultList);
            return container;
        }
    }
    public void searchMovies(final ResultListener<List<Result>> listener, String query) {
        AndroidNetworking.get("https://api.themoviedb.org/3/search/movie?api_key=be7d6f98c96b7defab89eaf466df8691&language=en-US&query=" + query +"&page=1&include_adult=false").build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                Container container = gson.fromJson(response, Container.class);
                listener.finish(container.getResults());
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }


}
