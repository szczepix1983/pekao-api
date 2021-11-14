package pl.pekao24.api.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import pl.pekao24.api.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Map;

public class HttpClient {

    private final Logger log = LoggerFactory.getLogger(HttpClient.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    protected RestTemplate restTemplate = new RestTemplate();

    public HttpClient(final ClientHttpRequestInterceptor interceptor) {
        this.restTemplate.getInterceptors().add(interceptor);
        this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        this.restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    }

    public <T> ResponseEntity<T> get(final Api api, final Class<T> clazz) {
        return restTemplate.getForEntity(api.getUrl(), clazz);
    }

    public <T> ResponseEntity<T> get(final Api api, final Map<String, String> params, final Class<T> clazz) {
        return restTemplate.getForEntity(api.getUrl(), clazz, params);
    }

    public <T> ResponseEntity<T> post(final Api api, final Class<T> clazz) {
        return restTemplate.postForEntity(api.getUrl(), new HttpEntity<>(new HttpHeaders()), clazz);
    }

    public <T> ResponseEntity<T> post(final Api api, final Serializable requestBody, final Class<T> clazz) {
        try {
            return restTemplate.postForEntity(api.getUrl(), createRequest(requestBody), clazz);
        } catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
            if (HttpStatus.PRECONDITION_FAILED.equals(httpClientOrServerExc.getStatusCode())) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    protected HttpEntity<String> createRequest(final Serializable body) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new HttpEntity<>(objectMapper.writeValueAsString(body), headers);
        } catch (JsonProcessingException e) {
            log.error("Error while processing request for: {} exception: {}", body, e);
            return null;
        }
    }
}
