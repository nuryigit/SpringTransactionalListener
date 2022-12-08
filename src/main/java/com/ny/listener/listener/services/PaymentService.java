package com.ny.listener.listener.services;

import org.springframework.scheduling.annotation.Async;

public interface PaymentService {
  void pay();

  @Async
  void emptyLoop();
}
