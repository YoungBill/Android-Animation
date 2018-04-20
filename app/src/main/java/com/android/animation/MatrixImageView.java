package com.android.animation;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by baina on 18-4-20.
 */

public class MatrixImageView extends android.support.v7.widget.AppCompatImageView {

    public MatrixImageView(Context context) {
        super(context);
        init();
    }

    public MatrixImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MatrixImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Matrix matrix = getImageMatrix();
        RectF drawableRect = new RectF(0, 0, 1080, 1920);
        RectF viewRect = new RectF(0, 0, 720 * 1.3f, 1280 * 1.3f);
        matrix.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.CENTER);
        setImageMatrix(matrix);
    }
}
