package br.com.dev.desafioclickbus.genre;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreDTO implements Serializable {

    public Long id;
    public String name;

    public GenreDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    protected GenreDTO() {

    }
}

@JsonRootName("genres")
class GenreList {
    private List<GenreDTO> genres;

    public GenreList() {
        this.genres = new ArrayList<>();
    }

    public List<GenreDTO> getGenres() {
        return Collections.unmodifiableList(genres);
    }
}
