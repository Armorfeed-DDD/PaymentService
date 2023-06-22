package com.armorfeed.api.payments.domain.enums;

public enum PaymentStatus {
    REGISTERED("REGISTERED"),
    COLLECTED("COLLECTED");

    private String status;
    private PaymentStatus(String status){
        this.status=status;
    }
    public String getStatus(){
        return this.status;
    }

}
