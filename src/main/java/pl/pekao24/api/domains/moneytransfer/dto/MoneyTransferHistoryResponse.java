package pl.pekao24.api.domains.moneytransfer.dto;

import java.io.Serializable;
import java.util.List;

public class MoneyTransferHistoryResponse implements Serializable {
    public List<MoneyTransferHistory> domesticTransfers;
}
