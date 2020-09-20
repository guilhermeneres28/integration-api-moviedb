package br.com.dev.desafioclickbus.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDetailDTO> getDetails(@PathVariable("movieId") String movieId) throws SearchMovieDetailIntegrationException {
        return movieService.findDetailFromApi(movieId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);
    }
}
