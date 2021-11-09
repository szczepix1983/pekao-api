package pl.pekao24.api.domains.account.dto;

import java.io.Serializable;
import java.util.List;

public class AccountCardDebitSummaryResponse implements Serializable {
    public String productType;
    public String number;
    public String id;
    public String pictureId;
    public String relatedAccountNumber;
    public String systemName;
    public String displayName;
    public String accountType;
    public String dateOfCardStatusChanged;
    public String type;
    public boolean activated;
    public boolean onlineDataAvailable;
    public String expiryDate;
    public String embossedName;
    public String status;
    public String cardOrigin;
    public String relationToUser;
    public List<AccountCardDebitAvailableOperation> availableOperations;
}
