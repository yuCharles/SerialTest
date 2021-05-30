package com.ywj.serialtest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class BigPhone implements Serializable, Parcelable {
  private static final long serialVersionUID = 1L;

  public List<BigPhone> children;
  private int number;
  private String brand;
  private String brand2;
  private String brand3;

  protected BigPhone(Parcel in) {
    children = in.createTypedArrayList(BigPhone.CREATOR);
    number = in.readInt();
    brand = in.readString();
    brand2 = in.readString();
    brand3 = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeTypedList(children);
    dest.writeInt(number);
    dest.writeString(brand);
    dest.writeString(brand2);
    dest.writeString(brand3);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<BigPhone> CREATOR = new Creator<BigPhone>() {
    @Override
    public BigPhone createFromParcel(Parcel in) {
      return new BigPhone(in);
    }

    @Override
    public BigPhone[] newArray(int size) {
      return new BigPhone[size];
    }
  };

  public void setBrand2(String brand2) {
    this.brand2 = brand2;
  }

  public void setBrand3(String brand3) {
    this.brand3 = brand3;
  }

  public BigPhone() {
  }

  public List<BigPhone> getChildren() {
    return children;
  }

  public String getBrand() {
    return brand;
  }

  public int getNumber() {
    return number;
  }

  public void setChildren(ArrayList<BigPhone> children) {
    this.children = children;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public void setNumber(int number) {
    this.number = number;
  }


}
