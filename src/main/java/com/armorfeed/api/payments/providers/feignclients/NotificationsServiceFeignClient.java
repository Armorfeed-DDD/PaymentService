package com.armorfeed.api.payments.providers.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.armorfeed.api.payments.providers.feignclients.dtos.CreateNotificationRequest;

@FeignClient(name = "notifications-service", value = "notifications-service", url = "http://localhost:8081")
public interface NotificationsServiceFeignClient {
    
    @PostMapping("/api/v1/notifications/create-notification")
    public ResponseEntity<String> createNotification(@RequestBody CreateNotificationRequest request);
}
