package com.ywj.serialtest;

import java.io.Serializable;

public class Phone implements Serializable {
  private String brand;

  public void setBrand(String brand) {
    this.brand = brand;
  }
}