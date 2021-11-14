package pl.pekao24.api.auth.passwordmask;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import pl.pekao24.api.http.HttpHeadersCache;
import pl.pekao24.api.http.HttpInterceptor;

import java.io.IOException;

@Component
public class PasswordMaskInterceptor extends HttpInterceptor {

    private final HttpHeadersCache httpHeadersCache;

    public PasswordMaskInterceptor(final HttpHeadersCache httpHeadersCache) {
        this.httpHeadersCache = httpHeadersCache;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set(HttpHeaders.USER_AGENT, HttpHeadersCache.USER_AGENT);
        request.getHeaders().set(HttpHeaders.HOST, HttpHeadersCache.HOST);
        request.getHeaders().set(HttpHeaders.ORIGIN, HttpHeadersCache.ORIGIN);
        request.getHeaders().set(HttpHeaders.REFERER, HttpHeadersCache.REFERER);

        logRequest(request, body);

        ClientHttpResponse clientHttpResponse = execution.execute(request, body);

        logResponse(clientHttpResponse);

        httpHeadersCache.put(request.getURI().toURL().toString(), clientHttpResponse.getHeaders());
        return clientHttpResponse;
    }

}
