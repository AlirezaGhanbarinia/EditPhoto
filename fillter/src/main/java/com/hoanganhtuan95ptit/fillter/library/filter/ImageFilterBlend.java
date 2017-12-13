package com.hoanganhtuan95ptit.fillter.library.filter;

import android.content.Context;
import android.opengl.GLES10;
import android.support.annotation.DrawableRes;

import com.hoanganhtuan95ptit.fillter.R;
import com.hoanganhtuan95ptit.fillter.library.gles.GlUtil;

public class ImageFilterBlend extends CameraFilterBlend {

    public ImageFilterBlend(Context context, @DrawableRes int drawableId) {
        super(context, drawableId);
    }

    @Override public int getTextureTarget() {
        return GLES10.GL_TEXTURE_2D;
    }

    @Override protected int createProgram(Context applicationContext) {
        return GlUtil.createProgram(applicationContext, R.raw.vertex_shader_2d_two_input,
                R.raw.fragment_shader_2d_blend);
    }
}