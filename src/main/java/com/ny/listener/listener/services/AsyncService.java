package com.ny.listener.listener.services;

import org.springframework.scheduling.annotation.Async;

public interface AsyncService {

  @Async
  void logContextAsync();

  void rollback();
}
