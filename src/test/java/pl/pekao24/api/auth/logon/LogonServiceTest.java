package pl.pekao24.api.auth.logon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pekao24.api.Api;
import pl.pekao24.api.auth.logon.dto.LogonRequest;
import pl.pekao24.api.http.HttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class LogonServiceTest {

    @Test
    void logon() {
        LogonServiceMock logonServiceMock = new LogonServiceMock();
        when(logonServiceMock.getClient().post(eq(Api.AUTHENTICATION_CUSTOMER_LOGON), any(LogonRequest.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok(""));

        boolean response = logonServiceMock.logon("login", "abc", "101");

        assertThat(response).isTrue();
    }

    static class LogonServiceMock extends LogonService {

        public LogonServiceMock() {
            super(mock(LogonInterceptor.class));

            client = mock(HttpClient.class);
        }

        HttpClient getClient() {
            return client;
        }
    }
}