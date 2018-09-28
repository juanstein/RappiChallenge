package com.example.rappichallenge.controller;

import android.content.Context;

import com.example.rappichallenge.dao.VideoDAO;
import com.example.rappichallenge.model.Result;
import com.example.rappichallenge.model.Video;
import com.example.rappichallenge.utils.ResultListener;

import java.util.List;

/**
 * Created by Juan on 27/09/2018.
 */

public class VideoController {
    private Context context;

    public VideoController(Context context) {
        this.context = context;
    }

    public void getVideos(final ResultListener<List<Video>> listener, final String id){

        VideoDAO videoDAO = new VideoDAO(context);

        videoDAO.getVideos(new ResultListener<List<Video>>() {
            @Override
            public void finish(List<Video> resultado) {
                listener.finish(resultado);
            }
        }, id);

    }
}
