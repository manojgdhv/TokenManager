package com.bank.service;

import com.bank.token.Token;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * {@link QueueService responsible for maintaing priority queue of {@link Token}}.
 */
public class QueueService {
    private static final int CAPACITY = 100;
    private Logger logger = Logger.getLogger(QueueService.class.getName());

    /**
     * Privileged customer should get higher priority.
     */
    private PriorityBlockingQueue<Token> counterQueue = new PriorityBlockingQueue<>(CAPACITY,
            (o1, o2) -> o2.getCustomer().isPrivileged().compareTo(o1.getCustomer().isPrivileged()));

    void addTokenForProcessing(Token token) {
        counterQueue.add(token);
    }

    public synchronized Token getTokenForProcessing() {
        try {
            return counterQueue.take();
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Error occurred while fetching token");
            e.printStackTrace();
        }
        return null;
    }
}
