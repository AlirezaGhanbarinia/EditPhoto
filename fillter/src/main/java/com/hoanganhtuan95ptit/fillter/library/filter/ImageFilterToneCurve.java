package com.hoanganhtuan95ptit.fillter.library.filter;

import android.content.Context;
import android.opengl.GLES10;

import com.hoanganhtuan95ptit.fillter.R;
import com.hoanganhtuan95ptit.fillter.library.gles.GlUtil;

import java.io.InputStream;

public class ImageFilterToneCurve extends CameraFilterToneCurve {

    public ImageFilterToneCurve(Context context, InputStream inputStream) {
        super(context, inputStream);
    }

    @Override public int getTextureTarget() {
        return GLES10.GL_TEXTURE_2D;
    }

    @Override protected int createProgram(Context applicationContext) {
        return GlUtil.createProgram(applicationContext, R.raw.vertex_shader,
                R.raw.fragment_shader_2d_tone_curve);
    }
}
