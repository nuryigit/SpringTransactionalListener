package com.ny.listener.listener;

import com.ny.listener.listener.impl.ListenerService;
import com.ny.listener.listener.services.AsyncService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Controller
@RequestMapping("/listener")
public class ListenerController {

  @Autowired
  ListenerService service;
  @Autowired
  HttpServletRequest httpServletRequest;


  @GetMapping(value = "/testAsync")
  public ResponseEntity testAsync()  {
    Collections.list(httpServletRequest.getHeaderNames()).forEach(header -> MDC.put(header, httpServletRequest.getHeader(header)));
    service.startAsync();
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
