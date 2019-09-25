package com.mhp.coding.challenges.mapping.controllers;

import com.mhp.coding.challenges.mapping.services.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerTest {

    private ArticleService service;
    private ArticleController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void list_should_returns_the_correct_json_article_list() throws Exception {
        this.mockMvc.perform(get("/article"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1001,\"title\":\"Article Nr.: 1001\",\"description\":\"Article Description 1001\",\"author\":\"Max Mustermann\",\"blocks\":[{\"sortIndex\":0,\"image\":{\"id\":1,\"url\":\"https://someurl.com/image/1\",\"imageSize\":\"LARGE\"}},{\"sortIndex\":1,\"text\":\"Some Text for 1001\"},{\"sortIndex\":2,\"text\":\"Second Text for 1001\"},{\"sortIndex\":3,\"images\":[{\"id\":2,\"url\":\"https://someurl.com/image/2\",\"imageSize\":\"LARGE\"},{\"id\":3,\"url\":\"https://someurl.com/image/3\",\"imageSize\":\"LARGE\"}]},{\"sortIndex\":4,\"text\":\"Third Text for 1001\"},{\"sortIndex\":5,\"url\":\"https://youtu.be/myvideo\",\"type\":\"YOUTUBE\"}]}")));
    }

    @Test
    public void details_should_return_a_200_and_a_correct_json() throws Exception {
        this.mockMvc.perform(get("/article/1001"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1001,\"title\":\"Article Nr.: 1001\",\"description\":\"Article Description 1001\",\"author\":\"Max Mustermann\",\"blocks\":[{\"sortIndex\":0,\"image\":{\"id\":1,\"url\":\"https://someurl.com/image/1\",\"imageSize\":\"LARGE\"}},{\"sortIndex\":1,\"text\":\"Some Text for 1001\"},{\"sortIndex\":2,\"text\":\"Second Text for 1001\"},{\"sortIndex\":3,\"images\":[{\"id\":2,\"url\":\"https://someurl.com/image/2\",\"imageSize\":\"LARGE\"},{\"id\":3,\"url\":\"https://someurl.com/image/3\",\"imageSize\":\"LARGE\"}]},{\"sortIndex\":4,\"text\":\"Third Text for 1001\"},{\"sortIndex\":5,\"url\":\"https://youtu.be/myvideo\",\"type\":\"YOUTUBE\"}]}")));
    }

    @Test
    public void details_should_return_a_404_for_unknown_ids() throws Exception {
        this.mockMvc.perform(get("/article/999"))
                .andExpect(status().is4xxClientError());
    }
}
