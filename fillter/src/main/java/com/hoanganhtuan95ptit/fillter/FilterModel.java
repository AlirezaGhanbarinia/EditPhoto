package com.hoanganhtuan95ptit.fillter;

import com.hoanganhtuan95ptit.fillter.library.filter.FilterManager;

/**
 * Created by Hoang Anh Tuan on 12/3/2017.
 */

class FilterModel {
    private FilterManager.FilterType type;
    private int img;

    public FilterModel(FilterManager.FilterType type, int img) {
        this.type = type;
        this.img = img;
    }

    public FilterManager.FilterType getType() {
        return type;
    }

    public void setType(FilterManager.FilterType type) {
        this.type = type;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

}
