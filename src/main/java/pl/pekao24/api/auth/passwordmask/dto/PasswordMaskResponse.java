package pl.pekao24.api.auth.passwordmask.dto;

import java.io.Serializable;

public class PasswordMaskResponse implements Serializable {
    public String passwordMask;
    public boolean firstLogin;
}
