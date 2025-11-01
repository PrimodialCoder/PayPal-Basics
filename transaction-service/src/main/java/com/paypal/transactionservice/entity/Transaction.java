package com.paypal.transactionservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Table(name = "transaction")
@Data
public class Transaction extends BaseModel{

    @Column(nullable = false)
    private long senderId;

    @Column(nullable = false)
    private long recipientId;

    @Column(nullable = false)
    @Positive(message= "Amount must be positive")
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String status;

    public Transaction() {}

    public Transaction(long senderId, long recipientId, Double amount, LocalDateTime timestamp, String status) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;

    }

    @PrePersist
    public void prePersist(){
        if(timestamp ==null){
            timestamp = LocalDateTime.now();
        }
        if(status == null){
            status = "PENDING";
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id =" + super.getId() +
                ", senderId = '" + senderId + '\'' +
                ", receiverId = '" + recipientId + '\'' +
                ", amount = " + amount +
                ", timestamp = " + timestamp +
                ", status = '" + status + '\'' +
                "} ";
    }
}
