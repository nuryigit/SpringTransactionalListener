package com.ny.listener.listener.event;

import com.ny.listener.listener.listener.TransactionCompletionAdapter;
import com.ny.listener.listener.listener.TransactionRunnableEvent;

public class AfterCompletionRunnableEvent extends TransactionRunnableEvent {

  public AfterCompletionRunnableEvent(TransactionCompletionAdapter adapter) {
    super(adapter);
  }

}
