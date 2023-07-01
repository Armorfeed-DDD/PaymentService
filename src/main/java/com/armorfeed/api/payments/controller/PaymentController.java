package com.armorfeed.api.payments.controller;

import com.armorfeed.api.payments.resources.CreatePaymentResource;
import com.armorfeed.api.payments.resources.PaymentResponse;
import com.armorfeed.api.payments.resources.UpdatePaymentResource;
import com.armorfeed.api.payments.services.PaymentsService;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/payments/")
public class PaymentController {

    @Autowired
    PaymentsService paymentsService;

    @PostMapping
    public ResponseEntity<?> savePayment(@RequestBody @Valid CreatePaymentResource payment, BindingResult validationResult){
        if(validationResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(validationResult.getAllErrors()
                            .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .collect(Collectors.toList()));
        }
        return paymentsService.save(payment);
    }


    @PatchMapping
    public ResponseEntity<?> patchPaymentStatus(
            @RequestBody @Valid UpdatePaymentResource updatePaymentResource,
            BindingResult bindingResult,
            @RequestHeader("Authorization") String bearerToken
    ){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
        }
        return this.paymentsService.updatePayment(updatePaymentResource,bearerToken);
    }

    @GetMapping("/customers/{customerId}")
    public List<PaymentResponse> getAllPaymentsByCustomerId(@PathVariable("customerId") Long customerId) {
        return paymentsService.getAllPaymentByCustomerId(customerId);
    }

    @GetMapping("/enterprises/{enterpriseId}")
    public List<PaymentResponse> getAllPaymentsByEnterpriseId(@PathVariable("enterpriseId") Long enterprisesId) {
        return paymentsService.getAllPaymentByEnterpriseId(enterprisesId);
    }
}
