package pl.pekao24.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CredentialsTest.CredentialsTestConfiguration.class)
@TestPropertySource(locations = {"classpath:credentials.properties"})
@EnableConfigurationProperties(value = Credentials.class)
class CredentialsTest {

    @Autowired
    Credentials config;

    @Test
    void getLogin() {
        assertThat(this.config.getLogin()).isEqualTo("123456");
    }

    @Test
    void getPassword() {
        assertThat(this.config.getPassword()).isEqualTo("password");
    }

    @Configuration
    static class CredentialsTestConfiguration {

        @Bean
        public Credentials config() {
            return new Credentials();
        }
    }
}