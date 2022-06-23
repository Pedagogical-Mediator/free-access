package com.example.freeaccess.service.news;

import com.example.freeaccess.client.push_service.PushNotificationClient;
import com.example.freeaccess.client.push_service.PushNotificationRequest;
import com.example.freeaccess.domain.news.NewsDTO;
import com.example.freeaccess.domain.notice.NoticeDTO;
import com.example.freeaccess.service.notice.NoticeAction;
import org.springframework.stereotype.Service;

@Service
public class SendPushNotification {

    private final PushNotificationClient pushNotificationClient;

    public SendPushNotification(PushNotificationClient pushNotificationClient) {
        this.pushNotificationClient = pushNotificationClient;
    }

    public void execute(NewsDTO newsDTO) {
        this.pushNotificationClient.send(new PushNotificationRequest(newsDTO));
    }
}
