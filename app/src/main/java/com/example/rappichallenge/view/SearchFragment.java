package com.example.rappichallenge.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.rappichallenge.R;
import com.example.rappichallenge.controller.MoviesController;
import com.example.rappichallenge.model.Result;
import com.example.rappichallenge.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;


public class SearchFragment extends Fragment implements MovieAdapter.MovieCellInteractionListener {

    private SearchFragmentInteractionListener mListener;
    List<Result> movieList;
    RecyclerView recyclerView;
    MoviesController moviesController;
    MovieAdapter movieAdapter;
    FloatingSearchView floatingSearchView;

    public SearchFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        floatingSearchView = view.findViewById(R.id.floating_search_view);
        recyclerView = view.findViewById(R.id.search_recycler);
        movieList = new ArrayList<>();

        movieAdapter = new MovieAdapter(movieList, getContext(), this);
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(movieAdapter);
        scaleInAnimationAdapter.setFirstOnly(false);
        recyclerView.setAdapter(scaleInAnimationAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        moviesController = new MoviesController(getContext());
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                moviesController.searchMovies(new ResultListener<List<Result>>() {
                    @Override
                    public void finish(List<Result> resultado) {
                        movieList.clear();
                        movieList.addAll(resultado);
                        movieAdapter.notifyDataSetChanged();
                    }
                }, newQuery);
            }
        });

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SearchFragmentInteractionListener) {
            mListener = (SearchFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SearchFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void movieCellInteraction(Result result) {
        mListener.searchFragmentInteraction(result);
    }


    public interface SearchFragmentInteractionListener {
        // TODO: Update argument type and name
        void searchFragmentInteraction(Result result);
    }
}
