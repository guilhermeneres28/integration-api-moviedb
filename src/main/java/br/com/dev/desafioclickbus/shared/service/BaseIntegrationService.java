package br.com.dev.desafioclickbus.shared.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.function.Function;

public abstract class BaseIntegrationService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${base-integration-api-url}")
    protected String BASE_API_URL;

    @Value("${api-integration-key}")
    protected   String API_KEY;

    @Autowired
    protected RestTemplate restTemplate;

    public <T, E> Optional<T> getDataFromApi(
            final String url,
            final Class<E> type,
            final Function<E, Optional<T>> transform
    ) {
            final ResponseEntity<E> response = restTemplate
                    .getForEntity(BASE_API_URL + url, type);
            if(response.getStatusCode().is2xxSuccessful())
                return transform.apply(response.getBody());
        return Optional.empty();
    }
}
