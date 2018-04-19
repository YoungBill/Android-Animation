package com.android.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Onclick(View view) {
        switch (view.getId()) {
            case R.id.frameAnimationBt:
                startActivity(new Intent(MainActivity.this, FrameAnimationActivity.class));
                break;
            case R.id.tweenAnimationBt:
                startActivity(new Intent(MainActivity.this, TweenAnimationActivity.class));
                break;
            case R.id.propertyAnimationBt:
                startActivity(new Intent(MainActivity.this, PropertyAnimationActivity.class));
                break;
        }
//        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
    }
}
