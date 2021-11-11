package pl.pekao24.api.domains.account.dto;

import java.io.Serializable;
import java.util.List;

public class AccountTransactionsResponse implements Serializable {
    public long id;
    public boolean today;
    public boolean yesterday;
    public int rootCatId;
    public int catId;
    public String categoryColor;
    public String categoryIconName;
    public String categoryName;
    public String account;
    public String productDisplayName;
    public String partyName;
    public List<String> partyAddress;
    public float amount;
    public float amountInPLN;
    public float orgAmount;
    public String orgCurrency;
    public String currency;
    public String descriptionFirstLine;
    public String descriptionSecondLine;
    public String bookingDate;
    public String valueDate;
    public String transactionDate;
    public String reference;
    public String typeName;
    public String type;
    public String incomeExpenseFlag;
    public boolean internal;
    public String viewType;
    public boolean includedToAnalysis;
    public String typeGroupId;
    public String accountOwnerName;
    public String maskedCardNumber;
    public String checksum;
}
