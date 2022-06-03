package com.example.freeaccess.service.notice;

import com.example.freeaccess.domain.notice.NoticeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("push-notification")
public interface PushNotificationClient {

    @PostMapping("/push-notification/send")
    void send(NoticeDTO noticeDTO);

}
