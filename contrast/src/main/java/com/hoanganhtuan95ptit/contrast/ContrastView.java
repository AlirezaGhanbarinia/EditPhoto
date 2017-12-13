package com.hoanganhtuan95ptit.contrast;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.annotation.FloatRange;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Hoang Anh Tuan on 12/12/2017.
 */

class ContrastView extends AppCompatImageView {

    private float contrast;
    private PublishSubject<Float> subject;

    public ContrastView(Context context) {
        super(context);
        initView();
    }

    public ContrastView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ContrastView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        subject = PublishSubject.create();
        subject.debounce(0, TimeUnit.MILLISECONDS)
//                .filter(new Predicate<Float>() {
//                    @Override
//                    public boolean test(Float contrast) throws Exception {
//                        return true;
//                    }
//                })
                .distinctUntilChanged()
                .switchMap(new Function<Float, ObservableSource<ColorMatrixColorFilter>>() {
                    @Override
                    public ObservableSource<ColorMatrixColorFilter> apply(Float value) throws Exception {
                        return postContrast(value);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ColorMatrixColorFilter>() {
                    @Override
                    public void accept(ColorMatrixColorFilter colorMatrixColorFilter) throws Exception {
                        setColorFilter(colorMatrixColorFilter);
                    }
                });
    }

    public float getContrast() {
        return contrast;
    }

    public void setContrast(@FloatRange(from = -180.0f, to = 180.0f) float contrast) {
        this.contrast = contrast / 180.0f + 1.0f;
        subject.onNext(this.contrast);
    }

    private Observable<ColorMatrixColorFilter> postContrast(float value) {
        return Observable.just(contrast(value));
    }

    private ColorMatrixColorFilter contrast(float value) {
        float scale = value;
        float[] array = new float[]{
                scale, 0, 0, 0, 0,
                0, scale, 0, 0, 0,
                0, 0, scale, 0, 0,
                0, 0, 0, 1, 0};
        ColorMatrix matrix = new ColorMatrix(array);
        return new ColorMatrixColorFilter(matrix);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
