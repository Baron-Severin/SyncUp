package com.applications.severin.baron.syncup.Utility;

import android.graphics.Bitmap;

/**
 * Created by Severin on 11/25/2016.
 */

public class BitmapScaler {

  public static Bitmap scaleToSizeSmall(Bitmap bitmap) {
    bitmap = scaleToFill(bitmap, 80, 80);
    return bitmap;
  }

  public static Bitmap scaleToSizeMedium(Bitmap bitmap) {
    bitmap = scaleToFill(bitmap, 300, 300);
    return bitmap;
  }

  // scale and keep aspect ratio
  private static Bitmap scaleToFitWidth(Bitmap b, int width)
  {
    float factor = width / (float) b.getWidth();
    return Bitmap.createScaledBitmap(b, width, (int) (b.getHeight() * factor), true);
  }


  // scale and keep aspect ratio
  private static Bitmap scaleToFitHeight(Bitmap b, int height)
  {
    float factor = height / (float) b.getHeight();
    return Bitmap.createScaledBitmap(b, (int) (b.getWidth() * factor), height, true);
  }


  // scale and keep aspect ratio
  private static Bitmap scaleToFill(Bitmap b, int width, int height)
  {
    float factorH = height / (float) b.getHeight();
    float factorW = width / (float) b.getWidth();
    float factorToUse = (factorH > factorW) ? factorW : factorH;
    return Bitmap.createScaledBitmap(b, (int) (b.getWidth() * factorToUse),
            (int) (b.getHeight() * factorToUse), true);
  }


  // scale and don't keep aspect ratio
  private static Bitmap strechToFill(Bitmap b, int width, int height)
  {
    float factorH = height / (float) b.getHeight();
    float factorW = width / (float) b.getWidth();
    return Bitmap.createScaledBitmap(b, (int) (b.getWidth() * factorW),
            (int) (b.getHeight() * factorH), true);
  }
}