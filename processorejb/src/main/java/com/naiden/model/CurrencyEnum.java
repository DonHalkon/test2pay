package com.naiden.model;

public enum CurrencyEnum {
    USD("USD"),
    EUR("EUR");
    private final String currency;

    CurrencyEnum(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}
