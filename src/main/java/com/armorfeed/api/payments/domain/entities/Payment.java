package com.armorfeed.api.payments.domain.entities;

import lombok.*;

import javax.persistence.*;

import com.armorfeed.api.payments.domain.enums.PaymentStatus;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@With
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment implements Serializable {
    
}
