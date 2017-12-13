package com.hoanganhtuan95ptit.fillter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;

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
        Bitmap bitmap = BitmapFactory.decodeFile(url, options);
        return bitmap;
    }

    public static Bitmap rotate(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static void rateApp(Context context) {
        final Uri uri = Uri.parse("market://details?id=" + context.getApplicationContext().getPackageName());
        final Intent rateAppIntent = new Intent(Intent.ACTION_VIEW, uri);
        if (context.getPackageManager().queryIntentActivities(rateAppIntent, 0).size() > 0) {
            context.startActivity(rateAppIntent);
        }
    }

}
