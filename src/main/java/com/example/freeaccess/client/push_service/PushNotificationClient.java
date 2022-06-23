package com.example.freeaccess.client.push_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("push-notification")
public interface PushNotificationClient {

    @PostMapping("/push-notification/send")
    void send(PushNotificationRequest request);

}
