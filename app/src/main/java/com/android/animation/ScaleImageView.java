package com.android.animation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by chentao on 2018/4/21.
 */

public class ScaleImageView extends android.support.v7.widget.AppCompatImageView {

    private static final float SCALE_INITIAL = 1.3f;

    private Context mContext;
    private int mWidth;
    private int mHeight;

    public ScaleImageView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public ScaleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }


    public ScaleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mWidth, mHeight);
    }

    private void init() {
        mWidth = (int) (Utils.getScreenWidth(mContext) * SCALE_INITIAL);
        mHeight = (int) (Utils.getScreenHeight(mContext) * SCALE_INITIAL);
    }

    public void setLayout(int width, int height) {
        mWidth = width;
        mHeight = height;
    }
}
