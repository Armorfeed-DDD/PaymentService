package com.armorfeed.api.payments.services;

import com.armorfeed.api.payments.domain.entities.Payment;
import com.armorfeed.api.payments.domain.enums.PaymentStatus;
import com.armorfeed.api.payments.providers.feignclients.UsersServiceFeignClient;
import com.armorfeed.api.payments.providers.feignclients.PaymentsServiceFeignClient;
import com.armorfeed.api.payments.repositories.PaymentRepository;
import com.armorfeed.api.payments.resources.CreatePaymentResource;
import com.armorfeed.api.payments.resources.PaymentResponse;
import com.armorfeed.api.payments.resources.UpdatePaymentResource;
import com.armorfeed.api.payments.security.FeignRequestInterceptor;
import com.armorfeed.api.payments.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<?> save(CreatePaymentResource payment) {
        List<String> errors = new LinkedList<>();
        if(usersServiceFeignClient.validateCustomerId(payment.getCustomerId()) == false) {
            errors.add("Customer with id " + payment.getCustomerId() + " does not exist");
            log.info("Customer with id {} does not exist", payment.getCustomerId());
        }
        if(usersServiceFeignClient.validateEnterpriseId(payment.getEnterpriseId()) == false) {
            errors.add("Enterprise with id " + payment.getEnterpriseId() + " does not exist");
            log.info("Enterprise with id {} does not exist", payment.getEnterpriseId());
        }
        if(errors.isEmpty() == false) {
            return ResponseEntity.badRequest().body(errors);
        }
        Payment newPayment = enhancedModelMapper.map(payment, Payment.class);
        Payment result = paymentRepository.save(newPayment);
        log.info("New payment was successfully created");
        return ResponseEntity.ok().body(result);
    }

    public List<PaymentResponse> getAllPaymentByCustomerId(Long customerId) {
        List<Payment> payments = paymentRepository.findPaymentCustomerId(customerId);
        List<PaymentResponse> result = enhancedModelMapper.mapList(payments, PaymentResponse.class);
        return result;
    }


    public List<PaymentResponse> getAllPaymentByEnterpriseId(Long enterpriseId) {
        List<Payment> payments = paymentRepository.findPaymentByEnterpriseId(enterpriseId);
        List<PaymentResponse> result = enhancedModelMapper.mapList(payments, PaymentResponse.class);
        return result;
    }

    public ResponseEntity<?>updatePayment(UpdatePaymentResource updatePaymentResource,String bearerToken){
        Optional<Payment> paymentResult=paymentRepository.findById(updatePaymentResource.getId());
        if(paymentResult.isEmpty()){
            log.info("Payment with id {} doesn´t exist", updatePaymentResource.getId());
            return ResponseEntity.badRequest().body("Payment with id given does not exist");
        }
        Payment currentPayment=paymentResult.get();
        log.info("A payment with id {} was found",currentPayment.getId());
        currentPayment.setStatus(PaymentStatus.valueOf(updatePaymentResource.getStatus()));
        Payment updatedPayment=paymentRepository.saveAndFlush(currentPayment);
        log.info("payment was updated succesfully");
        return ResponseEntity.ok().body(updatedPayment);
    }
}
