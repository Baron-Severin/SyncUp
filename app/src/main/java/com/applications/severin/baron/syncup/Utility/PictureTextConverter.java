package com.applications.severin.baron.syncup.Utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by erikrudie on 11/22/16.
 */

public abstract class PictureTextConverter {

  public static String bitmapToString(Bitmap bitmap) {

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
    byte[] b = baos.toByteArray();
    String encodedImage = Base64.encodeToString(b, Base64.URL_SAFE);
    return encodedImage;
  }

  public static Bitmap stringToBitmap(String encodedImage) {

    byte[] decodedString = Base64.decode(encodedImage, Base64.URL_SAFE);
    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    return decodedByte;
  }


}
