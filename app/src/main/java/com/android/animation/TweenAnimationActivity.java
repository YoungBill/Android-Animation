package com.android.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class TweenAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);

        // 补间动画是可以用代码定义的，如AlphaAnimation,RotateAnimation等
        ImageView alphaAnimationIv = findViewById(R.id.alphaAnimationIv);
        Animation alphaAnimation = AnimationUtils.loadAnimation(TweenAnimationActivity.this, R.anim.anim_alpha);
        alphaAnimationIv.startAnimation(alphaAnimation);

        ImageView rotateAnimationIv = findViewById(R.id.rotateAnimationIv);
        Animation rotateAnimation = AnimationUtils.loadAnimation(TweenAnimationActivity.this, R.anim.anim_rotate);
        rotateAnimationIv.startAnimation(rotateAnimation);

        ImageView scaleAnimationIv = findViewById(R.id.scaleAnimationIv);
        Animation scaleAnimation = AnimationUtils.loadAnimation(TweenAnimationActivity.this, R.anim.anim_scale);
        scaleAnimationIv.startAnimation(scaleAnimation);

        ImageView translateAnimationIv = findViewById(R.id.translateAnimationIv);
        Animation translateAnimation = AnimationUtils.loadAnimation(TweenAnimationActivity.this, R.anim.anim_translate);
        translateAnimationIv.startAnimation(translateAnimation);

    }
}
