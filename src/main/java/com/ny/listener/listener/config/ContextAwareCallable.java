package com.ny.listener.listener.config;

import org.slf4j.MDC;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;

public class ContextAwareCallable<T> implements Callable<T> {
    private Callable<T> task;
    private CustomRequestScopeAttributes customRequestScopeAttributes;

    public ContextAwareCallable(Callable<T> task, RequestAttributes context) {
        this.task = task;
        if (context != null) {
            //This is Custom class implements RequestAttributes class
            this.customRequestScopeAttributes = new CustomRequestScopeAttributes();

            //Add the request scoped bean to Custom class
            HttpServletRequest request = ((ServletRequestAttributes) context).getRequest();
            Collections.list(request.getHeaderNames()).forEach(header -> customRequestScopeAttributes.setAttribute(header, request.getHeader(header), 0));

            //Set that in RequestContextHolder and set as Inheritable as true
            //Inheritable is used for setting the attributes in diffrent ThreadLocal objects.
            RequestContextHolder.setRequestAttributes
                    (customRequestScopeAttributes,true);

        }
    }

    @Override
    public T call() throws Exception {
        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        try {
            if (copyOfContextMap != null) {
                MDC.setContextMap(copyOfContextMap);
            }
            return task.call();
        } finally {
            customRequestScopeAttributes.removeAllAttribute(0);
        }
    }
}
