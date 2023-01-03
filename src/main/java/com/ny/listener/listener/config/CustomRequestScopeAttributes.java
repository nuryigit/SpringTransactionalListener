package com.ny.listener.listener.config;


import org.springframework.web.context.request.RequestAttributes;

import java.util.HashMap;
import java.util.Map;

public class CustomRequestScopeAttributes implements RequestAttributes {

    private Map<String, Object> requestAttributeMap = new HashMap<>();
    @Override
    public Object getAttribute(String name, int scope) {
        if(scope== RequestAttributes.SCOPE_REQUEST) {
            return this.requestAttributeMap.get(name);
        }
        return null;
    }
    @Override
    public void setAttribute(String name, Object value, int scope) {
        if(scope== RequestAttributes.SCOPE_REQUEST){
            this.requestAttributeMap.put(name, value);
        }
    }
    @Override
    public void removeAttribute(String name, int scope) {
        if(scope== RequestAttributes.SCOPE_REQUEST) {
            this.requestAttributeMap.remove(name);
        }
    }

    public void removeAllAttribute(int scope) {
        if(scope== RequestAttributes.SCOPE_REQUEST) {
            this.requestAttributeMap.clear();
        }
    }

    @Override
    public String[] getAttributeNames(int scope) {
        if(scope== RequestAttributes.SCOPE_REQUEST) {
            return this.requestAttributeMap.keySet().toArray(new String[0]);
        }
        return  new String[0];
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback, int scope) {

    }

    @Override
    public Object resolveReference(String key) {
        return null;
    }

    @Override
    public String getSessionId() {
        return null;
    }

    @Override
    public Object getSessionMutex() {
        return null;
    }
}