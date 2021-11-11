package pl.pekao24.api.auth;

import pl.pekao24.api.auth.logon.LogonService;
import pl.pekao24.api.auth.passwordmask.PasswordMaskService;
import pl.pekao24.api.auth.passwordmask.dto.PasswordMaskResponse;
import pl.pekao24.api.auth.singleaccess.dto.SingleAccessResponse;
import pl.pekao24.api.auth.singleaccess.SingleAccessService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    private final PasswordMaskService passwordMaskService;
    private final LogonService logonService;
    private final SingleAccessService singleAccessService;

    public AuthService(final PasswordMaskService passwordMaskService,
                       final LogonService logonService,
                       final SingleAccessService singleAccessService) {
        this.passwordMaskService = passwordMaskService;
        this.logonService = logonService;
        this.singleAccessService = singleAccessService;
    }

    public boolean authorize(final String login, final String password) {
        PasswordMaskResponse passwordMaskResponse = passwordMaskService.passwordMask(login);
        boolean logon = logonService.logon(login, password, passwordMaskResponse.passwordMask);
        SingleAccessResponse singleAccessResponse = singleAccessService.singleAccess();
        return Objects.nonNull(passwordMaskResponse) &&
                logon &&
                Objects.nonNull(singleAccessResponse);
    }
}
