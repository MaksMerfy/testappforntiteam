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
public class PlanetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void PlanetControllerGetEmpty() throws Exception {
        this.mockMvc.perform(get("/planet"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void PlanetControllerGetInfo() throws Exception {
        this.mockMvc.perform(get("/planet/venera"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("venera"))
                .andExpect(jsonPath("$.overlord").value("MaksMerfy"));
    }

    @Test
    void PlanetControllerPostEmpty() throws Exception {
        this.mockMvc.perform(post("/planet"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void PlanetControllerPostEmptyJson() throws Exception {
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("name not valid");

        this.mockMvc.perform(post("/planet")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void PlanetControllerPostInvalidJson() throws Exception {
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("Can't find overlord");

        this.mockMvc.perform(post("/planet")
                        .content("{ \"name\" : 30, \"overlord\" : 1}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void PlanetControllerPostValidPlanet() throws Exception {
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("Planet is exist in base");

        this.mockMvc.perform(post("/planet")
                        .content("{ \"name\" : \"venera\", \"overlord\" : \"\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void PlanetControllerPostValidJson() throws Exception {
        List<String> errorMessages = new ArrayList<String>();

        this.mockMvc.perform(post("/planet")
                        .content("{ \"name\" : \"testPlanet\", \"overlord\" : \"MaksMerfy\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void PlanetControllerPostOverlordNotExist() throws Exception {
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("Can't find overlord");

        this.mockMvc.perform(post("/planet")
                        .content("{ \"name\" : \"Mercury\", \"overlord\" : \"testForTest\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }


    @Test
    void PlanetControllerSetEmpty() throws Exception {
        this.mockMvc.perform(post("/planet/setoverlord"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void PlanetControllerSetEmptyJson() throws Exception {
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("name not valid");
        errorMessages.add("Can't find planet");

        this.mockMvc.perform(post("/planet/setoverlord")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void PlanetControllerSetInvalidJson() throws Exception {
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("Can't find overlord");
        errorMessages.add("Can't find planet");

        this.mockMvc.perform(post("/planet/setoverlord")
                        .content("{ \"name\" : 30, \"overlord\" : 1}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void PlanetControllerSetValidPlanet() throws Exception {
        List<String> errorMessages = new ArrayList<String>();

        this.mockMvc.perform(post("/planet/setoverlord")
                        .content("{ \"name\" : \"venera\", \"overlord\" : \"\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void PlanetControllerSetValidJson() throws Exception {
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("Can't find planet");

        this.mockMvc.perform(post("/planet/setoverlord")
                        .content("{ \"name\" : \"testPlanet\", \"overlord\" : \"MaksMerfy\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void PlanetControllerSetOverlordNotExist() throws Exception {
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("Can't find overlord");
        errorMessages.add("Can't find planet");

        this.mockMvc.perform(post("/planet/setoverlord")
                        .content("{ \"name\" : \"Mercury\", \"overlord\" : \"testForTest\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void PlanetControllerDestroyEmpty() throws Exception {
        this.mockMvc.perform(post("/planet/destroy"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void PlanetControllerDestroyEmptyJson() throws Exception {
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("name not valid");
        errorMessages.add("Can't find planet");

        this.mockMvc.perform(post("/planet/destroy")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void PlanetControllerDestroyInvalidJson() throws Exception {
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("name not valid");
        errorMessages.add("Can't find planet");

        this.mockMvc.perform(post("/planet/destroy")
                        .content("{\"overlord\" : 1}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void PlanetControllerDestroyInvalidPlanet() throws Exception {
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("Can't find planet");

        this.mockMvc.perform(post("/planet/destroy")
                        .content("{\"name\" : 30}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }

    @Test
    void PlanetControllerDestroyValidPlanet() throws Exception {
        List<String> errorMessages = new ArrayList<String>();

        this.mockMvc.perform(post("/planet/destroy")
                        .content("{\"name\" : \"testForDestroy\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.name()))
                .andExpect(jsonPath("$.errorMessages").value(errorMessages));
    }
}
