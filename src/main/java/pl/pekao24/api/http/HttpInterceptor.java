package pl.pekao24.api.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public abstract class HttpInterceptor implements ClientHttpRequestInterceptor {

    private final Logger log = LoggerFactory.getLogger(HttpInterceptor.class);

    protected void logRequest(HttpRequest request, byte[] body) {
        log.info("===========================request begin================================================");
        log.info("URI         : {}", request.getURI());
        log.info("Method      : {}", request.getMethod());
        request.getHeaders().keySet().forEach(key -> log.info("Header key  : {} value: {}", key, request.getHeaders().get(key)));
        log.info("Request body: {}", new String(body, StandardCharsets.UTF_8));
        log.info("==========================request end================================================");
    }

    protected void logResponse(ClientHttpResponse response) throws IOException {
        log.info("============================response begin==========================================");
        log.info("Status code  : {}", response.getStatusCode());
        log.info("Status text  : {}", response.getStatusText());
        response.getHeaders().keySet().forEach(key -> {
            log.info("Header key  : {} value: {}", key, response.getHeaders().get(key));
        });
        log.info("Response body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
        log.info("=======================response end=================================================");
    }
}
