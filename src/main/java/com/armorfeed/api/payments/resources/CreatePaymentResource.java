package com.armorfeed.api.payments.resources;

import com.armorfeed.api.payments.domain.enums.PaymentStatus;
import com.armorfeed.api.payments.utils.ValidatePaymentStatus;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
public class CreatePaymentResource {

    @NotNull
    @NotBlank
    @NotEmpty
    private BigDecimal amount;

    @NotNull
    private Date date;

    @NotNull
    private Long shipments_id;

    @NotNull
    private Long operation_number;

    @NotNull
    private boolean collected;

    @NotNull
    private Long enterpriseId;

    @NotNull
    private Long customerId;

    @ValidatePaymentStatus(
            enumClass = PaymentStatus.class,
            message = "Payment status must be any of the following values: [REGISTERED, COLLECTED]."
    )
    private PaymentStatus status;
}
