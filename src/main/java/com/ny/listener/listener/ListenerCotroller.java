package com.ny.listener.listener;

import com.ny.listener.listener.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/listener")
public class ListenerCotroller {

  @Autowired
  ListenerService service;
  @Autowired
  PaymentService paymentService;

  @GetMapping(value = "/{val}")
  public ResponseEntity inquireCustomerAddress2(@PathVariable(value = "val") long val)  {
    service.print(val);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(value = "/deprecatedService")
  public ResponseEntity depracatedService()  {
    paymentService.pay();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(value = "/testAsync")
  public ResponseEntity testAsync()  {
    service.startAsync();
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
