package com.example.rappichallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Juan on 26/09/2018.
 */

public class Container {


    @SerializedName("results")
    @Expose
    private List<Result> results = null;


    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}