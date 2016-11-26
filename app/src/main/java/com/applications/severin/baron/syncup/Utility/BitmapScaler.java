package com.applications.severin.baron.syncup.Utility;

import android.graphics.Bitmap;

/**
 * Created by Severin on 11/25/2016.
 */

public class BitmapScaler {

  public static final int SMALL_PIC = 80;
  public static final int MEDIUM_PIC = 300;

  public static Bitmap scaleToSizeSmall(Bitmap bitmap) {
    bitmap = scaleToFill(bitmap, SMALL_PIC, SMALL_PIC);
    return bitmap;
  }

  public static Bitmap scaleToSizeMedium(Bitmap bitmap) {
    bitmap = scaleToFill(bitmap, MEDIUM_PIC, MEDIUM_PIC);
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