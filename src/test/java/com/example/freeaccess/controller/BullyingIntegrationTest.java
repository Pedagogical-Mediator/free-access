package com.example.freeaccess.controller;

import com.example.freeaccess.domain.bullying.BullyingDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = {"dev"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BullyingIntegrationTest {

    public static final String PATH = "/school/bullying";
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldSaveBullyingSuccessfully() throws Exception {
        BullyingDTO bullyingDTO = new BullyingDTO(null, "This is the description of bullying information", "image in base64", "http://localhost:8080");

        String bullyingInJSON = objectMapper.writeValueAsString(bullyingDTO);
        RequestBuilder request = MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON).content(bullyingInJSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        BullyingDTO returnedBullying = objectMapper.readValue(response.getContentAsString(), BullyingDTO.class);

        Assertions.assertEquals(201, response.getStatus());
        Assertions.assertNotNull(returnedBullying.getId());
        Assertions.assertEquals(bullyingDTO, returnedBullying);
    }

    @Test
    public void shouldNotSaveABullyingWithoutDescriptionAndReturnBadRequestAndException() throws Exception {
        BullyingDTO bullyingDTO = new BullyingDTO(null, null, "image", "http://localhost:8080");

        String bullyingInJSON = objectMapper.writeValueAsString(bullyingDTO);
        RequestBuilder request = MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON).content(bullyingInJSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    public void shouldNotSaveABullyingWithoutFormURLAndReturnBadRequestAndException() throws Exception {
        BullyingDTO bullyingDTO = new BullyingDTO(null, "description", "image", null);

        String bullyingInJSON = objectMapper.writeValueAsString(bullyingDTO);
        RequestBuilder request = MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON).content(bullyingInJSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    public void shouldNotSaveABullyingWhenABullyingWasAlreadyStored() throws Exception {
        BullyingDTO bullyingDTO = new BullyingDTO(null, "This is the description of bullying information", "image in base64", "http://localhost:8080");

        String bullyingInJSON = objectMapper.writeValueAsString(bullyingDTO);
        RequestBuilder request = MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON).content(bullyingInJSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        Assertions.assertEquals(201, response.getStatus());

        response = mockMvc.perform(request).andReturn().getResponse();

        Assertions.assertEquals(400, response.getStatus());

    }

}