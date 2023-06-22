package com.armorfeed.api.payments.controller;

import com.armorfeed.api.payments.domain.entities.Payment;
import com.armorfeed.api.payments.resources.UpdatePaymentResource;
import com.armorfeed.api.payments.services.PaymentsService;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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




    @PatchMapping
    public ResponseEntity<?> patchPaymentStatus(
            @RequestBody @Valid UpdatePaymentResource updatePaymentResource,
            BindingResult bindingResult,
            @RequestHeader("Authorization") String bearerToken
    ){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().stream().map((error) -> error.getDefaultMessage()).collect(Collectors.toList()));
        }
        return this.paymentsService.updatePayment(updatePaymentResource,bearerToken);
    }
}
