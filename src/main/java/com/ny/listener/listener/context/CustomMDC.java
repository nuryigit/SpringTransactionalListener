package com.ny.listener.listener.context;

import org.slf4j.spi.MDCAdapter;
import org.springframework.web.context.request.RequestAttributes;

import java.io.Closeable;
import java.util.Map;

public class CustomMDC {
    static final String NULL_MDCA_URL = "http://www.slf4j.org/codes.html#null_MDCA";
    static final String NO_STATIC_MDC_BINDER_URL = "http://www.slf4j.org/codes.html#no_static_mdc_binder";
    static MDCAdapter mdcAdapter;

    static RequestAttributes requestAttributes;

    private CustomMDC() {
    }


    public static void put(RequestAttributes requestAttributes1) throws IllegalArgumentException {
        requestAttributes = requestAttributes1;
    }

    public static RequestAttributes get() throws IllegalArgumentException {
       return requestAttributes;
    }

    public static void remove(String key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("key parameter cannot be null");
        } else if (mdcAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        } else {
            mdcAdapter.remove(key);
        }
    }

    public static void clear() {
        if (mdcAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        } else {
            mdcAdapter.clear();
        }
    }

    public static Map<String, String> getCopyOfContextMap() {
        if (mdcAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        } else {
            return mdcAdapter.getCopyOfContextMap();
        }
    }

    public static void setContextMap(Map<String, String> contextMap) {
        if (mdcAdapter == null) {
            throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
        } else {
            mdcAdapter.setContextMap(contextMap);
        }
    }

    public static MDCAdapter getMDCAdapter() {
        return mdcAdapter;
    }


    public static class MDCCloseable implements Closeable {
        private final String key;

        private MDCCloseable(String key) {
            this.key = key;
        }

        public void close() {
            org.slf4j.MDC.remove(this.key);
        }
    }
}

