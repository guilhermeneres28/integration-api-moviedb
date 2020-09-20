package br.com.dev.desafioclickbus.movie;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

public class MovieDTO{
    public double popularity;
    public int vote_count;
    public boolean video;
    public String poster_path;
    public int id;
    public boolean adult;
    public String backdrop_path;
    public String original_language;
    public String original_title;
    public List<Integer> genre_ids;
    public String title;
    public double vote_average;
    public String overview;
    public String release_date;
}

class Root{
    private int page;

    @JsonProperty("total_results")
    private int totalResults;

    @JsonProperty("total_pages")
    private int totalPages;
    private List<MovieDTO> results;

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<MovieDTO> getResults() {
        return Collections.unmodifiableList(results);
    }
}
