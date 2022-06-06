package com.example.freeaccess.controller;

import com.example.freeaccess.domain.notice.NoticeDTO;
import com.example.freeaccess.domain.notice.Url;
import com.example.freeaccess.service.notice.PushNotificationClient;
import com.example.freeaccess.service.notice.SendPushNotification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = {"dev"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class NoticeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PushNotificationClient pushNotificationClient;

    @MockBean
    private SendPushNotification sendPushNotification;

    public static final String PATH = "/school/notice";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Url> urls;
    private NoticeDTO expectedNoticeDTO;

    @BeforeEach
    void setUp() {
        urls = Arrays.asList(new Url("g1.com.br", "Globo"), new Url("ge.com.br", "Globo Esporte"));
        expectedNoticeDTO = new NoticeDTO(null, "1", "1", urls, LocalDate.now(), "1");
    }

    private MockHttpServletResponse performPost(NoticeDTO expected) throws Exception {
        String json = objectMapper.writeValueAsString(expected);
        RequestBuilder request = MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON).content(json);
        return this.mockMvc.perform(request).andReturn().getResponse();
    }

    private MockHttpServletResponse performDelete(Integer id) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(PATH + "/" + id);
        return this.mockMvc.perform(request).andReturn().getResponse();
    }

    private MockHttpServletResponse performGetById(Integer id) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(PATH + "/" + id);
        return this.mockMvc.perform(request).andReturn().getResponse();
    }

    private MockHttpServletResponse performGetAllPageable() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(PATH);
        return this.mockMvc.perform(request).andReturn().getResponse();
    }

    private MockHttpServletResponse performPut(NoticeDTO expected) throws Exception {
        String json = objectMapper.writeValueAsString(expected);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(PATH + "/" + expected.getId()).contentType(MediaType.APPLICATION_JSON).content(json);
        return this.mockMvc.perform(request).andReturn().getResponse();
    }

    private NoticeDTO saveANoticeSuccessfully(NoticeDTO expected) throws Exception {
        MockHttpServletResponse response = this.performPost(expected);
        NoticeDTO returned = objectMapper.readValue(response.getContentAsString(), NoticeDTO.class);

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        Assertions.assertNotNull(returned.getId());
        Assertions.assertEquals(expected, returned);
        return returned;
    }

    @Test
    public void shouldSaveNoticeSuccessfully() throws Exception {
        saveANoticeSuccessfully(this.expectedNoticeDTO);
    }

    @Test
    public void shouldPushMessageToNotificationServiceOnSaveNotice() throws Exception {
        Mockito.doNothing().when(sendPushNotification).execute(Mockito.any(NoticeDTO.class));
        Mockito.doNothing().when(pushNotificationClient).send(Mockito.any(NoticeDTO.class));

        this.performPost(this.expectedNoticeDTO);
        Mockito.verify(sendPushNotification).execute(Mockito.any(NoticeDTO.class));
    }

    @Test
    public void shouldNotSaveANoticeWithoutRequiredFieldsAndReturnBadRequest() throws Exception {
        expectedNoticeDTO = new NoticeDTO();

        MockHttpServletResponse response = this.performPost(this.expectedNoticeDTO);
        List<String> exceptions = this.objectMapper.readValue(response.getContentAsString(), List.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        Assertions.assertTrue(exceptions.stream().anyMatch(exception -> exception.contains("description")));
        Assertions.assertTrue(exceptions.stream().anyMatch(exception -> exception.contains("title")));

    }

    @Test
    public void shouldDeleteANoticeById() throws Exception {
        NoticeDTO returned = saveANoticeSuccessfully(this.expectedNoticeDTO);
        MockHttpServletResponse mockHttpServletResponse = this.performDelete(returned.getId());
        Assertions.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
    }

    @Test
    public void shouldFindANoticeById() throws Exception {
        NoticeDTO returned = saveANoticeSuccessfully(this.expectedNoticeDTO);

        MockHttpServletResponse response = this.performGetById(returned.getId());
        returned = objectMapper.readValue(response.getContentAsString(), NoticeDTO.class);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(returned, this.expectedNoticeDTO);
    }

    @Test
    public void shouldFindAllPageable() throws Exception {
        saveANoticeSuccessfully(this.expectedNoticeDTO);
        saveANoticeSuccessfully(this.expectedNoticeDTO);
        saveANoticeSuccessfully(this.expectedNoticeDTO);

        MockHttpServletResponse response = this.performGetAllPageable();
        Map page = this.objectMapper.readValue(response.getContentAsString(), Map.class);

        Assertions.assertEquals(page.get("totalElements"), 3);
        Assertions.assertEquals(page.get("totalPages"), 1);
    }

    @Test
    public void shouldUpdateNoticeSuccessfully() throws Exception {
        NoticeDTO expected = this.saveANoticeSuccessfully(this.expectedNoticeDTO);
        expected.setDescription("Nova description");
        MockHttpServletResponse response = this.performPut(expected);
        NoticeDTO returned = objectMapper.readValue(response.getContentAsString(), NoticeDTO.class);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expected, returned);
    }

}