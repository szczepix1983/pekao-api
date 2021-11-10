package pl.pekao24.api.auth.passwordmask;

import pl.pekao24.api.Api;
import pl.pekao24.api.auth.passwordmask.dto.PasswordMaskRequest;
import pl.pekao24.api.auth.passwordmask.dto.PasswordMaskResponse;
import pl.pekao24.api.http.HttpClient;
import org.springframework.stereotype.Service;

@Service
public class PasswordMaskService {

    private final HttpClient client;

    public PasswordMaskService(final PasswordMaskInterceptor interceptor) {
        this.client = new HttpClient(interceptor);
    }

    public PasswordMaskResponse getPasswordMask(final String login) {
        PasswordMaskRequest passwordMaskRequest = new PasswordMaskRequest();
        passwordMaskRequest.customer = login;
        return client.post(
                Api.AUTHENTICATION_CUSTOMER_LOGON_PASSWORD_MASK_GET,
                passwordMaskRequest,
                PasswordMaskResponse.class).getBody();
    }
}
