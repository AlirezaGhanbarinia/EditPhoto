package com.hoanganhtuan95ptit.fillter.library.filter;

import android.content.Context;

import com.hoanganhtuan95ptit.fillter.R;

public class FilterManager {

    private static int mCurveIndex;
    private static int[] mCurveArrays = new int[]{
            R.raw.cross_1,
            R.raw.cross_2,
            R.raw.cross_3,
            R.raw.cross_4,
            R.raw.cross_5,
            R.raw.cross_6,
            R.raw.cross_7,
            R.raw.cross_8,
            R.raw.cross_9,
            R.raw.cross_10,
            R.raw.cross_11,
    };

    private FilterManager() {
    }

    public static IFilter getCameraFilter(FilterType filterType, Context context) {
        switch (filterType) {
            case Normal:
            default:
                return new CameraFilter(context);
            case Blend:
                return new CameraFilterBlend(context, R.drawable.mask);
            case SoftLight:
                return new CameraFilterBlendSoftLight(context, R.drawable.mask);
            case Filter1:
                return new CameraFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[0]));
            case Filter2:
                return new CameraFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[1]));
            case Filter3:
                return new CameraFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[2]));
            case Filter4:
                return new CameraFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[3]));
            case Filter5:
                return new CameraFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[4]));
            case Filter6:
                return new CameraFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[5]));
            case Filter7:
                return new CameraFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[6]));
            case Filter8:
                return new CameraFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[7]));
            case Filter9:
                return new CameraFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[8]));
            case Filter10:
                return new CameraFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[9]));
            case Filter11:
                return new CameraFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[10]));
            case ToneCurve:
                mCurveIndex++;
                if (mCurveIndex > 10) {
                    mCurveIndex = 0;
                }
                return new CameraFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[mCurveIndex]));
        }
    }

    public static IFilter getImageFilter(FilterType filterType, Context context) {
        switch (filterType) {
            case Normal:
            default:
                return new ImageFilter(context);
            case Blend:
                return new ImageFilterBlend(context, R.drawable.mask);
            case SoftLight:
                return new ImageFilterBlendSoftLight(context, R.drawable.mask);
            case Filter1:
                return new ImageFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[0]));
            case Filter2:
                return new ImageFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[1]));
            case Filter3:
                return new ImageFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[2]));
            case Filter4:
                return new ImageFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[3]));
            case Filter5:
                return new ImageFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[4]));
            case Filter6:
                return new ImageFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[5]));
            case Filter7:
                return new ImageFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[6]));
            case Filter8:
                return new ImageFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[7]));
            case Filter9:
                return new ImageFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[8]));
            case Filter10:
                return new ImageFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[9]));
            case Filter11:
                return new ImageFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[10]));
            case ToneCurve:
                mCurveIndex++;
                if (mCurveIndex > 10) {
                    mCurveIndex = 0;
                }
                return new ImageFilterToneCurve(context,
                        context.getResources().openRawResource(mCurveArrays[mCurveIndex]));

        }
    }

    public enum FilterType {
        Normal,
        Blend,
        SoftLight,
        ToneCurve,
        Filter1,
        Filter2,
        Filter3,
        Filter4,
        Filter5,
        Filter6,
        Filter7,
        Filter8,
        Filter9,
        Filter10,
        Filter11,
    }
}
