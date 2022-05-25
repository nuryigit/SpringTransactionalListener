package com.ny.listener.listener.listener;


import com.transaction.phase.listener.event.AfterCommitRunnableEvent;
import com.transaction.phase.listener.event.AfterCompletionRunnableEvent;
import com.transaction.phase.listener.event.AfterRollbackRunnableEvent;
import com.transaction.phase.listener.event.BeforeCommitRunnableEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Listener {@link TransactionRunnableEventListener}
 */
@Component
public final class TransactionCompletionManager {

  private static ApplicationEventPublisher applicationEventPublisher;

  @Autowired
  public TransactionCompletionManager(ApplicationEventPublisher applicationEventPublisher) {
    TransactionCompletionManager.applicationEventPublisher = applicationEventPublisher;
  }

  public static void register(TransactionCompletionAdapter transactionCompletionAdapter) {
    TransactionRunnableEvent runnableEvent = convertToEvent(transactionCompletionAdapter);
    if (runnableEvent != null) applicationEventPublisher.publishEvent(runnableEvent);
  }

  private static TransactionRunnableEvent convertToEvent(TransactionCompletionAdapter transactionCompletionAdapter) {
    switch (transactionCompletionAdapter.getPhase()) {
      case AFTER_COMMIT:
        return new AfterCommitRunnableEvent(transactionCompletionAdapter);
      case AFTER_ROLLBACK:
        return new AfterRollbackRunnableEvent(transactionCompletionAdapter);
      case AFTER_COMPLETION:
        return new AfterCompletionRunnableEvent(transactionCompletionAdapter);
      case BEFORE_COMMIT:
        return new BeforeCommitRunnableEvent(transactionCompletionAdapter);
    }
    return null;
  }

}

