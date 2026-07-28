package com.cognizant.springlearn;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.cognizant.springlearn.controller.CountryController;

@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

    @Autowired
    private CountryController countryController;

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
        assertNotNull(countryController);
    }

    @Test
    void testGetCountry() throws Exception {
        ResultActions actions = mvc.perform(get("/country").with(httpBasic("user", "pwd")));

        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.code").exists());
        actions.andExpect(jsonPath("$.code").value("IN"));
        actions.andExpect(jsonPath("$.name").exists());
        actions.andExpect(jsonPath("$.name").value("India"));
    }

    @Test
    void testGetCountryException() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/az").with(httpBasic("user", "pwd")));

        actions.andExpect(status().isNotFound());
        actions.andExpect(status().reason("Country not found"));
    }

    @Test
    void testUpdateEmployeeException() throws Exception {
        // id 999 does not exist in the configured employee list
        String payload = "{\"id\":999,\"name\":\"Unknown\",\"salary\":1000,\"permanent\":true,"
                + "\"dateOfBirth\":\"01/01/2000\"}";

        ResultActions actions = mvc.perform(put("/employees")
                .with(httpBasic("user", "pwd"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload));

        actions.andExpect(status().isNotFound());
        actions.andExpect(status().reason("Employee not found"));
    }

    @Test
    void testAuthenticateReturnsToken() throws Exception {
        ResultActions actions = mvc.perform(get("/authenticate").with(httpBasic("user", "pwd")));

        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.token").exists());
    }

    @Test
    void testUnauthenticatedRequestIsRejected() throws Exception {
        mvc.perform(get("/countries")).andExpect(status().isUnauthorized());
    }

}
