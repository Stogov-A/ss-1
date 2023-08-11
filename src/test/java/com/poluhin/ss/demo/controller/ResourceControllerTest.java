package com.poluhin.ss.demo.controller;

import com.poluhin.ss.demo.domain.entity.ResourceObjectEntity;
import com.poluhin.ss.demo.domain.model.ResourceObject;
import com.poluhin.ss.demo.repository.ResourceObjectRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.hamcrest.Matchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
class ResourceControllerTest {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();


    @WithMockUser(roles = {"ADMIN", "USER"})
    @Test
    public void getResourceObject() throws Exception {
        ResourceObject resourceObject = new ResourceObject(1, "VALL", "PATH");
        mockMvc.perform(post("/resource")
                        .content(mapper.writeValueAsString(resourceObject))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

        mockMvc.perform(get("/resource/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated().withRoles("ADMIN", "USER"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.value", is("VALL")))
                .andExpect(jsonPath("$.path", is("PATH")));
    }

    @WithMockUser()
    @Test
    void createResourceObject() throws Exception {
        int id = 33;
        ResourceObject resourceObject = new ResourceObject(id, "VALL", "PATH");
        mockMvc.perform(post("/resource").content(mapper.writeValueAsString(resourceObject))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated().withUsername("user"))
                .andExpect(authenticated().withRoles("USER"))
                .andExpect(content().string(String.valueOf(id)));
    }
}