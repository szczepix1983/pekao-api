package pl.pekao24.api.domains.account.dto;

import java.io.Serializable;

public class AccountLimitsResponse implements Serializable {
    public float dailyLimit;
    public float monthlyLimit;
    public float westernUnionDailyLimit;
    public float westernUnionTransactionLimit;
}
