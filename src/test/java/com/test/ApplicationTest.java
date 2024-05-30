package com.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class ApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void test1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/checkout").content("{\n" +
                                "  \"tool_code\": \"JAKR\",\n" +
                                "  \"rental_day_count\": 5,\n" +
                                "  \"discount_percentage\": 101,\n" +
                                "  \"checkout_date\": \"12/01/15\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void test2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/checkout").content("{\n" +
                                "  \"tool_code\": \"LADW\",\n" +
                                "  \"rental_day_count\": 3,\n" +
                                "  \"discount_percentage\": 10,\n" +
                                "  \"checkout_date\": \"07/02/20\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("tool_code", equalTo("LADW")))
                .andExpect(jsonPath("rental_day_count", equalTo(3)))
                .andExpect(jsonPath("discount_percentage", equalTo(10)))
                .andExpect(jsonPath("checkout_date", equalTo("07/02/20")))
                .andExpect(jsonPath("tool_type", equalTo("Ladder")))
                .andExpect(jsonPath("tool_brand", equalTo("Werner")))
                .andExpect(jsonPath("due_date", equalTo("07/05/20")))
                .andExpect(jsonPath("daily_rental_charge", equalTo(1.99)))
                .andExpect(jsonPath("charge_days", equalTo(2)))
                .andExpect(jsonPath("pre-discount_charge", equalTo(3.98)))
                .andExpect(jsonPath("discount_amount", equalTo(0.40)))
                .andExpect(jsonPath("final_charge", equalTo(3.58)));
    }


    @Test
    void test3() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/checkout").content("{\n" +
                                "  \"tool_code\": \"CHNS\",\n" +
                                "  \"rental_day_count\": 5,\n" +
                                "  \"discount_percentage\": 25,\n" +
                                "  \"checkout_date\": \"07/02/15\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("tool_code", equalTo("CHNS")))
                .andExpect(jsonPath("rental_day_count", equalTo(5)))
                .andExpect(jsonPath("discount_percentage", equalTo(25)))
                .andExpect(jsonPath("checkout_date", equalTo("07/02/15")))
                .andExpect(jsonPath("tool_type", equalTo("Chainsaw")))
                .andExpect(jsonPath("tool_brand", equalTo("Stihl")))
                .andExpect(jsonPath("due_date", equalTo("07/07/15")))
                .andExpect(jsonPath("daily_rental_charge", equalTo(1.49)))
                .andExpect(jsonPath("charge_days", equalTo(3)))
                .andExpect(jsonPath("pre-discount_charge", equalTo(4.47)))
                .andExpect(jsonPath("discount_amount", equalTo(1.12)))
                .andExpect(jsonPath("final_charge", equalTo(3.35)));
    }

    @Test
    void test4() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/checkout").content("{\n" +
                                "  \"tool_code\": \"JAKD\",\n" +
                                "  \"rental_day_count\": 6,\n" +
                                "  \"discount_percentage\": 0,\n" +
                                "  \"checkout_date\": \"09/03/15\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("tool_code", equalTo("JAKD")))
                .andExpect(jsonPath("rental_day_count", equalTo(6)))
                .andExpect(jsonPath("discount_percentage", equalTo(0)))
                .andExpect(jsonPath("checkout_date", equalTo("09/03/15")))
                .andExpect(jsonPath("tool_type", equalTo("Jackhammer")))
                .andExpect(jsonPath("tool_brand", equalTo("DeWalt")))
                .andExpect(jsonPath("due_date", equalTo("09/09/15")))
                .andExpect(jsonPath("daily_rental_charge", equalTo(2.99)))
                .andExpect(jsonPath("charge_days", equalTo(3)))
                .andExpect(jsonPath("pre-discount_charge", equalTo(8.97)))
                .andExpect(jsonPath("discount_amount", equalTo(0.00)))
                .andExpect(jsonPath("final_charge", equalTo(8.97)));
    }

    @Test
    void test5() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/checkout").content("{\n" +
                                "  \"tool_code\": \"JAKR\",\n" +
                                "  \"rental_day_count\": 9,\n" +
                                "  \"discount_percentage\": 0,\n" +
                                "  \"checkout_date\": \"07/02/15\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("tool_code", equalTo("JAKR")))
                .andExpect(jsonPath("rental_day_count", equalTo(9)))
                .andExpect(jsonPath("discount_percentage", equalTo(0)))
                .andExpect(jsonPath("checkout_date", equalTo("07/02/15")))
                .andExpect(jsonPath("tool_type", equalTo("Jackhammer")))
                .andExpect(jsonPath("tool_brand", equalTo("Ridgid")))
                .andExpect(jsonPath("due_date", equalTo("07/11/15")))
                .andExpect(jsonPath("daily_rental_charge", equalTo(2.99)))
                .andExpect(jsonPath("charge_days", equalTo(6)))
                .andExpect(jsonPath("pre-discount_charge", equalTo(17.94)))
                .andExpect(jsonPath("discount_amount", equalTo(0.00)))
                .andExpect(jsonPath("final_charge", equalTo(17.94)));
    }

    @Test
    void test6() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/checkout").content("{\n" +
                                "  \"tool_code\": \"JAKR\",\n" +
                                "  \"rental_day_count\": 4,\n" +
                                "  \"discount_percentage\": 50,\n" +
                                "  \"checkout_date\": \"07/02/20\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("tool_code", equalTo("JAKR")))
                .andExpect(jsonPath("rental_day_count", equalTo(4)))
                .andExpect(jsonPath("discount_percentage", equalTo(50)))
                .andExpect(jsonPath("checkout_date", equalTo("07/02/20")))
                .andExpect(jsonPath("tool_type", equalTo("Jackhammer")))
                .andExpect(jsonPath("tool_brand", equalTo("Ridgid")))
                .andExpect(jsonPath("due_date", equalTo("07/06/20")))
                .andExpect(jsonPath("daily_rental_charge", equalTo(2.99)))
                .andExpect(jsonPath("charge_days", equalTo(2)))
                .andExpect(jsonPath("pre-discount_charge", equalTo(5.98)))
                .andExpect(jsonPath("discount_amount", equalTo(2.99)))
                .andExpect(jsonPath("final_charge", equalTo(2.99)));
    }


    private String getLocalServerUrl() {
        return String.format("http://localhost:%d/", port);
    }

}
