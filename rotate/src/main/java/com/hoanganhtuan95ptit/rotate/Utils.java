package com.hoanganhtuan95ptit.rotate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Hoang Anh Tuan on 10/27/2017.
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

    static Bitmap rotateBitmap(Bitmap bitmap, int rotate) {

        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        Bitmap resultBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        bitmap.recycle();

        Bitmap newBitmap = Bitmap.createBitmap(resultBitmap.getWidth(), resultBitmap.getHeight(), resultBitmap.getConfig());
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(resultBitmap, 0, 0, null);

        resultBitmap.recycle();

        return newBitmap;
    }
}
