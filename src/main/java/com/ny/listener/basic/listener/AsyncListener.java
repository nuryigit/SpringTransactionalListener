package com.ny.listener.basic.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncListener {

    @Async
    @EventListener
    void handleAsyncEvent(String event) {
        // handle event
    }
}