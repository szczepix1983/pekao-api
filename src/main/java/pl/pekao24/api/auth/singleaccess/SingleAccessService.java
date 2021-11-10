package pl.pekao24.api.auth.singleaccess;

import pl.pekao24.api.Api;
import pl.pekao24.api.auth.singleaccess.dto.SingleAccessResponse;
import pl.pekao24.api.http.HttpClient;
import org.springframework.stereotype.Service;

@Service
public class SingleAccessService {

    private final HttpClient client;

    public SingleAccessService(final SingleAccessInterceptor interceptor) {
        this.client = new HttpClient(interceptor);
    }

    public SingleAccessResponse getSingleAccess() {
        return client.post(
                Api.AUTHENTICATION_CUSTOMER_DFP_SINGLE_ACCESS,
                SingleAccessResponse.class).getBody();
    }
}
