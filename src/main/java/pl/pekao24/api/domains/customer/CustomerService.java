package pl.pekao24.api.domains.customer;

import pl.pekao24.api.auth.AuthInterceptor;
import pl.pekao24.api.domains.customer.dto.CustomerDetailsResponse;
import pl.pekao24.api.domains.customer.dto.CustomerProfileBasicResponse;
import pl.pekao24.api.Api;
import pl.pekao24.api.http.HttpClient;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    protected HttpClient client;

    public CustomerService(final AuthInterceptor interceptor) {
        this.client = new HttpClient(interceptor);
    }

    public CustomerProfileBasicResponse profileBasic() {
        return client.get(
                Api.CUSTOMER_PROFILE_BASIC,
                CustomerProfileBasicResponse.class).getBody();
    }

    public CustomerDetailsResponse details() {
        return client.get(
                Api.CUSTOMER_DETAILS,
                CustomerDetailsResponse.class).getBody();
    }

}
