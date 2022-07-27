package com.news.search.controllers;

import com.news.search.exceptions.NewsSearchException;
import com.news.search.services.impl.NewsSearchServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class})
@WebMvcTest(NewsSearchController.class)
public class NewsSearchControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private NewsSearchServiceImpl newsSearchService;

    @Test
    public void getNewsSearch() throws Exception {

        when(newsSearchService.getNewsSearch(anyString(), anyInt())).thenReturn("gsab");
        mvc.perform(MockMvcRequestBuilders.get("/news-search/?page=1&query=apple")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo("gsab")));

    }


    @Test
    public void getNewsSearchException() throws Exception {

        when(newsSearchService.getNewsSearch(anyString(), anyInt())).thenThrow(NewsSearchException.class);
        mvc.perform(MockMvcRequestBuilders.get("/news-search/?page=0&query=apple")).andDo(print()).andExpect(status().isBadRequest());

    }

}
