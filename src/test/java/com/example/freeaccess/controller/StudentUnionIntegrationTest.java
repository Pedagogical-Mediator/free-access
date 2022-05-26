package com.example.freeaccess.controller;

import com.example.freeaccess.domain.student_union.StudentUnionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.io.UnsupportedEncodingException;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = {"dev"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class StudentUnionIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    public static final String PATH = "/school/student-union";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldSaveStudentUnionSuccessfully() throws Exception {
        StudentUnionDTO studentUnionDTO = new StudentUnionDTO(1, "http://localhost:8080", "image in base64", "description");

        String json = objectMapper.writeValueAsString(studentUnionDTO);
        RequestBuilder request = MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON).content(json);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        StudentUnionDTO returnStudentUnion = objectMapper.readValue(response.getContentAsString(), StudentUnionDTO.class);

        Assertions.assertEquals(201, response.getStatus());
        Assertions.assertNotNull(returnStudentUnion.getId());
        Assertions.assertEquals(studentUnionDTO, returnStudentUnion);
    }

    @Test
    public void shouldNotSaveAStudentUnionWithoutDescriptionAndReturnBadRequest() throws Exception {
        StudentUnionDTO studentUnionDTO = new StudentUnionDTO(1, "http://localhost:8080", "image in base64", null);

        String json = objectMapper.writeValueAsString(studentUnionDTO);
        RequestBuilder request = MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON).content(json);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    public void shouldUpdateStudentUnionSuccessfully() throws Exception {
        StudentUnionDTO studentUnionDTO = new StudentUnionDTO(1, "http://localhost:8080", "image in base64", "description");

        String json = objectMapper.writeValueAsString(studentUnionDTO);
        RequestBuilder request = MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON).content(json);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        Assertions.assertEquals(201, response.getStatus());

        StudentUnionDTO studentUnionDTOUpdated = new StudentUnionDTO(1, "http://localhost:8081", "image in base64 new", "new description");

        json = objectMapper.writeValueAsString(studentUnionDTOUpdated);
        request = MockMvcRequestBuilders.put(PATH).contentType(MediaType.APPLICATION_JSON).content(json);

        response = mockMvc.perform(request).andReturn().getResponse();
        StudentUnionDTO returnStudentUnion = objectMapper.readValue(response.getContentAsString(), StudentUnionDTO.class);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(returnStudentUnion.getId());
        Assertions.assertEquals(studentUnionDTOUpdated, returnStudentUnion);
    }

    @Test
    public void shouldGetStudentUnionSuccessfully() throws Exception {
        StudentUnionDTO studentUnionDTO = new StudentUnionDTO(1, "http://localhost:8080", "image in base64", "description");

        String json = objectMapper.writeValueAsString(studentUnionDTO);
        RequestBuilder request = MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON).content(json);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        StudentUnionDTO returnStudentUnion = objectMapper.readValue(response.getContentAsString(), StudentUnionDTO.class);

        Assertions.assertEquals(201, response.getStatus());
        Assertions.assertNotNull(returnStudentUnion.getId());
        Assertions.assertEquals(studentUnionDTO, returnStudentUnion);


        request = MockMvcRequestBuilders.get(PATH).accept(MediaType.APPLICATION_JSON);
        response = mockMvc.perform(request).andReturn().getResponse();

        returnStudentUnion = objectMapper.readValue(response.getContentAsString(), StudentUnionDTO.class);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(returnStudentUnion.getId());
        Assertions.assertEquals(studentUnionDTO, returnStudentUnion);


    }

}