package org.flinkpoc.frauddetection.model;

public class Alert {
    private String transsactionId;
    private String amount;

    public String getId() {
        return transsactionId;
    }

    public void setId(String id) {
        this.transsactionId = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "transsactionId=" + transsactionId +
                ", amount=" + amount +
                '}';
    }
}
