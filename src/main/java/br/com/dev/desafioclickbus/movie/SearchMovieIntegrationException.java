package br.com.dev.desafioclickbus.movie;

import org.springframework.web.client.RestClientException;

public class SearchMovieIntegrationException extends Exception {
    public SearchMovieIntegrationException(String message) {
        super(message);
    }
}
