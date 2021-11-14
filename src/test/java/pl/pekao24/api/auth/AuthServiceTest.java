package pl.pekao24.api.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.pekao24.api.auth.logon.LogonService;
import pl.pekao24.api.auth.passwordmask.PasswordMaskService;
import pl.pekao24.api.auth.passwordmask.dto.PasswordMaskResponse;
import pl.pekao24.api.auth.singleaccess.SingleAccessService;
import pl.pekao24.api.auth.singleaccess.dto.SingleAccessResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AuthServiceTest {

    @Mock
    private PasswordMaskService passwordMaskService;

    @Mock
    private LogonService logonService;

    @Mock
    private SingleAccessService singleAccessService;

    @Test
    void authorize() {
        PasswordMaskResponse passwordMaskResponse = mock(PasswordMaskResponse.class);
        passwordMaskResponse.passwordMask = "101";
        SingleAccessResponse singleAccessResponse = mock(SingleAccessResponse.class);
        when(passwordMaskService.passwordMask(eq("login"))).thenReturn(passwordMaskResponse);
        when(logonService.logon(eq("login"), eq("password"), eq("101"))).thenReturn(true);
        when(singleAccessService.singleAccess()).thenReturn(singleAccessResponse);

        AuthService authService = new AuthService(passwordMaskService, logonService, singleAccessService);
        boolean status = authService.authorize("login", "password");

        assertThat(status).isTrue();
    }
}