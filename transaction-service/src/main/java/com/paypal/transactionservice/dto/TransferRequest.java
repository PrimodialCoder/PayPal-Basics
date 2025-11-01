package com.paypal.transactionservice.dto;

import lombok.Data;

@Data
public class TransferRequest {
    private Long senderId;
    private Long recipientId;
    private Double amount;

    public TransferRequest() {}

    public TransferRequest(Long senderId, Long recipientId, Double amount) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
    }
}
