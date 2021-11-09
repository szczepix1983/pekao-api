package pl.pekao24.api.http;

import org.springframework.http.HttpHeaders;
import pl.pekao24.api.Api;
import pl.pekao24.api.utils.CookieUtils;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class HttpHeadersCache extends HashMap<String, HttpHeaders> {

    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36";
    public static final String HOST = "www.pekao24.pl";
    public static final String ORIGIN = "https://www.pekao24.pl";
    public static final String REFERER = "https://www.pekao24.pl/logowanie";

    private static final List<String> REQUIRED_COOKIES = List.of("dtCookie", "FDS", "ID_TOKEN", "REF_TOKEN", "SESSION_ID");

    public String extractCookies(final Api api) {
        HttpHeaders lastResponseHeaders = get(api.getUrl());
        List<String> setCookie = lastResponseHeaders.get(HttpHeaders.SET_COOKIE);
        return CookieUtils.getRequiredCookies(setCookie, REQUIRED_COOKIES);
    }

    public String extractXCsrfToken(final Api api) {
        HttpHeaders lastResponseHeaders = get(api.getUrl());
        return StringUtils.join(lastResponseHeaders.get("X-CSRF-TOKEN"));
    }
}
