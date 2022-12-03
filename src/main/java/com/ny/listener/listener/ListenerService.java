package com.ny.listener.listener;


import com.ny.listener.listener.decorator.ContextAwareExecutorDecorator;
import com.ny.listener.listener.listener.TransactionCompletionAdapter;
import com.ny.listener.listener.listener.TransactionCompletionManager;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ListenerService {

    @Autowired
    HttpServletRequest request;
    @Autowired
    @Qualifier("asyncTaskExecutor")
    private Executor executor;

    @Transactional
    public void print(long val) {
        if (val < 0) {
            checkException(val);
            TransactionCompletionManager.register(TransactionCompletionAdapter.afterRollback(this::rollback));
        } else {
            System.out.println("it works");
        }

    }

    private void checkException(long val) {
        if (val < 0) {
            throw new NumberFormatException("O dan küçük olamaz");
        }
    }

    private void rollback() {
        System.out.println("rolled backed");
    }


    @Transactional
    public void startAsync() {
        System.out.println("started");

        ContextAwareExecutorDecorator contextAwareExecutorDecorator = new ContextAwareExecutorDecorator(executor);
        contextAwareExecutorDecorator.execute(this::emptyLoop);


        //pass without request
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        //executorService.submit(this::emptyLoop);

        //failed
        //TransactionCompletionManager.register(TransactionCompletionAdapter.afterCommit(this::emptyLoop));
    }

    @Async
    public void emptyLoop() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println("i" + i);
        }


        Collections.list(request.getHeaderNames()).forEach(header -> System.out.println(""+header + ", val : "+ request.getHeader(header)));
    }
}
