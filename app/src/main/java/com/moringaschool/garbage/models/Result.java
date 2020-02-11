
package com.moringaschool.garbage.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Result {

    @SerializedName("popularity")
    @Expose
    Double popularity;
    @SerializedName("vote_count")
    @Expose
    Integer voteCount;
    @SerializedName("video")
    @Expose
    Boolean video;
    @SerializedName("poster_path")
    @Expose
    String posterPath;
    @SerializedName("id")
    @Expose
    Integer id;
    @SerializedName("adult")
    @Expose
    Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    String backdropPath;
    @SerializedName("original_language")
    @Expose
    String originalLanguage;
    @SerializedName("original_title")
    @Expose
    String originalTitle;
    @SerializedName("genre_ids")
    @Expose
    List<Integer> genreIds = null;
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("vote_average")
    @Expose
    Double voteAverage;
    @SerializedName("overview")
    @Expose
    String overview;
    @SerializedName("release_date")
    @Expose
    String releaseDate;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param overview
     * @param voteAverage
     * @param releaseDate
     * @param video
     * @param originalLanguage
     * @param genreIds
     * @param title
     * @param originalTitle
     * @param popularity
     * @param voteCount
     * @param id
     * @param backdropPath
     * @param adult
     * @param posterPath
     */
    public Result(Double popularity, Integer voteCount, Boolean video, String posterPath, Integer id, Boolean adult, String backdropPath, String originalLanguage, String originalTitle, List<Integer> genreIds, String title, Double voteAverage, String overview, String releaseDate) {
        super();
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.posterPath = posterPath;
        this.id = id;
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genreIds = genreIds;
        this.title = title;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }



    public String getPosterPath() {
        return posterPath;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }


    public List<Integer> getGenreIds() {
        return genreIds;
    }


    public String getTitle() {
        return title;
    }


    public Double getVoteAverage() {
        return voteAverage;
    }


    public String getOverview() {
        return overview;
    }


    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}
