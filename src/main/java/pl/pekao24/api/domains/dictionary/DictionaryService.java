package pl.pekao24.api.domains.dictionary;

import org.springframework.stereotype.Service;
import pl.pekao24.api.Api;
import pl.pekao24.api.auth.AuthInterceptor;
import pl.pekao24.api.domains.dictionary.dto.DictionaryBankDataGetRequest;
import pl.pekao24.api.domains.dictionary.dto.DictionaryBankDataGetResponse;
import pl.pekao24.api.domains.dictionary.dto.DictionaryTransactionCategoriesResponse;
import pl.pekao24.api.http.HttpClient;

@Service
public class DictionaryService {

    protected HttpClient client;

    public DictionaryService(final AuthInterceptor interceptor) {
        this.client = new HttpClient(interceptor);
    }

    public DictionaryTransactionCategoriesResponse transactionCategories() {
        return client.get(
                Api.DICTIONARY_TRANSACTION_CATEGORIES,
                DictionaryTransactionCategoriesResponse.class).getBody();
    }

    public DictionaryBankDataGetResponse bankDataGet(final String accountNumber) {
        DictionaryBankDataGetRequest request = new DictionaryBankDataGetRequest();
        request.accountNumber = accountNumber;

        return client.post(
                Api.DICTIONARY_BANK_DATA_GET,
                request,
                DictionaryBankDataGetResponse.class).getBody();
    }
}
