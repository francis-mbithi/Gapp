
package com.moringaschool.garbage.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Popular {

    @SerializedName("page")
    @Expose
    Integer page;
    @SerializedName("total_results")
    @Expose
    Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    Integer totalPages;
    @SerializedName("results")
    @Expose
    List<Result> results = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Popular() {
    }

    /**
     * 
     * @param totalResults
     * @param totalPages
     * @param page
     * @param results
     */
    public Popular(Integer page, Integer totalResults, Integer totalPages, List<Result> results) {
        super();
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

}
