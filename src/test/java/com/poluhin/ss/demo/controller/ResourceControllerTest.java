package com.poluhin.ss.demo.controller;

import com.poluhin.ss.demo.domain.model.ResourceObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResourceControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    private final ObjectMapper mapper = new ObjectMapper();

    @WithMockUser(roles = "USER")
    @Test
    public void getResourceObject() throws Exception {
        ResourceObject resourceObject = new ResourceObject("1", "VALL", "PATH");
//        webTestClient.post().uri("/resource")
//                        .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(mapper.writeValueAsString(resourceObject))
//                .exchange()
//                .expectStatus().isOk();
                //.andExpect(content().string("1"));

        webTestClient.get()
                .uri("/resource/1")
                .exchange()
                .expectStatus().isOk();
    }

    @WithMockUser(roles = {"ADMIN", "USER"})
    @Test
    void createResourceObject() throws Exception {
        String id = "33";
        ResourceObject resourceObject = new ResourceObject(id, "VALL", "PATH");
        webTestClient.post()
                .uri("/resource")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(resourceObject)
                .exchange()
                .expectStatus().isOk();
    }
}