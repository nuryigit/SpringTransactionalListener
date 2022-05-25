package com.ny.listener.listener.event;

import com.transaction.phase.listener.listener.TransactionCompletionAdapter;
import com.transaction.phase.listener.listener.TransactionRunnableEvent;

public class BeforeCommitRunnableEvent extends TransactionRunnableEvent {

  public BeforeCommitRunnableEvent(TransactionCompletionAdapter adapter) {
    super(adapter);
  }

  @Override
  public boolean isTransactional() {
    return Boolean.FALSE;
  }

  @Override
  public boolean isReadOnlyTransactional() {
    return Boolean.FALSE;
  }
}

