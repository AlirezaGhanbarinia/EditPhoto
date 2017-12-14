package com.hoanganhtuan95ptit.editphoto.model;


import com.hoanganhtuan95ptit.editphoto.R;

/**
 * Created by Hoang Anh Tuan on 11/29/2017.
 */

public enum EditType {
    Crop(R.drawable.ic_crop),
    Filter(R.drawable.ic_filter),
    Rotate(R.drawable.ic_rotate),
    Saturation(R.drawable.ic_saturation),
    Brightness(R.drawable.ic_brightness),
    Contrast(R.drawable.ic_contrast);

    public int VALUE;

    EditType(int VALUE) {
        this.VALUE = VALUE;
    }
}
