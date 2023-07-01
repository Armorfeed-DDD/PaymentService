package com.armorfeed.api.payments.repositories;

import com.armorfeed.api.payments.domain.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    @Query("SELECT P FROM Payment P WHERE P.customerId = :customerId")
    List<Payment> findPaymentCustomerId(@Param("customerId") Long customerId);
    @Query("SELECT P FROM Payment P WHERE P.enterpriseId = :enterpriseId")
    List<Payment> findPaymentByEnterpriseId(@Param("enterpriseId") Long enterpriseId);
}
