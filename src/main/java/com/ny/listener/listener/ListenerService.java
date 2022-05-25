package com.ny.listener.listener;


import com.ny.listener.listener.listener.TransactionCompletionAdapter;
import com.ny.listener.listener.listener.TransactionCompletionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ListenerService {

  @Transactional
  public void print(long val) {
    if (val < 0) {
      checkException(val);
      TransactionCompletionManager.register(TransactionCompletionAdapter.afterRollback(this::rollback));
    }else  {
      System.out.println("it works");
    }

  }

  private void checkException(long val) {
    if (val < 0 ) {
      throw new NumberFormatException("O dan küçük olamaz");
    }
  }

  private void rollback() {
    System.out.println("rolled backed");
  }
}
