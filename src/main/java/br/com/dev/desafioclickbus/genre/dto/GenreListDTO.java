package br.com.dev.desafioclickbus.genre.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonRootName("genres")
public class GenreListDTO {
    private List<GenreDTO> genres;

    public GenreListDTO() {
        this.genres = new ArrayList<>();
    }

    public List<GenreDTO> getGenres() {
        return Collections.unmodifiableList(genres);
    }
}