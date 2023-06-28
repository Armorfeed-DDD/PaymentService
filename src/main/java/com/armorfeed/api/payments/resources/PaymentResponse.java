package com.armorfeed.api.payments.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private Long id;
    private BigDecimal amount;
    private Date date;
    private Long shipments_id;
    private Long operation_number;
    private boolean collected;
    private Long enterpriseId;
    private Long customerId;
}
