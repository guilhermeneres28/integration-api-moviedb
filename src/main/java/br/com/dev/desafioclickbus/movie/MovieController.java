package br.com.dev.desafioclickbus.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<List<MovieDTO>> search(@RequestBody @Valid MovieSearchRequestForm form) throws SearchMovieIntegrationException {
        return movieService.searchFromApi(form)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);
    }
}
