package br.com.dev.desafioclickbus.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MovieDetailDTO{
    private boolean adult;
    private int budget;
    private String homepage;
    private int id;
    private String overview;
    private double popularity;

    @JsonProperty("release_date")
    private String releaseDate;
    private int revenue;
    private String status;
    private String title;

    @JsonProperty("vote_average")
    private double voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;

    protected MovieDetailDTO() {

    }
}
