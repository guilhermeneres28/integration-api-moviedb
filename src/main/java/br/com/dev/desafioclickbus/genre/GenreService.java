package br.com.dev.desafioclickbus.genre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GenreService {

    private static final Logger log = LoggerFactory.getLogger(GenreService.class);
    private static final String BASE_API_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "88b3d3285d2e4b4e6fa0838c97c1ff68";

    @Autowired
    private RestTemplate restTemplate;

    public Optional<List<GenreDTO>> getGenresFromApi() throws GenreIntegrationException {
        try {
            final ResponseEntity<GenreList> response = restTemplate
                    .getForEntity(BASE_API_URL + "genre/movie/list?api_key=" + API_KEY + "&language=en-US", GenreList.class);
            if(response.getStatusCode().is2xxSuccessful())
                return response.getBody() != null ? Optional.of(response.getBody().getGenres()) : Optional.empty();
        } catch (RestClientException restClientException) {
            log.error("Error getting genres from movie api", restClientException);
            throw new GenreIntegrationException("Error getting genres from movie api" + restClientException.getMessage());
        }
        return Optional.empty();
    }
}
