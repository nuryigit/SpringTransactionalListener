package com.ny.listener.listener;


import com.ny.listener.listener.listener.TransactionCompletionAdapter;
import com.ny.listener.listener.listener.TransactionCompletionManager;
import com.ny.listener.listener.services.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class ListenerService {

    Logger logger = LoggerFactory.getLogger(ListenerService.class);

    @Autowired
    HttpServletRequest request;
    @Autowired
    AsyncService asyncService;
    @Autowired
    TransactionCompletionManager transactionCompletionManager;


    @Transactional
    public void print(long val) {
        transactionCompletionManager.register(TransactionCompletionAdapter.afterRollback(asyncService::rollback).withTransaction());
        checkVal(val);
    }

    private void checkVal(long val) {
        if (val < 0) {
            throw new NumberFormatException("O dan küçük olamaz");
        }
    }

    @Transactional
    public void startAsync() {
        logger.info("service called");

        setContext(1);

        asyncService.logContextAsync();
    }

    private void setContext(int value) {
        MDC.put("count", String.valueOf(value));
        MDC.put("callerThreadId", String.valueOf(Thread.currentThread().getId()));
    }
}
