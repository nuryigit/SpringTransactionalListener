package com.ny.listener.listener.impl;

import com.ny.listener.listener.services.PaymentService;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@Service
public class PayPalServiceImpl implements PaymentService {

  @Override
  public void pay() {
    System.out.println("paypal service");
  }

  public void emptyLoop() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    for (int i = 0; i < 100; i++) {
      System.out.println("i" + i);
    }

    System.out.println(RequestContextHolder.getRequestAttributes());


    MDC.getCopyOfContextMap().forEach((key, value) -> System.out.println(""+key + ", val : "+ value));
  }
}
