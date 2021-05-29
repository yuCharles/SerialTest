package com.ywj.serialtest;


import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelPhone implements Parcelable, Serializable {
  private String brand;
  private int number;

  public ParcelPhone() {
  }

  protected ParcelPhone(Parcel in) {
    brand = in.readString();
    number = in.readInt();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(brand);
    dest.writeInt(number);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<ParcelPhone> CREATOR = new Creator<ParcelPhone>() {
    @Override
    public ParcelPhone createFromParcel(Parcel in) {
      return new ParcelPhone(in);
    }

    @Override
    public ParcelPhone[] newArray(int size) {
      return new ParcelPhone[size];
    }
  };

  public void setNumber(int number) {
    this.number = number;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }
}