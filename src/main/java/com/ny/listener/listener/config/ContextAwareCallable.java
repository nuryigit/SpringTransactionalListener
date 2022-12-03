package com.ny.listener.listener.config;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.Callable;

public class ContextAwareCallable <T> implements Callable<T> {
    private Callable<T> task;
    private final RequestAttributes requestAttributes;

    public ContextAwareCallable(Callable<T> task, RequestAttributes requestAttributes) {
        this.task = task;
        this.requestAttributes = cloneRequestAttributes(requestAttributes);
    }

    @Override
    public T call() throws Exception {
        try {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            return task.call();
        } finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }

    private RequestAttributes cloneRequestAttributes(RequestAttributes requestAttributes){
        RequestAttributes clonedRequestAttribute = null;
        try{
            clonedRequestAttribute = new ServletRequestAttributes(((ServletRequestAttributes) requestAttributes).getRequest(), ((ServletRequestAttributes) requestAttributes).getResponse());
            if(requestAttributes.getAttributeNames(RequestAttributes.SCOPE_REQUEST).length>0){
                for(String name: requestAttributes.getAttributeNames(RequestAttributes.SCOPE_REQUEST)){
                    clonedRequestAttribute.setAttribute(name,requestAttributes.getAttribute(name,RequestAttributes.SCOPE_REQUEST),RequestAttributes.SCOPE_REQUEST);
                }
            }
            if(requestAttributes.getAttributeNames(RequestAttributes.SCOPE_SESSION).length>0){
                for(String name: requestAttributes.getAttributeNames(RequestAttributes.SCOPE_SESSION)){
                    clonedRequestAttribute.setAttribute(name,requestAttributes.getAttribute(name,RequestAttributes.SCOPE_SESSION),RequestAttributes.SCOPE_SESSION);
                }
            }

            return clonedRequestAttribute;
        }catch(Exception e){
            return requestAttributes;
        }
    }
}
