package com.armorfeed.api.payments.providers.feignclients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "vehicles-service", value = "vehicles-service", url = "http://localhost:8089")
public interface PaymentsServiceFeignClient {

}
