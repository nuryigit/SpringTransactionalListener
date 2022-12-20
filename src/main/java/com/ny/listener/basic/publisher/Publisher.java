package com.ny.listener.basic.publisher;

import com.ny.listener.basic.event.RunnableEvent;
import com.ny.listener.basic.event.UserCreatedEvent;
import com.ny.listener.basic.event.UserRemovedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
    private final ApplicationEventPublisher publisher;

    Publisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishCreatedEvent(final String name) {
        // Publishing event created by extending ApplicationEvent
        publisher.publishEvent(new UserCreatedEvent(this, name));

    }

    public void publishRemoveEvent(String name) {
        // Publishing an object as an event
        publisher.publishEvent(new UserRemovedEvent(name));
    }

    public void publishRunnableEvent(Runnable runnable) {
        // Publishing event created by extending ApplicationEvent
        publisher.publishEvent(new RunnableEvent(this, runnable));

    }

}
