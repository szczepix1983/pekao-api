package pl.pekao24.api.domains.customer.dto;

import java.io.Serializable;
import java.util.List;

public class CustomerDetailsResponse implements Serializable {
    public String cis;
    public String contractStatus;
    public String customerSegment;
    public String firstName;
    public String lastName;
    public String fullName;
    public boolean minor;
    public boolean resident;
    public String pesel;
    public String raban;
    public String rodoFlag;
    public CustomerDetailsCorrespondenceAddress correspondenceAddress;
    public CustomerDetailsLegalAddress legalAddress;
    public List<CustomerDetailsDocuments> documents;
}
