package com.bank.token;

import com.bank.user.Customer;

/**
 * {@link Token} represents Token provided to customer.
 */
public class Token {
    private String tokenNumber;
    private Customer customer;
    public TokenStatus tokenStatus;

    public Token(String tokenNumber, Customer customer, TokenStatus tokenStatus) {
        this.tokenNumber = tokenNumber;
        this.customer = customer;
        this.tokenStatus = tokenStatus;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public Customer getCustomer() {
        return customer;
    }
}
