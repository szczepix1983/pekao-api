package pl.pekao24.api.domains.account.dto;

import java.io.Serializable;
import java.util.List;

public class AccountSummaryResponse implements Serializable {
    public String id;
    public String accountNumber;
    public String displayName;
    public float balance;
    public float availableBalance;
    public String currency;
    public String relation;
    public String typeCode;
    public float overdraftLimit;
    public boolean defaultAccount;
    public String openingDate;
    public String historyAvailabilityDate;
    public String group;
    public List<String> availableOptions;
    public String productType;
}
