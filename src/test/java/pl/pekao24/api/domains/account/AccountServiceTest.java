package pl.pekao24.api.domains.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pekao24.api.Api;
import pl.pekao24.api.auth.AuthInterceptor;
import pl.pekao24.api.domains.account.dto.AccountCardDebitSummaryResponse;
import pl.pekao24.api.domains.account.dto.AccountLimitsResponse;
import pl.pekao24.api.domains.account.dto.AccountSummaryResponse;
import pl.pekao24.api.domains.account.dto.AccountTransactionsResponse;
import pl.pekao24.api.http.HttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AccountServiceTest {

    @Test
    void summary() {
        AccountServiceMock accountService = new AccountServiceMock();
        AccountSummaryResponse[] responseMock = new AccountSummaryResponse[]{};
        when(accountService.getClient().get(eq(Api.ACCOUNT_SUMMARY), eq(AccountSummaryResponse[].class)))
                .thenReturn(ResponseEntity.ok(responseMock));

        AccountSummaryResponse[] response = accountService.summary();

        assertThat(response).isEqualTo(responseMock);
    }

    @Test
    void cardDebitSummary() {
        AccountServiceMock accountService = new AccountServiceMock();
        AccountCardDebitSummaryResponse[] responseMock = new AccountCardDebitSummaryResponse[]{};
        when(accountService.getClient().get(eq(Api.ACCOUNT_CARD_DEBIT_SUMMARY), eq(AccountCardDebitSummaryResponse[].class)))
                .thenReturn(ResponseEntity.ok(responseMock));

        AccountCardDebitSummaryResponse[] response = accountService.cardDebitSummary();

        assertThat(response).isEqualTo(responseMock);
    }

    @Test
    void transactions() {
        AccountServiceMock accountService = new AccountServiceMock();
        AccountTransactionsResponse[] responseMock = new AccountTransactionsResponse[]{};
        when(accountService.getClient().get(eq(Api.ACCOUNT_TRANSACTIONS), any(), eq(AccountTransactionsResponse[].class)))
                .thenReturn(ResponseEntity.ok(responseMock));
        String accountIds = "acountIds";
        String dateFrom = "dateFrom";
        String dateTo = "dateTo";

        AccountTransactionsResponse[] response = accountService.transactions(accountIds, dateFrom, dateTo, true);

        assertThat(response).isEqualTo(responseMock);
    }

    @Test
    void limits() {
        AccountServiceMock accountService = new AccountServiceMock();
        AccountLimitsResponse responseMock = new AccountLimitsResponse();
        when(accountService.getClient().get(eq(Api.ACCOUNT_LIMITS), any(), eq(AccountLimitsResponse.class)))
                .thenReturn(ResponseEntity.ok(responseMock));
        String accountIds = "acountIds";

        AccountLimitsResponse response = accountService.limits(accountIds);

        assertThat(response).isEqualTo(responseMock);
    }

    static class AccountServiceMock extends AccountService {

        public AccountServiceMock() {
            super(mock(AuthInterceptor.class));

            client = mock(HttpClient.class);
        }

        HttpClient getClient() {
            return client;
        }
    }
}