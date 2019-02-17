package com.bank.counter;

import com.bank.token.Token;
import com.bank.token.TokenStatus;
import com.bank.service.QueueService;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * {@link Counter} thread responsible for processing {@link Token}.
 */
public class Counter implements Runnable {
    private Logger logger = Logger.getLogger(Counter.class.getName());
    private int counterNumber;
    private QueueService queueService;
    private boolean close;
    private Token currentToken;

    public Counter(int counterNumber, QueueService queueService) {
        this.counterNumber = counterNumber;
        this.queueService = queueService;
    }


    public void run() {
        logger.log(Level.INFO, "Counter [{0}] open", counterNumber);
        while (!close) {
            Token token = queueService.getTokenForProcessing();
            this.currentToken = token;
            token.tokenStatus = TokenStatus.COUNTER_ASSIGNED;
            process(token);
            this.currentToken = null;
            token.tokenStatus = TokenStatus.FINISHED;
        }
        logger.log(Level.INFO, "Counter [{0}] closed", counterNumber);
    }

    private void process(Token token) {
        try {
            //Do some processing
            logger.log(Level.INFO,
                    "Processed token[{0}] of Customer[{1}] from counter [{2}]",
                    new Object[]{
                            token.getTokenNumber(),
                            token.getCustomer().getName(),
                            counterNumber});

            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Error occurred while processing");
            e.printStackTrace();
        }
    }

    void close() {
        this.close = true;
    }

    public boolean isClose() {
        return close;
    }

    public int getCounterNumber() {
        return counterNumber;
    }

    public Token getCurrentToken() {
        return currentToken;
    }
}
