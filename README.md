# EditPhoto

android library EditPhoto. Image Editor for Android

# Demo

[<img src="/store/Gif.gif">](https://play.google.com/store/apps/details?id=com.hoanganhtuan95ptit.camdoc_business) 


# ️Features 
* Crop with 
```java
((EditPhotoActivity) getActivity()).addFragmentToStack(CropFragment.create(inputUrl, this));
```
* Filter with 
```java
((EditPhotoActivity) getActivity()).addFragmentToStack(FilterFragment.create(inputUrl, this));
```
* Rotate with 
```java
((EditPhotoActivity) getActivity()).addFragmentToStack(RotateFragment.create(inputUrl, this));
```
* Saturation with 
```java
((EditPhotoActivity) getActivity()).addFragmentToStack(SaturationFragment.create(inputUrl, this));
```
* Brightness with 
```java
((EditPhotoActivity) getActivity()).addFragmentToStack(BrightnessFragment.create(inputUrl, this));
```
* Contrast with 
```java
((EditPhotoActivity) getActivity()).addFragmentToStack(ContrastFragment.create(inputUrl, this));
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
                ((EditPhotoActivity) getActivity()).addFragmentToStack(CropFragment.create(inputUrl, this));
                break;
            case Filter:
                ((EditPhotoActivity) getActivity()).addFragmentToStack(FilterFragment.create(inputUrl, this));
                break;
            case Rotate:
                ((EditPhotoActivity) getActivity()).addFragmentToStack(RotateFragment.create(inputUrl, this));
                break;
            case Saturation:
                ((EditPhotoActivity) getActivity()).addFragmentToStack(SaturationFragment.create(inputUrl, this));
                break;
            case Brightness:
                ((EditPhotoActivity) getActivity()).addFragmentToStack(BrightnessFragment.create(inputUrl, this));
                break;
            case Contrast:
                ((EditPhotoActivity) getActivity()).addFragmentToStack(ContrastFragment.create(inputUrl, this));
                break;
        }
```
# Thank 

 Name | Library
------------ | -------------
Yalantis | [UCrop](https://github.com/Yalantis/uCrop) 


# Project


[<img src="/store/Store.png">](https://play.google.com/store/apps/details?id=com.hoanganhtuan95ptit.camdoc_business)
