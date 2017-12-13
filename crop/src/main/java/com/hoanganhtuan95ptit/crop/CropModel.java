package com.hoanganhtuan95ptit.crop;

/**
 * Created by Hoang Anh Tuan on 11/30/2017.
 */

class CropModel {
    private int img;
    private String name;

    private Type type;

    public CropModel(int img, String name, Type type) {
        this.img = img;
        this.name = name;
        this.type = type;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        TYPE11,
        TYPE23,
        TYPE32,
        TYPE43,
        TYPE34,
        TYPE169,
        TYPE916
    }
}
