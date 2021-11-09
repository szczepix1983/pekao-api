package pl.pekao24.api.utils;

import java.util.Arrays;
import java.util.List;

public class PasswordUtils {

    public static String unmask(final String password, final String mask) {
        List<String> passwordElements = Arrays.asList(password.split(""));
        List<String> maskElements = Arrays.asList(mask.split(""));

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < maskElements.size(); i++) {
            if (maskElements.get(i).equals("1")) {
                builder.append(passwordElements.get(i));
            }
        }
        return builder.toString();
    }
}
