package pl.pekao24.api.domains.loan.dto;

import java.io.Serializable;

public class LoanOfferResponse implements Serializable {
    public boolean hasPexOffer;
    public boolean hasCreditCardOffer;
    public boolean hasPwkOffer;
    public float maxPexLimit;
    public float maxCreditCardLimit;
    public float pexRrso;
    public float creditCardRrso;
    public String offerType;
    public LoanOfferDeposit deposit;
    public LoanOfferDetails pex;
    public LoanOfferDetails creditCard;
}
