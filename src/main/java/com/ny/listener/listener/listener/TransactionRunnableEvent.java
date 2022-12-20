package com.ny.listener.listener.listener;

public abstract class TransactionRunnableEvent {

  private final Runnable runnable;
  private final boolean transactional;
  private final boolean readOnlyTransactional;
  private final boolean async;

  public TransactionRunnableEvent(TransactionCompletionAdapter adapter) {
    this.runnable = adapter.getRunnable();
    this.transactional = adapter.isTransactional();
    this.readOnlyTransactional = adapter.isReadOnlyTransactional();
    this.async = adapter.isAsync();
  }

  public Runnable getRunnable() {
    return runnable;
  }

  public boolean isTransactional() {
    return transactional;
  }

  public boolean isReadOnlyTransactional() {
    return readOnlyTransactional;
  }

  public boolean isAsync() {
    return async;
  }

}

