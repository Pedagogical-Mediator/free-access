package com.example.freeaccess.service.notice;

import com.example.freeaccess.client.push_service.PushNotificationClient;
import com.example.freeaccess.client.push_service.PushNotificationRequest;
import com.example.freeaccess.domain.notice.NoticeDTO;
import org.springframework.stereotype.Service;

@Service
public class SendPushNotification implements NoticeAction {

    private final PushNotificationClient pushNotificationClient;

    public SendPushNotification(PushNotificationClient pushNotificationClient) {
        this.pushNotificationClient = pushNotificationClient;
    }

    @Override
    public void execute(NoticeDTO noticeDTO) {
        this.pushNotificationClient.send(new PushNotificationRequest(noticeDTO));
    }
}
