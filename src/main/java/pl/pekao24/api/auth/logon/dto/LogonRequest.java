package pl.pekao24.api.auth.logon.dto;

import java.io.Serializable;

public class LogonRequest implements Serializable {
    public String customer;
    public String password;
}
