package pl.pekao24.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class ApiTest {

    @Test
    void getUrl() {
        for (Api api : Api.values()) {
            assertThat(api.getUrl().endsWith(api.toString())).isTrue();
        }
    }
}