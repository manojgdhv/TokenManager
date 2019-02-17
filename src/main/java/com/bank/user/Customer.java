package com.bank.user;

/**
 * {@link Customer} represents customer from Bank.
 */
public class Customer {
    private int accountNumber;
    private String name;
    private Boolean privileged;

    public Customer(int accountNumber, String name, boolean privileged) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.privileged = privileged;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public Boolean isPrivileged() {
        return privileged;
    }
}
