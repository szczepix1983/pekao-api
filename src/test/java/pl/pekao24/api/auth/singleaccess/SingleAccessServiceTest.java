package pl.pekao24.api.auth.singleaccess;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pekao24.api.Api;
import pl.pekao24.api.auth.singleaccess.dto.SingleAccessResponse;
import pl.pekao24.api.http.HttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SingleAccessServiceTest {

    @Test
    void singleAccess() {
        SingleAccessServiceMock singleAccessServiceMock = new SingleAccessServiceMock();
        SingleAccessResponse responseMock = new SingleAccessResponse();
        when(singleAccessServiceMock.getClient().post(eq(Api.AUTHENTICATION_CUSTOMER_DFP_SINGLE_ACCESS), eq(SingleAccessResponse.class)))
                .thenReturn(ResponseEntity.ok(responseMock));

        SingleAccessResponse response = singleAccessServiceMock.singleAccess();

        assertThat(response).isEqualTo(responseMock);
    }

    static class SingleAccessServiceMock extends SingleAccessService {

        public SingleAccessServiceMock() {
            super(mock(SingleAccessInterceptor.class));

            client = mock(HttpClient.class);
        }

        HttpClient getClient() {
            return client;
        }
    }
}