package pl.pekao24.api.domains.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pekao24.api.Api;
import pl.pekao24.api.auth.AuthInterceptor;
import pl.pekao24.api.domains.customer.dto.CustomerDetailsResponse;
import pl.pekao24.api.domains.customer.dto.CustomerProfileBasicResponse;
import pl.pekao24.api.http.HttpClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CustomerServiceTest {

    @Test
    void profileBasic() {
        CustomerServiceMock customerServiceMock = new CustomerServiceMock();
        CustomerProfileBasicResponse responseMock = new CustomerProfileBasicResponse();
        when(customerServiceMock.getClient().get(eq(Api.CUSTOMER_PROFILE_BASIC), eq(CustomerProfileBasicResponse.class)))
                .thenReturn(ResponseEntity.ok(responseMock));

        CustomerProfileBasicResponse response = customerServiceMock.profileBasic();

        assertThat(response).isEqualTo(responseMock);
    }

    @Test
    void details() {
        CustomerServiceMock customerServiceMock = new CustomerServiceMock();
        CustomerDetailsResponse responseMock = new CustomerDetailsResponse();
        when(customerServiceMock.getClient().get(eq(Api.CUSTOMER_DETAILS), eq(CustomerDetailsResponse.class)))
                .thenReturn(ResponseEntity.ok(responseMock));

        CustomerDetailsResponse response = customerServiceMock.details();

        assertThat(response).isEqualTo(responseMock);
    }

    static class CustomerServiceMock extends CustomerService {

        public CustomerServiceMock() {
            super(mock(AuthInterceptor.class));

            client = mock(HttpClient.class);
        }

        HttpClient getClient() {
            return client;
        }
    }
}