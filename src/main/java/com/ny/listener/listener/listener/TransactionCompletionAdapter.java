package com.ny.listener.listener.listener;


import org.springframework.transaction.event.TransactionPhase;


public class TransactionCompletionAdapter {

  private final TransactionPhase phase;
  private final Runnable runnable;
  /**
   * Covering Runnable with Transaction
   * Only use if there is db save/update operations
   * TransactionPhase.BEFORE_COMMIT not supported (This phase already in transaction)
   */
  private boolean transactional;
  /**
   * Covering Runnable with read only Transaction
   * Only use if there is db read operations
   * TransactionPhase.BEFORE_COMMIT not supported (This phase already in transaction)
   */
  private boolean readOnlyTransactional;
  /**
   * Async operation
   */
  private boolean async;
  public TransactionCompletionAdapter(TransactionPhase phase, Runnable runnable) {
    this.phase = phase;
    this.runnable = runnable;
  }

  public TransactionCompletionAdapter withTransaction() {
    this.transactional = true;
    return this;
  }

  public TransactionCompletionAdapter withReadOnlyTransaction() {
    this.readOnlyTransactional = true;
    return this;
  }

  public TransactionCompletionAdapter async() {
    this.async = true;
    return this;
  }

  public static TransactionCompletionAdapter afterRollback(Runnable runnable) {
    return new TransactionCompletionAdapter(TransactionPhase.AFTER_ROLLBACK, runnable);
  }

  public static TransactionCompletionAdapter afterCommit(Runnable runnable) {
    return new TransactionCompletionAdapter(TransactionPhase.AFTER_COMMIT, runnable);
  }

  public static TransactionCompletionAdapter afterCompletion(Runnable runnable) {
    return new TransactionCompletionAdapter(TransactionPhase.AFTER_COMPLETION, runnable);
  }

  public static TransactionCompletionAdapter beforeCommit(Runnable runnable) {
    return new TransactionCompletionAdapter(TransactionPhase.BEFORE_COMMIT, runnable);
  }

  public TransactionPhase getPhase() {
    return phase;
  }

  public Runnable getRunnable() {
    return runnable;
  }

  public boolean isTransactional() {
    return transactional;
  }

  public void setTransactional(boolean transactional) {
    this.transactional = transactional;
  }

  public boolean isReadOnlyTransactional() {
    return readOnlyTransactional;
  }

  public void setReadOnlyTransactional(boolean readOnlyTransactional) {
    this.readOnlyTransactional = readOnlyTransactional;
  }

  public boolean isAsync() {
    return async;
  }

  public void setAsync(boolean async) {
    this.async = async;
  }
}