package com.android.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class FrameAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        ImageView image = findViewById(R.id.frameIv);
        AnimationDrawable drawable = (AnimationDrawable) image.getBackground();
        drawable.start();
    }
}
