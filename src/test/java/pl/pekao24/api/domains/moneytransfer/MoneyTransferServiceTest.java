package pl.pekao24.api.domains.moneytransfer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pekao24.api.Api;
import pl.pekao24.api.auth.AuthInterceptor;
import pl.pekao24.api.domains.moneytransfer.dto.MoneyTransferHistoryResponse;
import pl.pekao24.api.domains.moneytransfer.dto.MoneyTransferPredefinedListResponse;
import pl.pekao24.api.http.HttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MoneyTransferServiceTest {

    @Test
    void predefinedList() {
        MoneyTransferServiceMock moneyTransferServiceMock = new MoneyTransferServiceMock();
        MoneyTransferPredefinedListResponse responseMock = new MoneyTransferPredefinedListResponse();
        when(moneyTransferServiceMock.getClient().get(eq(Api.MONEY_TRANSFER_PREDEFINED_LIST), eq(MoneyTransferPredefinedListResponse.class)))
                .thenReturn(ResponseEntity.ok(responseMock));

        MoneyTransferPredefinedListResponse response = moneyTransferServiceMock.predefinedList();

        assertThat(response).isEqualTo(responseMock);
    }

    @Test
    void history() {
        MoneyTransferServiceMock moneyTransferServiceMock = new MoneyTransferServiceMock();
        MoneyTransferHistoryResponse responseMock = new MoneyTransferHistoryResponse();
        when(moneyTransferServiceMock.getClient().get(eq(Api.MONEY_TRANSFER_HISTORY), eq(MoneyTransferHistoryResponse.class)))
                .thenReturn(ResponseEntity.ok(responseMock));

        MoneyTransferHistoryResponse response = moneyTransferServiceMock.history();

        assertThat(response).isEqualTo(responseMock);
    }

    static class MoneyTransferServiceMock extends MoneyTransferService {

        public MoneyTransferServiceMock() {
            super(mock(AuthInterceptor.class));

            client = mock(HttpClient.class);
        }

        HttpClient getClient() {
            return client;
        }
    }
}