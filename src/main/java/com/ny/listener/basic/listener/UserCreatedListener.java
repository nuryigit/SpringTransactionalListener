package com.ny.listener.basic.listener;

import com.ny.listener.basic.event.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserCreatedListener {

    Logger logger = LoggerFactory.getLogger(UserCreatedListener.class);

    @Async
    @TransactionalEventListener(phase= TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(UserCreatedEvent event) throws InterruptedException {
        // handle UserCreatedEvent
        Thread.sleep(2000);
        logger.info("UserCreatedListener, user created {}", event);

    }
}
