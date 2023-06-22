package com.armorfeed.api.payments.resources;

import com.armorfeed.api.payments.domain.enums.PaymentStatus;
import com.armorfeed.api.payments.utils.ValidatePaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
public class UpdatePaymentResource {

    @NotNull(message="Payment id must not be null")
    private Long id;
    @NotNull(message = "Status must not be null")
    @ValidatePaymentStatus(enumClass = PaymentStatus.class,
            message = "Payment status is not valid")
    private String status;
}
