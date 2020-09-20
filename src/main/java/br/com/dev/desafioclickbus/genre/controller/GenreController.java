package br.com.dev.desafioclickbus.genre.controller;

import br.com.dev.desafioclickbus.genre.dto.GenreDTO;
import br.com.dev.desafioclickbus.genre.exceptions.GenreIntegrationException;
import br.com.dev.desafioclickbus.genre.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/v1/genres")
@RestController
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping()
    public ResponseEntity<List<GenreDTO>> getGenres() throws GenreIntegrationException {
        Optional<List<GenreDTO>> genres = genreService.getGenresFromApi();
        return genres.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
