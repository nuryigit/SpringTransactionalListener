package com.ny.listener.basic.listener;

import com.ny.listener.basic.event.UserRemovedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserRemovedListener {

    @TransactionalEventListener(phase= TransactionPhase.AFTER_COMPLETION)
    void handleAfterUserRemoved(UserRemovedEvent event) {
        // handle UserRemovedEvent
    }
}
