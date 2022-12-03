package com.ny.listener.listener.decorator;

import org.slf4j.MDC;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.Executor;

public class ContextAwareExecutorDecorator implements Executor, TaskExecutor {

    private final Executor executor;

    public ContextAwareExecutorDecorator( Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute( Runnable command) {
        Runnable ctxAwareCommand = decorateContextAware(command);
        executor.execute(ctxAwareCommand);
    }

    private Runnable decorateContextAware( Runnable command) {
        RequestAttributes originalRequestContext = RequestContextHolder.currentRequestAttributes();

        if (originalRequestContext != null) {
            HttpServletRequest request = ((ServletRequestAttributes) originalRequestContext).getRequest();
            copyRequestToMDC(request);
        }

        final Map<String, String> originalContextCopy = MDC.getCopyOfContextMap();
        return () -> {
            try {
                if (originalRequestContext != null) {
                    RequestContextHolder.setRequestAttributes(originalRequestContext);
                }
                MDC.setContextMap(originalContextCopy);
                command.run();
            } finally {
                MDC.clear();
                RequestContextHolder.resetRequestAttributes();
            }
        };
    }

    private void copyRequestToMDC( HttpServletRequest request) {
        if (request != null) {
            Collections.list(request.getHeaderNames()).forEach(header -> MDC.put(header, request.getHeader(header)));
        }
    }
}