package br.com.dev.desafioclickbus.genre.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

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
