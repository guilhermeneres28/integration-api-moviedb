package br.com.dev.desafioclickbus.genre.service;

import br.com.dev.desafioclickbus.genre.dto.GenreDTO;
import br.com.dev.desafioclickbus.genre.dto.GenreListDTO;
import br.com.dev.desafioclickbus.genre.exceptions.GenreIntegrationException;
import br.com.dev.desafioclickbus.shared.service.BaseIntegrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService extends BaseIntegrationService {

    public Optional<List<GenreDTO>> getGenresFromApi() throws GenreIntegrationException {
        try {
           return getDataFromApi("genre/movie/list?api_key=" + API_KEY, GenreListDTO.class, this::transformToGenresList);
        } catch (RestClientException restClientException) {
            log.error("Error getting genres from movie api", restClientException);
            throw new GenreIntegrationException("Error getting genres from movie api" + restClientException.getMessage());
        }
    }

    private Optional<List<GenreDTO>> transformToGenresList(GenreListDTO genreListDTO) {
        return Optional.ofNullable(genreListDTO).map(GenreListDTO::getGenres);
    }
}
