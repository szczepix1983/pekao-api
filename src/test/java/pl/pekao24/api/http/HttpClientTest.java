package pl.pekao24.api.http;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.pekao24.api.Api;
import pl.pekao24.api.auth.AuthInterceptor;

import java.io.Serializable;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HttpClientTest {

    @Test
    void get() {
        HttpClientMock httpClientMock = new HttpClientMock();
        ResponseEntity<String> responseMock = ResponseEntity.ok("response");
        when(httpClientMock.getRestTemplate().getForEntity(eq(Api.ACCOUNT_LIMITS.getUrl()), eq(String.class)))
                .thenReturn(responseMock);

        ResponseEntity<String> response = httpClientMock.get(Api.ACCOUNT_LIMITS, String.class);

        assertThat(response).isEqualTo(responseMock);
    }

    @Test
    void getWithParams() {
        HttpClientMock httpClientMock = new HttpClientMock();
        ResponseEntity<String> responseMock = ResponseEntity.ok("response");
        when(httpClientMock.getRestTemplate().getForEntity(eq(Api.ACCOUNT_LIMITS.getUrl()), eq(String.class), any(Map.class)))
                .thenReturn(responseMock);

        ResponseEntity<String> response = httpClientMock.get(Api.ACCOUNT_LIMITS, Map.of(), String.class);

        assertThat(response).isEqualTo(responseMock);
    }

    @Test
    void post() {
        HttpClientMock httpClientMock = new HttpClientMock();
        ResponseEntity<String> responseMock = ResponseEntity.ok("response");
        when(httpClientMock.getRestTemplate().postForEntity(eq(Api.ACCOUNT_LIMITS.getUrl()), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseMock);

        ResponseEntity<String> response = httpClientMock.post(Api.ACCOUNT_LIMITS, String.class);

        assertThat(response).isEqualTo(responseMock);
    }

    @Test
    void postWithRequest() {
        HttpClientMock httpClientMock = new HttpClientMock();
        ResponseEntity<String> responseMock = ResponseEntity.ok("response");
        when(httpClientMock.getRestTemplate().postForEntity(eq(Api.ACCOUNT_LIMITS.getUrl()), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseMock);

        ResponseEntity<String> response = httpClientMock.post(Api.ACCOUNT_LIMITS, null, String.class);

        assertThat(response).isEqualTo(responseMock);
    }

    @Test
    void postWithRequesException() {
        HttpClientMock httpClientMock = new HttpClientMock();
        HttpClientErrorException responseException = new HttpClientErrorException(HttpStatus.PRECONDITION_FAILED);
        when(httpClientMock.getRestTemplate().postForEntity(eq(Api.ACCOUNT_LIMITS.getUrl()), any(HttpEntity.class), eq(String.class)))
                .thenThrow(responseException);

        ResponseEntity<String> response = httpClientMock.post(Api.ACCOUNT_LIMITS, null, String.class);

        assertThat(response).isEqualTo(ResponseEntity.ok().build());
    }

    @Test
    void createRequest() {
        HttpClientMock httpClientMock = new HttpClientMock();
        RequestMock requestMock = new RequestMock();
        requestMock.name = "mock";

        HttpEntity<String> entity = httpClientMock.createRequest(requestMock);

        assertThat(entity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(entity.getBody()).isEqualTo("{\"name\":\"mock\"}");
    }

    @Test
    void createRequestWithException() {
        HttpClientMock httpClientMock = new HttpClientMock();

        HttpEntity<String> entity = httpClientMock.createRequest(new RequestMockForException());

        assertThat(entity).isNull();
    }

    static class RequestMockForException implements Serializable {}

    static class HttpClientMock extends HttpClient {

        public HttpClientMock() {
            super(mock(AuthInterceptor.class));

            restTemplate = mock(RestTemplate.class);
        }

        RestTemplate getRestTemplate() {
            return restTemplate;
        }
    }
}