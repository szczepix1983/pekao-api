package pl.pekao24.api.auth.passwordmask;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pekao24.api.Api;
import pl.pekao24.api.auth.passwordmask.dto.PasswordMaskRequest;
import pl.pekao24.api.auth.passwordmask.dto.PasswordMaskResponse;
import pl.pekao24.api.http.HttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PasswordMaskServiceTest {

    @Test
    void passwordMask() {
        PasswordMaskServiceMock passwordMaskServiceMock = new PasswordMaskServiceMock();
        PasswordMaskResponse responseMock = new PasswordMaskResponse();
        when(passwordMaskServiceMock.getClient().post(eq(Api.AUTHENTICATION_CUSTOMER_LOGON_PASSWORD_MASK_GET), any(PasswordMaskRequest.class), eq(PasswordMaskResponse.class)))
                .thenReturn(ResponseEntity.ok(responseMock));

        PasswordMaskResponse response = passwordMaskServiceMock.passwordMask("login");

        assertThat(response).isEqualTo(responseMock);
    }

    static class PasswordMaskServiceMock extends PasswordMaskService {

        public PasswordMaskServiceMock() {
            super(mock(PasswordMaskInterceptor.class));

            client = mock(HttpClient.class);
        }

        HttpClient getClient() {
            return client;
        }
    }
}