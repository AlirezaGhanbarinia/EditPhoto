# EditPhoto

android library EditPhoto. Image Editor for Android

# Demo

[<img src="/store/Gif.gif">](https://play.google.com/store/apps/details?id=com.hoanganhtuan95ptit.camdoc_business) 


# ️Features 
* Crop with 
```java
((EditPhotoActivity) getActivity()).addFragmentToStack(CropFragment.create(outputUrl, this));
```
* Filter with 
```java
((EditPhotoActivity) getActivity()).addFragmentToStack(FilterFragment.create(outputUrl, this));
```
* Rotate with 
```java
((EditPhotoActivity) getActivity()).addFragmentToStack(RotateFragment.create(outputUrl, this));
```
* Saturation with 
```java
((EditPhotoActivity) getActivity()).addFragmentToStack(SaturationFragment.create(outputUrl, this));
```
* Brightness with 
```java
((EditPhotoActivity) getActivity()).addFragmentToStack(BrightnessFragment.create(outputUrl, this));
```
* Contrast with 
```java
((EditPhotoActivity) getActivity()).addFragmentToStack(ContrastFragment.create(outputUrl, this));
```


# Download

* Step 1. Add it in your root build.gradle at the end of repositories:
```java
    allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
        }
    }
```
* Step 2. Add the dependency
```java
    dependencies {
        compile 'com.github.hoanganhtuan95ptit:EditPhoto:1.0.1'
    }
```

# Using

* Java

```java
        switch (type) {
            case Crop:
                ((EditPhotoActivity) getActivity()).addFragmentToStack(CropFragment.create(outputUrl, this));
                break;
            case Filter:
                ((EditPhotoActivity) getActivity()).addFragmentToStack(FilterFragment.create(outputUrl, this));
                break;
            case Rotate:
                ((EditPhotoActivity) getActivity()).addFragmentToStack(RotateFragment.create(outputUrl, this));
                break;
            case Saturation:
                ((EditPhotoActivity) getActivity()).addFragmentToStack(SaturationFragment.create(outputUrl, this));
                break;
            case Brightness:
                ((EditPhotoActivity) getActivity()).addFragmentToStack(BrightnessFragment.create(outputUrl, this));
                break;
            case Contrast:
                ((EditPhotoActivity) getActivity()).addFragmentToStack(ContrastFragment.create(outputUrl, this));
                break;
        }
```
# Thank 

 Name | Library
------------ | -------------
Yalantis | [UCrop](https://github.com/Yalantis/uCrop) 


# Project


[<img src="/store/Store.png">](https://play.google.com/store/apps/details?id=com.hoanganhtuan95ptit.camdoc_business)
