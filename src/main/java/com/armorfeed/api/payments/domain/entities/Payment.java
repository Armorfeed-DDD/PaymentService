package com.armorfeed.api.payments.domain.entities;

import lombok.*;

import javax.persistence.*;

import com.armorfeed.api.payments.domain.enums.PaymentStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@With
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Long shipments_id;

    @Column(nullable = false)
    private Long operation_number;

    @Column(nullable = false)
    private boolean collected;

    @Column(nullable = false, name = "users_enterprise_id")
    private Long enterpriseId;

    @Column(nullable = false, name = "users_customer_id")
    private Long customerId;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

}
