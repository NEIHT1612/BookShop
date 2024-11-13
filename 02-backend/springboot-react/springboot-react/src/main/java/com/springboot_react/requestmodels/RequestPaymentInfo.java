package com.springboot_react.requestmodels;

import lombok.Data;

@Data
public class RequestPaymentInfo {
    private int amount;
    private String currency;
    private String receiptEmail;
}
