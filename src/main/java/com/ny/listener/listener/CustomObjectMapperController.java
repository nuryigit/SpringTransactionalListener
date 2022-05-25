package com.ny.listener.listener;

import com.transaction.phase.listener.same.type.bean.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mapper")
public class CustomObjectMapperController {

  @Autowired
  CustomObjectMapper customObjectMapper;
  @Autowired
  CustomObjectMapper maskedCustomObjectMapper;

  @GetMapping()
  public ResponseEntity inquireCustomerAddress2()  {
    customObjectMapper.print();
    maskedCustomObjectMapper.print();
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
