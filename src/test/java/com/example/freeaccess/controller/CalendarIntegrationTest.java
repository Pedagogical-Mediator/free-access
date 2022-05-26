package com.example.freeaccess.controller;

import com.example.freeaccess.domain.calendar.CalendarDTO;
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
class CalendarIntegrationTest {

    public static final String PATH = "/school/calendar";
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldSaveCalendarSuccessfully() throws Exception {
        CalendarDTO calendarDTO = new CalendarDTO(1, "http://localhost:8080");

        String calendarDTOJSON = objectMapper.writeValueAsString(calendarDTO);
        RequestBuilder request = MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON).content(calendarDTOJSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        CalendarDTO returnCalendar = objectMapper.readValue(response.getContentAsString(), CalendarDTO.class);

        Assertions.assertEquals(201, response.getStatus());
        Assertions.assertNotNull(returnCalendar.getId());
        Assertions.assertEquals(calendarDTO, returnCalendar);
    }

    @Test
    public void shouldNotSaveACalendarWithoutDescriptionAndReturnBadRequestAndException() throws Exception {
        CalendarDTO calendarDTO = new CalendarDTO(1, null);

        String calendarDTOJSON = objectMapper.writeValueAsString(calendarDTO);
        RequestBuilder request = MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON).content(calendarDTOJSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    public void shouldNotSaveABullyingWhenABullyingWasAlreadyStored() throws Exception {
        CalendarDTO calendarDTO = new CalendarDTO(1, "http://localhost:8080");

        String bullyingInJSON = objectMapper.writeValueAsString(calendarDTO);
        RequestBuilder request = MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON).content(bullyingInJSON);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        Assertions.assertEquals(201, response.getStatus());

        response = mockMvc.perform(request).andReturn().getResponse();

        Assertions.assertEquals(400, response.getStatus());

    }

}