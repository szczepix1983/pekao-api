package pl.pekao24.api.domains.dictionary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pekao24.api.Api;
import pl.pekao24.api.auth.AuthInterceptor;
import pl.pekao24.api.domains.dictionary.dto.DictionaryBankDataGetRequest;
import pl.pekao24.api.domains.dictionary.dto.DictionaryBankDataGetResponse;
import pl.pekao24.api.domains.dictionary.dto.DictionaryTransactionCategoriesResponse;
import pl.pekao24.api.http.HttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DictionaryServiceTest {

    @Test
    void transactionCategories() {
        DictionaryServiceMock dictionaryServiceMock = new DictionaryServiceMock();
        DictionaryTransactionCategoriesResponse responseMock = new DictionaryTransactionCategoriesResponse();
        when(dictionaryServiceMock.getClient().get(eq(Api.DICTIONARY_TRANSACTION_CATEGORIES), eq(DictionaryTransactionCategoriesResponse.class)))
                .thenReturn(ResponseEntity.ok(responseMock));

        DictionaryTransactionCategoriesResponse response = dictionaryServiceMock.transactionCategories();

        assertThat(response).isEqualTo(responseMock);
    }

    @Test
    void bankDataGet() {
        DictionaryServiceMock dictionaryServiceMock = new DictionaryServiceMock();
        DictionaryBankDataGetResponse responseMock = new DictionaryBankDataGetResponse();
        when(dictionaryServiceMock.getClient().post(eq(Api.DICTIONARY_BANK_DATA_GET), any(DictionaryBankDataGetRequest.class), eq(DictionaryBankDataGetResponse.class)))
                .thenReturn(ResponseEntity.ok(responseMock));

        DictionaryBankDataGetResponse response = dictionaryServiceMock.bankDataGet("accountNumber");

        assertThat(response).isEqualTo(responseMock);
    }

    static class DictionaryServiceMock extends DictionaryService {

        public DictionaryServiceMock() {
            super(mock(AuthInterceptor.class));

            client = mock(HttpClient.class);
        }

        HttpClient getClient() {
            return client;
        }
    }
}