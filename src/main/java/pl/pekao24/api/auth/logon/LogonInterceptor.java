package pl.pekao24.api.auth.logon;

import org.springframework.http.HttpHeaders;
import pl.pekao24.api.Api;
import pl.pekao24.api.http.HttpHeadersCache;
import pl.pekao24.api.http.HttpInterceptor;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

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
        request.getHeaders().set(HttpHeaders.COOKIE, StringUtils.join(httpHeadersCache.get(Api.LOGON_PASSWORD_MASK.getUrl()).get(HttpHeaders.SET_COOKIE)));
        request.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        logRequest(request, body);

        ClientHttpResponse clientHttpResponse = execution.execute(request, body);

        logResponse(clientHttpResponse);

        httpHeadersCache.put(request.getURI().toURL().toString(), clientHttpResponse.getHeaders());
        return clientHttpResponse;
    }
}
