package br.com.dev.desafioclickbus.genre.service;

import br.com.dev.desafioclickbus.genre.dto.GenreDTO;
import br.com.dev.desafioclickbus.genre.dto.GenreListDTO;
import br.com.dev.desafioclickbus.genre.exceptions.GenreIntegrationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private static final Logger log = LoggerFactory.getLogger(GenreService.class);

    @Value("${base-integration-api-url}")
    private String BASE_API_URL;

    @Value("${api-integration-key}")
    private  String API_KEY;

    @Autowired
    private RestTemplate restTemplate;

    public Optional<List<GenreDTO>> getGenresFromApi() throws GenreIntegrationException {
        try {
            final ResponseEntity<GenreListDTO> response = restTemplate
                    .getForEntity(BASE_API_URL + "genre/movie/list?api_key=" + API_KEY, GenreListDTO.class);
            if(response.getStatusCode().is2xxSuccessful())
                return Optional.ofNullable(response.getBody()).map(GenreListDTO::getGenres);
        } catch (RestClientException restClientException) {
            log.error("Error getting genres from movie api", restClientException);
            throw new GenreIntegrationException("Error getting genres from movie api" + restClientException.getMessage());
        }
        return Optional.empty();
    }
}
