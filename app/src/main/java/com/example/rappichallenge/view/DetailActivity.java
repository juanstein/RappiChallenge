package com.example.rappichallenge.view;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rappichallenge.R;
import com.example.rappichallenge.controller.MoviesController;
import com.example.rappichallenge.controller.VideoController;
import com.example.rappichallenge.model.Result;
import com.example.rappichallenge.model.Video;
import com.example.rappichallenge.utils.ResultListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class DetailActivity extends AppCompatActivity implements VideoAdapter.VideoCellInteractionListener {

    public static final String MOVIE = "movie";
    ImageView imageView;
    TextView textViewTitle;
    TextView textViewOverview;
    RecyclerView recyclerView;
    List<Video> videoList;
    VideoAdapter videoAdapter;
    VideoController videoController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupWindowAnimations();
        textViewTitle = findViewById(R.id.detail_title);
        textViewOverview = findViewById(R.id.detail_overview);
        imageView = findViewById(R.id.detail_image_view);
        recyclerView = findViewById(R.id.video_recycler);

        Intent intent = getIntent();
        Bundle unBundle = intent.getExtras();

        String movieString = unBundle.getString(MOVIE);

        Gson gson = new Gson();

        Result movie = gson.fromJson(movieString, Result.class);
        textViewTitle.setText(movie.getTitle());
        textViewOverview.setText(movie.getOverview());
        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500/" + movie.getPosterPath()).into(imageView);

        videoList = new ArrayList<>();


        videoAdapter = new VideoAdapter(videoList, getApplicationContext(), this, getLifecycle());
        recyclerView.setAdapter(videoAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        videoController = new VideoController(getApplicationContext());

        videoController.getVideos(new ResultListener<List<Video>>() {
            @Override
            public void finish(List<Video> resultado) {
                videoList.clear();
                videoList.addAll(resultado);
                videoAdapter.notifyDataSetChanged();
            }
        }, movie.getId().toString());

    }
    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
    }

    @Override
    public void videoCellInteraction(Result result) {

    }
}
