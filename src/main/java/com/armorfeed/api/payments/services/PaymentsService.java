package com.armorfeed.api.payments.services;

import com.armorfeed.api.payments.providers.feignclients.UsersServiceFeignClient;
import com.armorfeed.api.payments.providers.feignclients.PaymentsServiceFeignClient;
import com.armorfeed.api.payments.repositories.PaymentRepository;
import com.armorfeed.api.payments.security.FeignRequestInterceptor;
import com.armorfeed.api.payments.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentsService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    EnhancedModelMapper enhancedModelMapper;

    @Autowired
    FeignRequestInterceptor feignRequestInterceptor;

    @Autowired
    UsersServiceFeignClient usersServiceFeignClient;

    @Autowired
    PaymentsServiceFeignClient paymentsServiceFeignClient;


}
