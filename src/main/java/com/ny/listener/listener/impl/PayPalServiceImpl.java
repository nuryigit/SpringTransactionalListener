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

    System.out.println("MDC------------------");
    MDC.getCopyOfContextMap().forEach((key, value) -> System.out.println(""+key + ", val : "+ value));
  }
}
