package com.hoanganhtuan95ptit.fillter.library.filter.BlurFilter;

import android.content.Context;

import com.hoanganhtuan95ptit.fillter.library.filter.CameraFilter;
import com.hoanganhtuan95ptit.fillter.library.filter.FilterGroup;

public class CameraFilterGaussianBlur extends FilterGroup<CameraFilter> {

    public CameraFilterGaussianBlur(Context context, float blur) {
        super();
        addFilter(new CameraFilterGaussianSingleBlur(context, blur, false));
        addFilter(new CameraFilterGaussianSingleBlur(context, blur, true));
    }
}
