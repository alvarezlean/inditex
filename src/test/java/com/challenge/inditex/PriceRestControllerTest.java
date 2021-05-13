package com.challenge.inditex;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PriceRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testCase1() throws Exception {

        mvc.perform(get("/product/35455/brand/1/price?date=2020-06-14-10.00.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("priceId", is(1)));

    }

    @Test
    void testCase2() throws Exception {

        mvc.perform(get("/product/35455/brand/1/price?date=2020-06-14-16.00.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("priceId", is(2)));

    }

    @Test
    void testCase3() throws Exception {

        mvc.perform(get("/product/35455/brand/1/price?date=2020-06-14-21.00.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("priceId", is(1)));

    }

    @Test
    void testCase4() throws Exception {

        mvc.perform(get("/product/35455/brand/1/price?date=2020-06-15-10.00.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("priceId", is(3)));

    }

    @Test
    void testCase5() throws Exception {

        mvc.perform(get("/product/35455/brand/1/price?date=2020-06-15-21.00.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("priceId", is(4)));

    }

}
