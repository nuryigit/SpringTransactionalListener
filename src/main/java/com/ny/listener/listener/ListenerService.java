package com.ny.listener.listener;


import com.ny.listener.listener.listener.TransactionCompletionAdapter;
import com.ny.listener.listener.listener.TransactionCompletionManager;
import com.ny.listener.listener.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@Service
public class ListenerService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    private PaymentService payPalService;


    @Autowired
    @Qualifier("executorAsyncThread")
    private TaskExecutor taskExecutor;



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

        //payPalService.emptyLoop();
        //CompletableFuture.runAsync( () -> payPalService.emptyLoop(), taskExecutor);


        TransactionCompletionManager.register(TransactionCompletionAdapter.afterCompletion(() ->payPalService.emptyLoop()).withTransaction());
    }


}
