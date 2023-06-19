package com.armorfeed.api.payments.providers.feignclients;

import com.armorfeed.api.payments.providers.feignclients.dtos.AuthTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-service", value = "users-service", url = "http://localhost:8090")
public interface UsersServiceFeignClient {

}
