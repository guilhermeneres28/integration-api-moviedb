package br.com.dev.desafioclickbus.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public final class TestUtil {

    private TestUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Class cannot be instantiate");
    }

    public static <T> ResultActions performPost(MockMvc mockMvc, String url, T body) throws Exception {
        String jsonBody = toJson(body);
        return mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON));
    }

    public static <T> String toJson(T t) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(t);
    }
}
