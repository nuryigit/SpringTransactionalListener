package com.ny.listener.listener.event;


import com.ny.listener.listener.listener.TransactionCompletionAdapter;
import com.ny.listener.listener.listener.TransactionRunnableEvent;

public class AfterCommitRunnableEvent extends TransactionRunnableEvent {

  public AfterCommitRunnableEvent(TransactionCompletionAdapter adapter) {
    super(adapter);
  }

}
