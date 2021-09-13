package com.drbaltar.springplayground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void shouldReturnDefaultWelcomeMessage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from Spring!"));
    }

    @Test
    void shouldReturnWelcomeMessageWithInputName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/?name=Kyle"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Kyle from Spring!"));
    }

    @Test
    void shouldReturnPIOnGETRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/math/pi"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(Math.PI)));
    }
}
