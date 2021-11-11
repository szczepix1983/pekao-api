package pl.pekao24.api.domains.account;

import pl.pekao24.api.domains.account.dto.AccountCardDebitSummaryResponse;
import pl.pekao24.api.domains.account.dto.AccountLimitsResponse;
import pl.pekao24.api.domains.account.dto.AccountSummaryResponse;
import pl.pekao24.api.domains.account.dto.AccountTransactionsResponse;
import pl.pekao24.api.auth.AuthInterceptor;
import pl.pekao24.api.Api;
import pl.pekao24.api.http.HttpClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {

    protected HttpClient client;

    public AccountService(final AuthInterceptor interceptor) {
        this.client = new HttpClient(interceptor);
    }

    public AccountSummaryResponse[] summary() {
        return client.get(
                Api.ACCOUNT_SUMMARY,
                AccountSummaryResponse[].class).getBody();
    }

    public AccountCardDebitSummaryResponse[] cardDebitSummary() {
        return client.get(
                Api.ACCOUNT_CARD_DEBIT_SUMMARY,
                AccountCardDebitSummaryResponse[].class).getBody();
    }

    public AccountTransactionsResponse[] transactions(final String accountIds,
                                                      final String dateFrom,
                                                      final String dateTo,
                                                      final boolean synchronize) {
        Map<String, String> params = new HashMap<>();
        params.put("accountIds", accountIds);
        params.put("dateFrom", dateFrom);
        params.put("dateTo", dateTo);
        params.put("synchronize", "" + synchronize);
        return client.get(
                Api.ACCOUNT_TRANSACTIONS,
                params,
                AccountTransactionsResponse[].class).getBody();
    }

    public AccountLimitsResponse limits(final String accountIds) {
        Map<String, String> params = new HashMap<>();
        params.put("accountIds", accountIds);
        return client.get(
                Api.ACCOUNT_LIMITS,
                params,
                AccountLimitsResponse.class).getBody();
    }
}
