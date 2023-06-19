package com.armorfeed.api.payments.providers.feignclients.dtos;

import lombok.Getter;

@Getter
public class AuthTokenResponse {
    private boolean isValidToken;
    private String message;
}
