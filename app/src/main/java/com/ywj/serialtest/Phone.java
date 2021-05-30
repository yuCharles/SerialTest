package com.ywj.serialtest;

import java.io.Serializable;

public class Phone implements Serializable {

  private static final long serialVersionUID = 1L;

  private String brand;

  public void setBrand(String brand) {
    this.brand = brand;
  }
}