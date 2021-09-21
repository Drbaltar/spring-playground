package com.drbaltar.springplayground.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlaygroundController.class)
public class PlaygroundControllerTest {

    @Autowired
    MockMvc mvc;

    ObjectMapper objectMapper = new ObjectMapper();                    // 1

    @Test
    public void testCreate() throws Exception {
        record Pet(String name, String color) {}
        var testString = """
                {
                    "name": "Hercules",
                    "age": 57
                }
                """;
        var data = new HashMap<String, Object>() {  // 2
            {
                put("name", "Hercules");
                put("pet", (new Pet("Mae", "Brown")));
            }
        };

        var json = objectMapper.writeValueAsString(data);            // 3

        var request = post("/some/path")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);                                         // 4

        this.mvc.perform(request).andExpect(status().is4xxClientError());
    }
}
