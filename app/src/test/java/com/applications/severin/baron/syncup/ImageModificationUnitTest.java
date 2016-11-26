package com.applications.severin.baron.syncup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.applications.severin.baron.syncup.Database.LocalDbHelper;
import com.applications.severin.baron.syncup.Utility.BitmapScaler;
import com.applications.severin.baron.syncup.Utility.PictureTextConverter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "/src/main/AndroidManifest.xml")
public class ImageModificationUnitTest {

  Context mContext;
  Bitmap uglyCookie;

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Before
  public void setup() {
    mContext = RuntimeEnvironment.application;
    uglyCookie = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.cookie_small);
  }

  @Test
  public void stringConversion_resultsInSameByteCount() throws Exception {
    String encodedPic = PictureTextConverter.bitmapToString(uglyCookie);
    Bitmap decoded = PictureTextConverter.stringToBitmap(encodedPic);

    assertEquals(uglyCookie.getByteCount(), decoded.getByteCount());
  }

  @Test
  public void imageScale_createsCorrectHeightOrWidth() throws Exception {
    Bitmap smallPic = BitmapScaler.scaleToSizeSmall(uglyCookie);
    Bitmap mediumPic = BitmapScaler.scaleToSizeMedium(uglyCookie);

    int smallH = smallPic.getHeight();
    int smallW = smallPic.getWidth();
    int medH = mediumPic.getHeight();
    int medW = mediumPic.getWidth();

    assertTrue(smallH == BitmapScaler.SMALL_PIC || smallW == BitmapScaler.SMALL_PIC);
    assertTrue(medH == BitmapScaler.MEDIUM_PIC || medW == BitmapScaler.MEDIUM_PIC);
  }
}