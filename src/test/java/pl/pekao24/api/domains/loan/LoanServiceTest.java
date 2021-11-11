package pl.pekao24.api.domains.loan;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pekao24.api.Api;
import pl.pekao24.api.auth.AuthInterceptor;
import pl.pekao24.api.domains.loan.dto.LoanDetailsResponse;
import pl.pekao24.api.domains.loan.dto.LoanOfferResponse;
import pl.pekao24.api.domains.loan.dto.LoanSummaryResponse;
import pl.pekao24.api.http.HttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class LoanServiceTest {

    @Test
    void offer() {
        LoanServiceMock loanServiceMock = new LoanServiceMock();
        LoanOfferResponse responseMock = new LoanOfferResponse();
        when(loanServiceMock.getClient().get(eq(Api.LOAN_OFFER), eq(LoanOfferResponse.class)))
                .thenReturn(ResponseEntity.ok(responseMock));

        LoanOfferResponse response = loanServiceMock.offer();

        assertThat(response).isEqualTo(responseMock);
    }

    @Test
    void summary() {
        LoanServiceMock loanServiceMock = new LoanServiceMock();
        LoanSummaryResponse[] responseMock = new LoanSummaryResponse[]{};
        when(loanServiceMock.getClient().get(eq(Api.LOAN_SUMMARY), eq(LoanSummaryResponse[].class)))
                .thenReturn(ResponseEntity.ok(responseMock));

        LoanSummaryResponse[] response = loanServiceMock.summary();

        assertThat(response).isEqualTo(responseMock);
    }

    @Test
    void details() {
        LoanServiceMock loanServiceMock = new LoanServiceMock();
        LoanDetailsResponse responseMock = new LoanDetailsResponse();
        when(loanServiceMock.getClient().get(eq(Api.LOAN_DETAILS), any(), eq(LoanDetailsResponse.class)))
                .thenReturn(ResponseEntity.ok(responseMock));

        LoanDetailsResponse response = loanServiceMock.details("id");

        assertThat(response).isEqualTo(responseMock);
    }

    static class LoanServiceMock extends LoanService {

        public LoanServiceMock() {
            super(mock(AuthInterceptor.class));

            client = mock(HttpClient.class);
        }

        HttpClient getClient() {
            return client;
        }
    }
}