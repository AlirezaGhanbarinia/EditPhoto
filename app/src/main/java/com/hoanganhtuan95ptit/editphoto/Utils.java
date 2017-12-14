package com.hoanganhtuan95ptit.editphoto;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Hoang Anh Tuan on 10/27/2017.
 */

public class Utils {

    public static Boolean saveBitmap(String output, Bitmap bitmap) {
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

    public static Bitmap scaleDown(Bitmap realImage) {
        float ratio = Math.min(1000.0F / (float)realImage.getWidth(), 1000.0F / (float)realImage.getHeight());
        int width = Math.round(ratio * (float)realImage.getWidth());
        int height = Math.round(ratio * (float)realImage.getHeight());
        return Bitmap.createScaledBitmap(realImage, width, height, true);
    }

    public static Bitmap getBitmapSdcard(String url) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(url, options);
//        if (bitmap.getWidth() > bitmap.getHeight()) {
//            bitmap = rotate(bitmap);
//        }
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


    public static void shareFile(Context context, String url) {
        try {
            File myFile = new File(url);
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            String ext = myFile.getName().substring(myFile.getName().lastIndexOf(".") + 1);
            String type = mime.getMimeTypeFromExtension(ext);
            Intent sharingIntent = new Intent("android.intent.action.SEND");
            sharingIntent.setType(type);
            sharingIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(myFile));
            context.startActivity(Intent.createChooser(sharingIntent, "Share using"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readAllPhoto(Activity activity, PhotoCallback photoCallback) {
        new PhotoTask(activity, photoCallback).execute();
    }

    public static Bitmap brightBitmap(Bitmap bitmap, int brightness) {
        float[] colorTransform = {
                1, 0, 0, 0, brightness,
                0, 1, 0, 0, brightness,
                0, 0, 1, 0, brightness,
                0, 0, 0, 1, 0};

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0f);
        colorMatrix.set(colorTransform);

        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        Paint paint = new Paint();
        paint.setColorFilter(colorFilter);


        Bitmap resultBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, paint);

        return resultBitmap;
    }

    public static Bitmap contrastBitmap(Bitmap bitmap, int contrast) {
        float[] colorTransform = {
                contrast, 0, 0, 0, 1,
                0, contrast, 0, 0, 1,
                0, 0, contrast, 0, 1,
                0, 0, 0, 1, 0};

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0f);
        colorMatrix.set(colorTransform);

        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        Paint paint = new Paint();
        paint.setColorFilter(colorFilter);


        Bitmap resultBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, paint);

        return resultBitmap;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int rotate) {

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

    private static class PhotoTask extends AsyncTask<String, String, Object> {

        @SuppressLint("StaticFieldLeak")
        private Activity activity;
        private PhotoCallback callback;

        PhotoTask(Activity activity, PhotoCallback callback) {
            this.activity = activity;
            this.callback = callback;
        }

        @Override
        protected Object doInBackground(String... params) {
            final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
            final String orderBy = MediaStore.Images.Media._ID;
            Cursor imagecursor = activity.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);
            assert imagecursor != null;
            for (int i = 0; i < imagecursor.getCount(); i++) {
                imagecursor.moveToPosition(i);
                int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
                publishProgress(imagecursor.getString(dataColumnIndex));
            }
            imagecursor.close();
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            callback.onProgressUpdateListPhoto(values[0]);
        }
    }

    public interface PhotoCallback {
        void onProgressUpdateListPhoto(String url);
    }
}
