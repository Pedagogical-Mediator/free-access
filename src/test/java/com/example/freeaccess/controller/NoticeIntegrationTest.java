package com.example.freeaccess.controller;

import com.example.freeaccess.domain.notice.NoticeDTO;
import com.example.freeaccess.domain.notice.Url;
import com.example.freeaccess.service.notice.PushNotificationClient;
import com.example.freeaccess.service.notice.SendPushNotification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


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

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        return response;
    }

    @Test
    public void shouldSaveNoticeSuccessfully() throws Exception {
        MockHttpServletResponse response = performPost(expectedNoticeDTO);
        NoticeDTO returned = objectMapper.readValue(response.getContentAsString(), NoticeDTO.class);

        Assertions.assertEquals(201, response.getStatus());
        Assertions.assertNotNull(returned.getId());
        Assertions.assertEquals(expectedNoticeDTO, returned);
    }

    @Test
    public void shouldPushMessageToNotificationServiceOnSaveNotice() throws Exception {
        Mockito.doNothing().when(sendPushNotification).execute(Mockito.any(NoticeDTO.class));
        Mockito.doNothing().when(pushNotificationClient).send(Mockito.any(NoticeDTO.class));

        performPost(expectedNoticeDTO);
        Mockito.verify(sendPushNotification).execute(Mockito.any(NoticeDTO.class));
    }

    @Test
    public void shouldNotSaveANoticeWithoutRequiredFieldsAndReturnBadRequest() throws Exception {
        NoticeDTO noticeDTO = new NoticeDTO();

        MockHttpServletResponse response = performPost(noticeDTO);
        List<String> exceptions = objectMapper.readValue(response.getContentAsString(), List.class);

        System.out.println(exceptions);

        Assertions.assertEquals(400, response.getStatus());
        Assertions.assertTrue(exceptions.stream().anyMatch(exception -> exception.contains("description")));
        Assertions.assertTrue(exceptions.stream().anyMatch(exception -> exception.contains("title")));

    }
}