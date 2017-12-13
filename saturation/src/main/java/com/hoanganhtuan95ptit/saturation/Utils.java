package com.hoanganhtuan95ptit.saturation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Hoang Anh Tuan on 12/12/2017.
 */

class Utils {
    static Boolean saveBitmap(String output, Bitmap bitmap) {
        FileOutputStream out = null;
        boolean success = true;
        try {
            out = new FileOutputStream(output);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            if (!bitmap.isRecycled()) bitmap.recycle();
        } catch (Exception e) {
            success = false;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignored) {
            }
        }
        return success;
    }

    static Bitmap scaleDown(Bitmap realImage) {
        float ratio = Math.min((float) 1000 / realImage.getWidth(), (float) 1000 / realImage.getHeight());
        int width = Math.round(ratio * realImage.getWidth());
        int height = Math.round(ratio * realImage.getHeight());

        return Bitmap.createScaledBitmap(realImage, width, height, true);
    }

    static Bitmap getBitmapSdcard(String url) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(url, options);
    }

    static Bitmap saturationBitmap(Bitmap bitmap, float saturation) {

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(saturation);

        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        Paint paint = new Paint();
        paint.setColorFilter(colorFilter);

        Bitmap resultBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, paint);

        return resultBitmap;
    }

}
