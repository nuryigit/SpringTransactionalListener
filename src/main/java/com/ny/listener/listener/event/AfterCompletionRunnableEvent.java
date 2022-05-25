package com.ny.listener.listener.event;

import com.transaction.phase.listener.listener.TransactionCompletionAdapter;
import com.transaction.phase.listener.listener.TransactionRunnableEvent;

public class AfterCompletionRunnableEvent extends TransactionRunnableEvent {

  public AfterCompletionRunnableEvent(TransactionCompletionAdapter adapter) {
    super(adapter);
  }

}
