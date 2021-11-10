package pl.pekao24.api.domains.moneytransfer.dto;

import java.io.Serializable;

public class MoneyTransferHistory implements Serializable {
    public String sourceAccountNumber;
    public float transferAmount;
    public String currency;
    public String title;
    public MoneyTransferHistoryBeneficiary beneficiary;
}
