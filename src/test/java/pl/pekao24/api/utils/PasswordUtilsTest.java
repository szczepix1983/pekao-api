package pl.pekao24.api.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PasswordUtilsTest {

    @Test
    void unmask() {
        String result = PasswordUtils.unmask("password", "11001001");

        assertThat(result).isEqualTo("pawd");
    }

    @Test
    void unmaskWithEmptyMask() {
        String result = PasswordUtils.unmask("password", "");

        assertThat(result).isEqualTo("");
    }
}