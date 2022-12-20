package com.ny.listener.basic.event;

import org.springframework.context.ApplicationEvent;

public class UserCreatedEvent extends ApplicationEvent {
    private String name;

    public UserCreatedEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserCreatedEvent{" +
                "name='" + name + '\'' +
                '}';
    }
}
