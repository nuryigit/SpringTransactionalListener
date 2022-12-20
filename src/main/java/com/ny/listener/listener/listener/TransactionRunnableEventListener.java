package com.ny.listener.listener.listener;


import com.ny.listener.listener.event.AfterCommitRunnableEvent;
import com.ny.listener.listener.event.AfterCompletionRunnableEvent;
import com.ny.listener.listener.event.AfterRollbackRunnableEvent;
import com.ny.listener.listener.event.BeforeCommitRunnableEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TransactionRunnableEventListener {

  protected final Logger logger = LogManager.getLogger(this.getClass());

  @Resource
  private TransactionRunnableEventListener proxyTrigger;

  @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
  public void afterRollback(AfterRollbackRunnableEvent event) {
    this.run(TransactionPhase.AFTER_ROLLBACK, event);
  }

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void afterCommit(AfterCommitRunnableEvent event) {
    this.run(TransactionPhase.AFTER_COMMIT, event);
  }

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
  public void afterCompletion(AfterCompletionRunnableEvent event) {
    this.run(TransactionPhase.AFTER_COMPLETION, event);
  }

  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void beforeCommit(BeforeCommitRunnableEvent event) {
    this.run(TransactionPhase.BEFORE_COMMIT, event);
  }

  private void run(TransactionPhase transactionPhase, TransactionRunnableEvent event) {
    if (event.isReadOnlyTransactional()) {
      proxyTrigger.runWithReadOnlyTransaction(event);
    } else if (event.isTransactional()) {
      proxyTrigger.runWithTransaction(event);
    } else {
      if (event.isAsync()) {
        this.runAsync(event.getRunnable());
      } else {
        event.getRunnable().run();
      }
    }
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void runWithTransaction(TransactionRunnableEvent event) {
    if (event.isAsync()) {
      this.runAsync(event.getRunnable());
    } else {
      event.getRunnable().run();
    }
  }

  @Transactional(readOnly = true)
  public void runWithReadOnlyTransaction(TransactionRunnableEvent event) {
    if (event.isAsync()) {
      this.runAsync(event.getRunnable());
    } else {
      event.getRunnable().run();
    }
  }

  public void runAsync(Runnable runnable) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(runnable);
  }
}