package com.ny.listener.listener.impl;

import com.ny.listener.listener.services.AsyncService;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AsyncServiceImpl implements AsyncService {

    Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    public void logContextAsync() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("exception at thread sleep, e : ", e);
        }

        logger.info("RequestContext: {}", RequestContextHolder.getRequestAttributes());
        MDC.getCopyOfContextMap().forEach((key, value) -> logger.info("RequestContext from MDC {}, val : {}", key, value));
    }

    public void rollback() {
        logger.info("if there is an exception and see this message this means : transaction rollbacked, everything works fine");
    }
}
