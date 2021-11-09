package pl.pekao24.api;

public enum Api {

    LOGON("authentication/customer/logon"),
    LOGON_PASSWORD_MASK("authentication/customer/logon/password-mask/get"),
    SINGLE_ACCESS("authentication/customer/dfp/single-access"),
    CUSTOMER_PROFILE_BASIC("customer/profile/basic"),
    CUSTOMER_DETAILS("customer/details"),
    ACCOUNT_SUMMARY("account/summary"),
    ACCOUNT_CARD_DEBIT_SUMMARY("account/card/debit/summary"),
    ACCOUNT_TRANSACTIONS("account/transactions?accountIds={accountIds}&dateFrom={dateFrom}&dateTo={dateTo}&synchronize={synchronize}"),
    LOAN_OFFER("loan/offer"),
    LOAN_SUMMARY("loan/summary"),
    LOAN_DETAILS("loan/details/{id}"),
    MONEY_TRANSFER_PREDEFINED_LIST("money-transfer/predefined/list");

    private final String endpoint;
    private static final String HOST = "https://www.pekao24.pl/api/";

    Api(final String endpoint) {
        this.endpoint = endpoint;
    }

    public String getUrl() {
        return HOST + endpoint;
    }

    @Override
    public String toString(){
        return endpoint;
    }
}
