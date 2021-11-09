package pl.pekao24.api.domains.moneytransfer.dto;

import java.io.Serializable;
import java.util.List;

public class MoneyTransferPredefinedListResponse implements Serializable {
    public List<MoneyTransferPredefined> predefinedDomestics;
}
