package com.ny.listener.listener.impl;

import com.transaction.phase.listener.services.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PayPalServiceImpl implements PaymentService {

  @Override
  public void pay() {
    System.out.println("paypal service");
  }
}
