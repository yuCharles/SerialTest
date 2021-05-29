package com.ywj.serialtest;

import java.io.Serializable;

public class Card implements Serializable {
  private String number;

  public Card(String number) {
    this.number = number;
  }
}