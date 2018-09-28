package com.example.rappichallenge.view;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;

import com.example.rappichallenge.R;
import com.example.rappichallenge.model.Result;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements PopularFragment.PopularOnFragmentInteractionListener, TopRatedFragment.TopRatedOnFragmentInteractionListener,
        UpcomingFragment.UpcomingOnFragmentInteractionListener, SearchFragment.SearchFragmentInteractionListener{

    ViewPager viewPager;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
        viewPager = findViewById(R.id.viewPager);
        MovieListFragmentAdapter adapterViewPager = new MovieListFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        gson = new Gson();
    }

    @Override
    public void popularOnFragmentInteraction(Result result) {
        String movieString = gson.toJson(result);
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putString(DetailActivity.MOVIE, movieString);
        intent.putExtras(unBundle);
        startActivity(intent);
    }

    @Override
    public void topRatedOnFragmentInteraction(Result result) {
        String movieString = gson.toJson(result);
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putString(DetailActivity.MOVIE, movieString);
        intent.putExtras(unBundle);
        startActivity(intent);
    }

    @Override
    public void upcomingOnFragmentInteraction(Result result) {
        String movieString = gson.toJson(result);
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putString(DetailActivity.MOVIE, movieString);
        intent.putExtras(unBundle);
        startActivity(intent);
    }
    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);
    }


    @Override
    public void searchFragmentInteraction(Result result) {
        String movieString = gson.toJson(result);
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putString(DetailActivity.MOVIE, movieString);
        intent.putExtras(unBundle);
        startActivity(intent);
    }
}
