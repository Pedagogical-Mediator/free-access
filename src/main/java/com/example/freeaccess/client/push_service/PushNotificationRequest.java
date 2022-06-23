package com.example.freeaccess.client.push_service;

import com.example.freeaccess.domain.news.NewsDTO;
import com.example.freeaccess.domain.notice.NoticeDTO;

public class PushNotificationRequest {

    private String title;
    private String message;

    public PushNotificationRequest(String title, String messageBody) {
        this.title = title;
        this.message = messageBody;
    }

    public PushNotificationRequest(NoticeDTO noticeDTO) {
        this(noticeDTO.getTitle(), noticeDTO.getDescription());
    }

    public PushNotificationRequest(NewsDTO newsDTO) {
        this(newsDTO.getTitle(), newsDTO.getDescription());
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

}
