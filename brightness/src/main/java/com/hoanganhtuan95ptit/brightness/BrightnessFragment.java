package com.hoanganhtuan95ptit.brightness;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hoanganhtuan95ptit.library.TwoLineSeekBar;
import com.wang.avi.AVLoadingIndicatorView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Hoang Anh Tuan on 12/12/2017.
 */

public class BrightnessFragment extends Fragment implements View.OnClickListener, TwoLineSeekBar.OnSeekChangeListener {

    private static final String INPUT_URL = "inputUrl";

    BrightnessView brightnessView;
    AVLoadingIndicatorView ivLoading;
    TextView tvProcess;
    LinearLayout llProcess;
    TwoLineSeekBar seekBar;
    ImageView ivCancel;
    TextView tvTitle;
    ImageView ivCheck;
    LinearLayout controller;
    RelativeLayout rootBrightness;

    private String inputUrl;
    private boolean start = true;

    public static BrightnessFragment create(String inputUrl, OnBrightnessListener onBrightnessListener) {
        BrightnessFragment fragment = new BrightnessFragment();
        fragment.setOnBrightnessListener(onBrightnessListener);
        Bundle bundle = new Bundle();
        bundle.putString(INPUT_URL, inputUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressLint("ValidFragment")
    private BrightnessFragment() {
    }

    private OnBrightnessListener onBrightnessListener;

    public void setOnBrightnessListener(OnBrightnessListener onBrightnessListener) {
        this.onBrightnessListener = onBrightnessListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_brightness, container, false);
        mappingView(view);
        return view;
    }

    private void mappingView(View view) {
        brightnessView = view.findViewById(R.id.brightnessView);
        ivLoading = view.findViewById(R.id.ivLoading);
        tvProcess = view.findViewById(R.id.tvProcess);
        llProcess = view.findViewById(R.id.llProcess);
        seekBar = view.findViewById(R.id.seekBar);
        ivCancel = view.findViewById(R.id.ivCancel);
        tvTitle = view.findViewById(R.id.tvTitle);
        ivCheck = view.findViewById(R.id.ivCheck);
        controller = view.findViewById(R.id.controller);
        rootBrightness = view.findViewById(R.id.rootBrightness);

        ivCancel.setOnClickListener(this);
        ivCheck.setOnClickListener(this);
        rootBrightness.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {

        seekBar.reset();
        seekBar.setSeekLength(-1000, 1000, 0, 1f);
        seekBar.setOnSeekChangeListener(this);
        seekBar.setValue(0);

        if (getArguments() != null) {
            inputUrl = getArguments().getString(INPUT_URL);
            showImage();
        }
    }

    private void showImage() {
        Observable.just(inputUrl)
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String url) throws Exception {
                        return getBitmap(url);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        showLoading();
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        brightnessView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        hideLoading();
                    }
                });
    }

    private Bitmap getBitmap(String inputUrl) {
        Bitmap bitmap = Utils.getBitmapSdcard(inputUrl);
        bitmap = Utils.scaleDown(bitmap);
        return bitmap;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSeekChanged(float value, float step) {
        if (llProcess.getVisibility() != View.VISIBLE && !start)
            llProcess.setVisibility(View.VISIBLE);

        start = false;
        tvProcess.setText(Float.toString(value / 10f));
        brightnessView.setBright(value / 10f);
    }

    @Override
    public void onSeekStopped(float value, float step) {
        if (llProcess.getVisibility() != View.GONE) llProcess.setVisibility(View.GONE);
    }


    private void back() {
        getActivity().onBackPressed();
    }

    private void saveImage() {
        Observable.just(inputUrl)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String url) throws Exception {
                        return saveBitmap(url);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        showLoading();
                    }

                    @Override
                    public void onNext(String url) {
                        if (onBrightnessListener != null)
                            onBrightnessListener.onBrightnessPhotoCompleted(url);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        back();
                    }
                });
    }

    private String saveBitmap(String url) {
        Bitmap bitmap = Utils.getBitmapSdcard(url);
        Bitmap bitmapBright = Utils.brightBitmap(bitmap, brightnessView.getBright());
        Utils.saveBitmap(url, bitmapBright);
        if (bitmap != null && !bitmap.isRecycled()) bitmap.recycle();
        return url;
    }

    private void hideLoading() {
        if (ivLoading != null)
            ivLoading.smoothToHide();
    }

    private void showLoading() {
        if (ivLoading != null)
            ivLoading.smoothToShow();
    }

    @Override
    public void onClick(View view) {
        if (ivLoading.isShown()) return;
        if (view.getId() == R.id.ivCancel) {
            back();
        } else if (view.getId() == R.id.ivCheck) {
            saveImage();
        }
    }
}
