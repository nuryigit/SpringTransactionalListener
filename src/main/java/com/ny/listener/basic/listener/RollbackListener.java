package com.ny.listener.basic.listener;

import com.ny.listener.basic.event.RunnableEvent;
import com.ny.listener.basic.event.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class RollbackListener {

    Logger logger = LoggerFactory.getLogger(UserCreatedListener.class);

    @TransactionalEventListener(phase= TransactionPhase.AFTER_ROLLBACK)
    public void onApplicationEvent(RunnableEvent event) throws InterruptedException {
        // handle UserCreatedEvent
        Thread.sleep(2000);
        logger.info("RollbackListener, {}", event);
        event.getRunnable().run();
    }
}
