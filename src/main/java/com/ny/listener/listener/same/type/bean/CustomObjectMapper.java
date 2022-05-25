package com.ny.listener.listener.same.type.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomObjectMapper extends ObjectMapper {

  private long id;
  private static final long serialVersionUID = -987364856739313L;

  public CustomObjectMapper() {

  }

  public CustomObjectMapper(long id) {
    this.id = id;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void print(String name) {
    System.out.println(this.getId());
    System.out.println("print : " + name);
  }

  public void print() {
    System.out.println(this.getId());
  }

}
