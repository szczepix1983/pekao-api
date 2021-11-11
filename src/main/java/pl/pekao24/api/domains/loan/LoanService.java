package pl.pekao24.api.domains.loan;

import pl.pekao24.api.Api;
import pl.pekao24.api.domains.loan.dto.LoanOfferResponse;
import pl.pekao24.api.http.HttpClient;
import pl.pekao24.api.auth.AuthInterceptor;
import pl.pekao24.api.domains.loan.dto.LoanDetailsResponse;
import pl.pekao24.api.domains.loan.dto.LoanSummaryResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoanService {

    protected HttpClient client;

    public LoanService(final AuthInterceptor interceptor) {
        this.client = new HttpClient(interceptor);
    }

    public LoanOfferResponse offer() {
        return client.get(
                Api.LOAN_OFFER,
                LoanOfferResponse.class).getBody();
    }

    public LoanSummaryResponse[] summary() {
        return client.get(
                Api.LOAN_SUMMARY,
                LoanSummaryResponse[].class).getBody();
    }

    public LoanDetailsResponse details(final String id) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        return client.get(
                Api.LOAN_DETAILS,
                params,
                LoanDetailsResponse.class).getBody();
    }
}
