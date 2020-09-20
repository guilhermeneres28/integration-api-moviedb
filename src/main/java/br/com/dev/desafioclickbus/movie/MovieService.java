package br.com.dev.desafioclickbus.movie;

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
public class MovieService {

    private static final Logger log = LoggerFactory.getLogger(MovieService.class);

    @Value("${base-integration-api-url}")
    private String BASE_API_URL;

    @Value("${api-integration-key}")
    private  String API_KEY;

    @Autowired
    private RestTemplate restTemplate;

    public Optional<List<MovieDTO>> searchFromApi(MovieSearchRequestForm form) throws SearchMovieIntegrationException {
        try {
           final ResponseEntity<Root> response = restTemplate
                   .getForEntity(BASE_API_URL + "search/movie?api_key=" + API_KEY +  "&query=" + form.getQuery(), Root.class);
           if(response.getStatusCode().is2xxSuccessful())
               return Optional.ofNullable(response.getBody()).map(Root::getResults).filter(results -> !results.isEmpty());
        } catch (RestClientException restClientException) {
            log.error("Error to integrate with movie search api" + restClientException.getMessage());
            throw new SearchMovieIntegrationException("Error retriving movie data");
        }
        return Optional.empty();
    }
}
