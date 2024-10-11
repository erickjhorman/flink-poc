package org.flinkpoc.frauddetection.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction  {

    private String transactionId;
    private long accountId;
    private short ipAddress;
    private String location;
    private String device;
    private BigDecimal amount;
    private Instant createdAt;
    private String transactionType; //withdrawals, transfers, deposits, credit card

    public Transaction(String transactionId, BigDecimal amount) {
        this.amount = amount;
        this.transactionId = transactionId;
    }
}
