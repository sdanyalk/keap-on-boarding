package com.keap.taskapi.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testTasksEndpoint() throws Exception {
        this.
                mockMvc
                .perform(get("/tasks"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", is("complete the project")));
    }

    @Test
    void testDummyEndpoint() throws Exception {
        this.
                mockMvc
                .perform(get("/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }
}