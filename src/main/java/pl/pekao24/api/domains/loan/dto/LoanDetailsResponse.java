package pl.pekao24.api.domains.loan.dto;

import java.io.Serializable;

public class LoanDetailsResponse implements Serializable {
    public float remainingCapital;
    public float paidCapital;
    public float paidInterest;
    public String autodebitAccountNumber;
    public String contractSignDate;
    public float margin;
    public float baseRate;
    public float pastDueTotalAmount;
    public String borrower;
    public String currency;
    public String installmentType;
    public String notificationType;
    public float lastPaymentAmount;
    public float nextPaymentAmount;
    public float initialAmount;
    public float remainingAmount;
    public boolean allOwnersHaveActiveP24;
}
