package pl.pekao24.api.domains.moneytransfer;

import pl.pekao24.api.Api;
import pl.pekao24.api.auth.AuthInterceptor;
import pl.pekao24.api.domains.moneytransfer.dto.MoneyTransferHistoryResponse;
import pl.pekao24.api.http.HttpClient;
import pl.pekao24.api.domains.moneytransfer.dto.MoneyTransferPredefinedListResponse;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransferService {

    protected HttpClient client;

    public MoneyTransferService(final AuthInterceptor interceptor) {
        this.client = new HttpClient(interceptor);
    }

    public MoneyTransferPredefinedListResponse predefinedList() {
        return client.get(
                Api.MONEY_TRANSFER_PREDEFINED_LIST,
                MoneyTransferPredefinedListResponse.class).getBody();
    }

    public MoneyTransferHistoryResponse history() {
        return client.get(
                Api.MONEY_TRANSFER_HISTORY,
                MoneyTransferHistoryResponse.class).getBody();
    }
}
