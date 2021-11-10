package pl.pekao24.api.auth.singleaccess;

import org.springframework.http.HttpHeaders;
import pl.pekao24.api.Api;
import pl.pekao24.api.http.HttpHeadersCache;
import pl.pekao24.api.http.HttpInterceptor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SingleAccessInterceptor extends HttpInterceptor {

    private final HttpHeadersCache httpHeadersCache;

    public SingleAccessInterceptor(final HttpHeadersCache httpHeadersCache) {
        this.httpHeadersCache = httpHeadersCache;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set(HttpHeaders.COOKIE, httpHeadersCache.extractCookies(Api.AUTHENTICATION_CUSTOMER_LOGON));
        request.getHeaders().set("x-csrf-token", httpHeadersCache.extractXCsrfToken(Api.AUTHENTICATION_CUSTOMER_LOGON));

        logRequest(request, body);

        ClientHttpResponse clientHttpResponse = execution.execute(request, body);

        logResponse(clientHttpResponse);

        httpHeadersCache.put(request.getURI().toURL().toString(), clientHttpResponse.getHeaders());
        return clientHttpResponse;
    }
}
