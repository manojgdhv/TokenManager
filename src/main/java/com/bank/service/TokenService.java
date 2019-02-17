package com.bank.service;

import com.bank.counter.Counter;
import com.bank.token.Token;
import com.bank.token.TokenStatus;
import com.bank.user.Customer;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * {@link TokenService} is a singleton class responsible for issuing tokens to customer. It creates multiple counters
 * which takes the task
 * from {@link QueueService} to process.
 */
public class TokenService {
    public static final String TOKEN_PREFIX = "_TK";
    private static TokenService tokenService;
    private QueueService queueService;

    private List<Counter> counters = new ArrayList();

    private TokenService(int noOfCounters) {
        super();
        queueService = new QueueService();
        initCounters(noOfCounters);
    }

    private void initCounters(int noOfCounters) {
        ExecutorService executorService = Executors.newFixedThreadPool(noOfCounters);
        for (int i = 0; i < noOfCounters; i++) {
            Counter counter = new Counter(i, queueService);
            executorService.submit(counter);
            counters.add(counter);
        }
    }

    public void closeCounters() {
        counters.stream().forEach((c) -> c.isClose());
    }

    /**
     * Creates {@link TokenService} singleton instance.
     * @param noOfCounters Number of counters to be created.
     * @return Singlton instance of {@link TokenService}
     */
    public static synchronized TokenService createTokenManager(int noOfCounters) {
        if (tokenService == null) {
            tokenService = new TokenService(noOfCounters);
        }
        return tokenService;
    }

    /**
     * Place the {@link Token} in queue.
     * @param customer {@link Customer} details
     * @return {@link Token} issued token
     */
    public Token issueToken(Customer customer) {
        if (isCounterOpen()) {
            Token token = new Token(customer.getName().concat(TOKEN_PREFIX), customer, TokenStatus.IN_A_QUEUE);
            queueService.addTokenForProcessing(token);
            return token;
        }

        return null;
    }

    private boolean isCounterOpen() {
        return counters.stream().anyMatch(counter -> !counter.isClose());
    }

    public void displayTokens() {
        counters.stream().forEach(counter -> {
                    if (counter.getCurrentToken() != null) {
                        System.out.println(String.format("Counter [%d] processing Token [%s]",
                                counter.getCounterNumber(),
                                counter.getCurrentToken().getTokenNumber()));
                    }
                }
        );

    }
}
