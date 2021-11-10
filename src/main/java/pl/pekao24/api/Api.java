package pl.pekao24.api;

public enum Api {
    AUTHENTICATION_CUSTOMER_LOGON("authentication/customer/logon"),
    AUTHENTICATION_CUSTOMER_LOGON_PASSWORD_MASK_GET("authentication/customer/logon/password-mask/get"),
    AUTHENTICATION_CUSTOMER_DFP_SINGLE_ACCESS("authentication/customer/dfp/single-access"),
    CUSTOMER_PROFILE_BASIC("customer/profile/basic"),
    CUSTOMER_DETAILS("customer/details"),
    ACCOUNT_SUMMARY("account/summary"),
    ACCOUNT_CARD_DEBIT_SUMMARY("account/card/debit/summary"),
    ACCOUNT_TRANSACTIONS("account/transactions?accountIds={accountIds}&dateFrom={dateFrom}&dateTo={dateTo}&synchronize={synchronize}"),
    ACCOUNT_LIMITS("account/limits/{accountIds}"),
    LOAN_OFFER("loan/offer"),
    LOAN_SUMMARY("loan/summary"),
    LOAN_DETAILS("loan/details/{id}"),
    MONEY_TRANSFER_PREDEFINED_LIST("money-transfer/predefined/list"),
    MONEY_TRANSFER_HISTORY("money-transfer/history"),
    DICTIONARY_TRANSACTION_CATEGORIES("dictionary/transaction-categories"),
    DICTIONARY_BANK_DATA_GET("dictionary/bank/data/get");

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
