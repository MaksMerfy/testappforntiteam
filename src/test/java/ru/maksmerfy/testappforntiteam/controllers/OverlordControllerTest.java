package ru.maksmerfy.testappforntiteam.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.maksmerfy.testappforntiteam.models.Planet;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = "/sql-before-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class OverlordControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void OverlordControllerGetEmpty() throws Exception {
        this.mockMvc.perform(get("/overlord"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void OverlordControllerGetInfo() throws Exception {
        List<String> listPlanets = new ArrayList<String>();
        listPlanets.add("venera");
        this.mockMvc.perform(get("/overlord/MaksMerfy"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("MaksMerfy"))
                .andExpect(jsonPath("$.age").value(30))
                .andExpect(jsonPath("$.planets").value(listPlanets));
    }

    @Test
    void OverlordControllerPostEmpty() throws Exception {
        this.mockMvc.perform(post("/overlord"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void OverlordControllerPostEmptyJson() throws Exception {
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("name not valid");
        errorMessages.add("age must be above 0");

        this.mockMvc.perform(post("/overlord")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void OverlordControllerPostInvalidJson() throws Exception {
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("age must be above 0");

        this.mockMvc.perform(post("/overlord")
                        .content("{ \"name\" : 30, \"age\" : \"asd\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void OverlordControllerPostInvalidAge() throws Exception {
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("age must be above 0");

        this.mockMvc.perform(post("/overlord")
                        .content("{ \"name\" : \"testUser\", \"age\" : \"asd\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void OverlordControllerPostValidJson() throws Exception {
        List<String> errorMessages = new ArrayList<String>();

        this.mockMvc.perform(post("/overlord")
                        .content("{ \"name\" : \"testUser\", \"age\" : \"30\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void OverlordControllerPostOverlordExist() throws Exception {
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("Overlord is exist in base");

        this.mockMvc.perform(post("/overlord")
                        .content("{ \"name\" : \"MaksMerfy\", \"age\" : \"30\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void OverlordControllerGetloafers() throws Exception {
        List<String> listLoafers = new ArrayList<String>();
        listLoafers.add("testLoafer");
        this.mockMvc.perform(get("/loafers"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.listLoafers").value(listLoafers));
    }

}