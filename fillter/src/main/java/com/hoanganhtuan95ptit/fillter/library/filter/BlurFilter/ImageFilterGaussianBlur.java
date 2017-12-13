package com.hoanganhtuan95ptit.fillter.library.filter.BlurFilter;

import android.content.Context;

import com.hoanganhtuan95ptit.fillter.library.filter.CameraFilter;
import com.hoanganhtuan95ptit.fillter.library.filter.FilterGroup;

public class ImageFilterGaussianBlur extends FilterGroup<CameraFilter> {

    public ImageFilterGaussianBlur(Context context, float blur) {
        super();
        addFilter(new ImageFilterGaussianSingleBlur(context, blur, false));
        addFilter(new ImageFilterGaussianSingleBlur(context, blur, true));
    }
}
