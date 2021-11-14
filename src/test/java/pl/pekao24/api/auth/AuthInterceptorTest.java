package pl.pekao24.api.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pekao24.api.Api;
import pl.pekao24.api.http.HttpHeadersCache;

import java.net.URI;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AuthInterceptorTest {

    @Test
    void intercept() throws Exception {
        HttpHeadersCache httpHeadersCache = mock(HttpHeadersCache.class);
        HttpRequest request = mock(HttpRequest.class);
        HttpHeaders requestHeaders = mock(HttpHeaders.class);
        ClientHttpRequestExecution clientHttpRequestExecution = mock(ClientHttpRequestExecution.class);
        ClientHttpResponse clientHttpResponse = mock(ClientHttpResponse.class);
        when(request.getHeaders()).thenReturn(requestHeaders);
        when(clientHttpRequestExecution.execute(eq(request), any())).thenReturn(clientHttpResponse);
        when(clientHttpResponse.getStatusCode()).thenReturn(HttpStatus.OK);
        when(clientHttpResponse.getHeaders()).thenReturn(requestHeaders);
        when(request.getURI()).thenReturn(URI.create(Api.ACCOUNT_CARD_DEBIT_SUMMARY.getUrl()));
        when(httpHeadersCache.extractCookies(eq(Api.AUTHENTICATION_CUSTOMER_DFP_SINGLE_ACCESS))).thenReturn("session");
        when(httpHeadersCache.extractXCsrfToken(eq(Api.AUTHENTICATION_CUSTOMER_DFP_SINGLE_ACCESS))).thenReturn("token");

        AuthInterceptor interceptor = new AuthInterceptor(httpHeadersCache);
        interceptor.intercept(request, new byte[]{}, clientHttpRequestExecution);

        verify(requestHeaders, times(1)).set(eq(HttpHeaders.COOKIE), eq("session"));
        verify(requestHeaders, times(1)).set(eq("x-csrf-token"), eq("token"));
        verify(requestHeaders, times(1)).set(eq(HttpHeaders.ACCEPT), eq("application/json, text/plain, */*"));
        verify(requestHeaders, times(1)).set(eq("Channel-Indicator"), eq("DESKTOP"));
        verify(httpHeadersCache, times(1)).put(eq(request.getURI().toURL().toString()), eq(requestHeaders));
    }
}