package com.ny.listener.listener.event;

import com.transaction.phase.listener.listener.TransactionCompletionAdapter;
import com.transaction.phase.listener.listener.TransactionRunnableEvent;

public class AfterRollbackRunnableEvent extends TransactionRunnableEvent {

  public AfterRollbackRunnableEvent(TransactionCompletionAdapter adapter) {
    super(adapter);
  }

}
