package com.example.rappichallenge.dao;

import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.rappichallenge.model.Container;
import com.example.rappichallenge.model.Result;
import com.example.rappichallenge.model.Video;
import com.example.rappichallenge.model.VideoContainer;
import com.example.rappichallenge.utils.ResultListener;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Juan on 27/09/2018.
 */

public class VideoDAO {
    private Context context;

    public VideoDAO(Context context) {
        this.context = context;
    }

    private final Gson gson = new Gson();

    public void getVideos(final ResultListener<List<Video>> listener, String id) {
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/" + id + "/videos?api_key=be7d6f98c96b7defab89eaf466df8691&language=en-US").build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                VideoContainer container = gson.fromJson(response, VideoContainer.class);
                listener.finish(container.getResults());
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }
}
