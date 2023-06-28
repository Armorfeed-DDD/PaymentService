package com.armorfeed.api.payments.providers.feignclients.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNotificationRequest {
    private String title;
    private String message;
    private String sender;
    private Long customerId;
    private Long enterpriseId;
}
