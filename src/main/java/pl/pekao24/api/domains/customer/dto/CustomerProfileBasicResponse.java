package pl.pekao24.api.domains.customer.dto;

import java.io.Serializable;

public class CustomerProfileBasicResponse implements Serializable {
    public String cis;
    public String firstName;
    public String lastName;
    public String fullName;
    public String customerSegment;
    public String customerType;
    public String accessType;
    public String makAccessType;
    public String contractStatus;
    public String makContractStatus;
    public String danosFlag;
    public boolean minor;
    public boolean resident;
    public String smsPhoneNumber;
    public String callbackPhoneNumber;
    public String economicActivityType;
}
