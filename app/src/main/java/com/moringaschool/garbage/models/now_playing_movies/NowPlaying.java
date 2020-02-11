
package com.moringaschool.garbage.models.now_playing_movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlaying {

    @SerializedName("results")
    @Expose
    private List<Results> results = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public NowPlaying() {
    }

    /**
     * 
     * @param results
     */
    public NowPlaying(List<Results> results) {
        super();
        this.results = results;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

}
