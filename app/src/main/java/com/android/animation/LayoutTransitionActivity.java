package com.android.animation;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class LayoutTransitionActivity extends AppCompatActivity {

    private LinearLayout mContainerLl;
    private int mNum;
    private LayoutTransition mLayoutTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layouttransition);

        mContainerLl = findViewById(R.id.containerLl);
        mLayoutTransition = new LayoutTransition();
        setupCustomAnimations();
        mContainerLl.setLayoutTransition(mLayoutTransition);
    }

    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.addBt:
                addButtonView();
                break;
            case R.id.removeBt:
                removeButtonView();
                break;
        }
    }

    private void addButtonView() {
        mNum++;
        Button button = new Button(this);
        button.setText("button" + mNum);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mContainerLl.addView(button, params);
    }

    private void removeButtonView() {
        if (mNum > 0) {
            mContainerLl.removeViewAt(0);
            mNum--;
        }
    }

    // 生成自定义动画
    private void setupCustomAnimations() {
        //设置每个动画持续的时间
        mLayoutTransition.setStagger(LayoutTransition.APPEARING, mLayoutTransition.getDuration(LayoutTransition.APPEARING));
        mLayoutTransition.setStagger(LayoutTransition.DISAPPEARING, mLayoutTransition.getDuration(LayoutTransition.DISAPPEARING));

        // 动画：APPEARING
        // Adding
        PropertyValuesHolder appearingAlpha = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);
        PropertyValuesHolder appearingTranslationX = PropertyValuesHolder.ofFloat("translationX", Utils.getScreenWidth(this), 0f);
        ObjectAnimator mAnimatorAppearing = ObjectAnimator.ofPropertyValuesHolder(this, appearingAlpha, appearingTranslationX);
        //为LayoutTransition设置动画及动画类型
        mLayoutTransition.setAnimator(LayoutTransition.APPEARING, mAnimatorAppearing);

        // 动画：DISAPPEARING
        // Removing
        PropertyValuesHolder disappearingAlpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
        PropertyValuesHolder disappearingTranslationX = PropertyValuesHolder.ofFloat("translationX", 0f, Utils.getScreenWidth(this));
        ObjectAnimator mAnimatorDisappearing = ObjectAnimator.ofPropertyValuesHolder(this, disappearingAlpha, disappearingTranslationX);
        //为LayoutTransition设置动画及动画类型
        mLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, mAnimatorDisappearing);
    }
}
