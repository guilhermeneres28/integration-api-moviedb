package br.com.dev.desafioclickbus.movie;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MovieSearchRequestForm {

    @NotBlank
    @NotNull
    private String query;

    public MovieSearchRequestForm(String query) {
        this.query = query;
    }

    protected MovieSearchRequestForm() {

    }

    public String getQuery() {
        return query;
    }
}
