package pl.pekao24.api.auth.logon;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import pl.pekao24.api.Api;
import pl.pekao24.api.http.HttpHeadersCache;
import pl.pekao24.api.http.HttpInterceptor;

import java.io.IOException;

@Component
public class LogonInterceptor extends HttpInterceptor {

    private final HttpHeadersCache httpHeadersCache;

    public LogonInterceptor(final HttpHeadersCache httpHeadersCache) {
        this.httpHeadersCache = httpHeadersCache;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set(HttpHeaders.USER_AGENT, HttpHeadersCache.USER_AGENT);
        request.getHeaders().set(HttpHeaders.HOST, HttpHeadersCache.HOST);
        request.getHeaders().set(HttpHeaders.ORIGIN, HttpHeadersCache.ORIGIN);
        request.getHeaders().set(HttpHeaders.REFERER, HttpHeadersCache.REFERER);
        request.getHeaders().set(HttpHeaders.COOKIE, StringUtils.join(httpHeadersCache.get(Api.AUTHENTICATION_CUSTOMER_LOGON_PASSWORD_MASK_GET.getUrl()).get(HttpHeaders.SET_COOKIE)));

        logRequest(request, body);

        ClientHttpResponse clientHttpResponse = execution.execute(request, body);

        logResponse(clientHttpResponse);

        httpHeadersCache.put(request.getURI().toURL().toString(), clientHttpResponse.getHeaders());
        return clientHttpResponse;
    }
}
