package com.bank.client;

import com.bank.service.TokenService;
import com.bank.user.Customer;

/**
 * {@link Client} represents client program which will issue token on behalf of customer.
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        TokenService tokenService = TokenService.createTokenManager(4);
        tokenService.issueToken(new Customer(1, "A",false));
        tokenService.issueToken(new Customer(2, "B",true));
        tokenService.issueToken(new Customer(3, "C",true));
        tokenService.issueToken(new Customer(4, "D",false));
        tokenService.issueToken(new Customer(5, "E",true));
        tokenService.issueToken(new Customer(6, "F",true));

        int i = 0;
        while(i < 10) {
            System.out.println("====Token List====");
            tokenService.displayTokens();
            Thread.sleep(2000l);
            i++;
        }

        tokenService.closeCounters();
    }
}
