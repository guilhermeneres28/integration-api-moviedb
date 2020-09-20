package br.com.dev.desafioclickbus.movie;

import br.com.dev.desafioclickbus.movie.model.MovieSearchRequestForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest()
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_success_when_valid_query() throws Exception {
        final String movieName = "Avengers";
        final MovieSearchRequestForm movieSearchRequestForm = new MovieSearchRequestForm(movieName);

        performPost(movieSearchRequestForm)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void should_success_when_has_atleast_item() throws Exception {
        final String movieName = "Avengers";
        final MovieSearchRequestForm movieSearchRequestForm = new MovieSearchRequestForm(movieName);

        performPost(movieSearchRequestForm)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").isNotEmpty());
    }

    @Test
    void should_success_when_return_no_content() throws Exception {
        final String movieName = "Movie notFound";
        final MovieSearchRequestForm movieSearchRequestForm = new MovieSearchRequestForm(movieName);

        performPost(movieSearchRequestForm)
                .andExpect(status().isNoContent());
    }

    @Test
    void should_return_bad_request_when_invalid_query() throws Exception {
        final String movieName = "";
        final MovieSearchRequestForm movieSearchRequestForm = new MovieSearchRequestForm(movieName);

        performPost(movieSearchRequestForm)
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_success_when_find_movie_detail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/movies/2154"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.adult").exists())
                .andExpect(jsonPath("$.budget").exists())
                .andExpect(jsonPath("$.homepage").exists())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.overview").exists())
                .andExpect(jsonPath("$.popularity").exists())
                .andExpect(jsonPath("$.revenue").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.release_date").exists())
                .andExpect(jsonPath("$.vote_count").exists())
                .andExpect(jsonPath("$.vote_average").exists());
    }


    private <T> ResultActions performPost(T body) throws Exception {
        String jsonBody = toJson(body);
        return mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/movies/")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON));
    }

    private <T> String toJson(T t) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(t);
    }
}