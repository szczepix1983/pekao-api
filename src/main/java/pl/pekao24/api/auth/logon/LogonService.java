package pl.pekao24.api.auth.logon;

import pl.pekao24.api.Api;
import pl.pekao24.api.utils.PasswordUtils;
import pl.pekao24.api.auth.logon.dto.LogonRequest;
import pl.pekao24.api.http.HttpClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LogonService {

    private final HttpClient client;

    public LogonService(final LogonInterceptor interceptor) {
        this.client = new HttpClient(interceptor);
    }

    public boolean getLogon(final String login, final String password, final String mask) {
        final String passwordForMask = PasswordUtils.unmask(password, mask);

        LogonRequest logonRequest = new LogonRequest();
        logonRequest.customer = login;
        logonRequest.password = passwordForMask;

        ResponseEntity<String> responseEntity = client.post(
                Api.AUTHENTICATION_CUSTOMER_LOGON,
                logonRequest,
                String.class);
        return responseEntity.getStatusCode().equals(HttpStatus.OK);
    }
}
