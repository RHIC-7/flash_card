package com.flash_card.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class GetCategoryTest {

    @Autowired
    private MockMvc mockmvc;

    @Test
    void requestGetCategory_response200() throws Exception {
        this.mockmvc.perform(get("/get/category"))
                .andExpect(status().isOk());
    }

    @Test
    void requestGetUserCategory_response200() throws Exception {
        this.mockmvc.perform(get("/get/category/10"))
                .andExpect(status().isOk());
    }
}
