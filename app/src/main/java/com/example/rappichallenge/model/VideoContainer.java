package com.example.rappichallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Juan on 27/09/2018.
 */

public class VideoContainer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<Video> results = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }

}