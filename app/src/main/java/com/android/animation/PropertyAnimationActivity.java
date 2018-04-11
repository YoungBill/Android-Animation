package com.android.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PropertyAnimationActivity extends AppCompatActivity implements ValueAnimator.AnimatorUpdateListener {

    private static final String TAT = PropertyAnimationActivity.class.getSimpleName();

    private Person mPerson;
    private Button mPersonBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        mPerson = new Person();
        mPerson.setName("张三");
        mPersonBt = findViewById(R.id.personBt);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.personBt:
                ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mPerson, "age", 100);
                objectAnimator.addUpdateListener(this);
                objectAnimator.setDuration(5000);
                objectAnimator.start();
                break;
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int value = (int) animation.getAnimatedValue();
        mPersonBt.setText(mPerson.toString());

        Log.d(TAT, "当前值:" + value);
    }
}
