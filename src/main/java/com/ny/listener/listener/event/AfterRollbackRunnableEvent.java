package com.ny.listener.listener.event;

import com.ny.listener.listener.listener.TransactionCompletionAdapter;
import com.ny.listener.listener.listener.TransactionRunnableEvent;

public class AfterRollbackRunnableEvent extends TransactionRunnableEvent {

  public AfterRollbackRunnableEvent(TransactionCompletionAdapter adapter) {
    super(adapter);
  }

}
