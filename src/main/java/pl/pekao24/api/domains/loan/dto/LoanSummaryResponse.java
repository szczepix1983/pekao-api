package pl.pekao24.api.domains.loan.dto;

import java.io.Serializable;

public class LoanSummaryResponse implements Serializable {
    public String id;
    public String productType;
    public String accountNumber;
    public String systemName;
    public String ownName;
    public String displayName;
    public String interestType;
    public float interestRate;
    public float nextInstallmentAmount;
    public String nextInstallmentDate;
    public int nextInstallmentDays;
    public String currency;
    public float initialAmount;
    public String endDate;
    public float pastDueTotalAmount;
    public float remainingAmount;
    public String startDate;
    public String customerToLoanRelationship;
}
