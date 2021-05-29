package com.ywj.serialtest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private String path = "";
  private Button mButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mButton = findViewById(R.id.bt_test);
    path = getFilesDir().getPath();
    mButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        doParcalable();
        doSerializable();
      }
    });
    main();
  }

  public void main() {
    String FILE_PATH = path + "info.txt";


    ListNode node = new ListNode(1);


    try {
      File f = new File(FILE_PATH);
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH));

      Phone phone = new Phone();
      phone.setBrand("OnePlus");

      out.writeObject(phone);
      out.flush();
      out.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void doParcalable() {
    long writeStart;
    long writeEnd;
    long readStart;
    long readEnd;

    Parcel parcel;
    int dataStartPos;
    ParcelPhone curPhone;
    parcel = Parcel.obtain();
    curPhone = createPhone();

    writeStart = System.nanoTime();
    dataStartPos = parcel.dataPosition();
    parcel.writeParcelable(curPhone, 0);
    writeEnd = System.nanoTime();

    int length = parcel.marshall().length;

    parcel.setDataPosition(dataStartPos);

    readStart = System.nanoTime();
    ParcelPhone.CREATOR.createFromParcel(parcel);
    readEnd = System.nanoTime();
    Log.d("PARCELTEST", "parcel: " +
        (writeEnd - writeStart) / 1000 + "微秒; unparcel: " +
        (readEnd - readStart) / 1000 +
        "微秒; Size: " + length);
  }

  private void doSerializable() {
    long writeStart;
    long writeEnd;
    long readStart;
    long readEnd;

    ByteArrayOutputStream dataOut;
    ByteArrayInputStream dataIn;
    try {
      ObjectOutputStream out;
      ObjectInputStream in;

      dataOut = new ByteArrayOutputStream();
      out = new ObjectOutputStream(dataOut);
      ParcelPhone phone = createPhone();
      writeStart = System.nanoTime();
      out.writeObject(phone);
      writeEnd = System.nanoTime();
      out.flush();

      byte[] data = dataOut.toByteArray();
      int lenght = data.length;
      dataIn = new ByteArrayInputStream(data);
      readStart = System.nanoTime();
      in = new ObjectInputStream(dataIn);
      in.readObject();
      readEnd = System.nanoTime();

      Log.d("PARCELTEST", "Serialiazable: " + (writeEnd - writeStart) / 1000
          + "微秒; unparcel: " + (readEnd - readStart) / 1000
          + " 微秒; Size: " + lenght);

    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private ParcelPhone createPhone() {
    ParcelPhone parcelPhone = new ParcelPhone();
    parcelPhone.setBrand("OnePlus");
    parcelPhone.setNumber(185);
    return parcelPhone;
  }

}

