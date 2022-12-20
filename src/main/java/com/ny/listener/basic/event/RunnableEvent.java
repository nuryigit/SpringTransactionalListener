package com.ny.listener.basic.event;

import org.springframework.context.ApplicationEvent;

public class RunnableEvent extends ApplicationEvent {
    private Runnable runnable;

    public RunnableEvent(Object source, Runnable runnable) {
        super(source);
        this.runnable = runnable;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public String toString() {
        return "RunnableEvent{" +
                "runnable=" + runnable +
                '}';
    }
}
