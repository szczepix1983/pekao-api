package pl.pekao24.api.domains.moneytransfer.dto;

import java.io.Serializable;

public class MoneyTransferPredefined implements Serializable {
    public long id;
    public String name;
    public boolean authRequired;
    public String checksum;
    public String title;
    public MoneyTransferPredefinedBeneficiary beneficiary;
}
