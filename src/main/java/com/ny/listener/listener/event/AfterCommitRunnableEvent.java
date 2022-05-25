package com.ny.listener.listener.event;


import com.transaction.phase.listener.listener.TransactionCompletionAdapter;
import com.transaction.phase.listener.listener.TransactionRunnableEvent;

public class AfterCommitRunnableEvent extends TransactionRunnableEvent {

  public AfterCommitRunnableEvent(TransactionCompletionAdapter adapter) {
    super(adapter);
  }

}
