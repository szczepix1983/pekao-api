package pl.pekao24.api.utils;

import org.apache.tomcat.util.buf.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CookieUtils {

    public static String getRequiredCookies(final List<String> cookies, final List<String> requiredCookies) {
        List<String> result = new ArrayList<>();
        requiredCookies.forEach(cookie -> cookies.forEach(value -> {
            if(value.startsWith(cookie)) {
                List<String> cookieSplit = List.of(value.split(";"));
                result.add(cookieSplit.get(0));
            }
        }));
        return StringUtils.join(result, ";".charAt(0));
    }
}
