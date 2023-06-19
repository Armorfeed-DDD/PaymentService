package com.armorfeed.api.payments.repositories;

import com.armorfeed.api.payments.domain.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

}
