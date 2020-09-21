package br.com.dev.desafioclickbus.movie.service;

import br.com.dev.desafioclickbus.movie.dto.MovieDTO;
import br.com.dev.desafioclickbus.movie.dto.MovieDetailDTO;
import br.com.dev.desafioclickbus.movie.dto.PageDTO;
import br.com.dev.desafioclickbus.movie.exceptions.SearchMovieDetailIntegrationException;
import br.com.dev.desafioclickbus.movie.exceptions.SearchMovieIntegrationException;
import br.com.dev.desafioclickbus.movie.model.MovieSearchRequestForm;
import br.com.dev.desafioclickbus.shared.service.BaseIntegrationService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Optional;

@Service
public class MovieIntegrationService extends BaseIntegrationService {

    public Optional<List<MovieDTO>> searchFromApi(MovieSearchRequestForm form) throws SearchMovieIntegrationException {
        try {
           return getDataFromApi("search/movie?api_key=" + API_KEY +  "&query=" + form.getQuery(), PageDTO.class, this::transformToMovies);
        } catch (RestClientException restClientException) {
            log.error("Error to integrate with movie search api" + restClientException.getMessage());
            throw new SearchMovieIntegrationException("Error retriving movie data");
        }
    }

    public Optional<MovieDetailDTO> findDetailFromApi(String movieId) throws SearchMovieDetailIntegrationException {
        try {
            return this.getDataFromApi("/movie/" + movieId + "?api_key=" + API_KEY, MovieDetailDTO.class, Optional::ofNullable);
        } catch (RestClientException restClientException) {
            log.error("Error retrieving movie detail" + restClientException.getMessage());
            throw new SearchMovieDetailIntegrationException("Error retriving movie data");
        }
    }

    private Optional<List<MovieDTO>> transformToMovies(PageDTO pageDTO) {
        return Optional.ofNullable(pageDTO).map(PageDTO::getResults).filter(results -> !results.isEmpty());
    }
}
