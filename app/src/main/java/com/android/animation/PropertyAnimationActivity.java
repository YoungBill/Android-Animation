package com.android.animation;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PropertyAnimationActivity extends AppCompatActivity {

    private static final String TAG = PropertyAnimationActivity.class.getSimpleName();

    private Person mPerson;
    private Button mPersonBt;
    private Button mArgbBt;
    private Button mEvaluatorBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        mPerson = new Person();
        mPerson.setName("张三");
        mPersonBt = findViewById(R.id.personBt);
        mArgbBt = findViewById(R.id.argbBt);
        mEvaluatorBt = findViewById(R.id.evaluatorBt);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.personBt:
                // 有get方法，就可以不用提供初始值，如果没有get方法，就必须提供初始值，不然从0开始
                ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mPerson, "age", 100);
                objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mPersonBt.setText(mPerson.toString());
                    }
                });
                objectAnimator.setDuration(5000);
                objectAnimator.start();
                break;
            case R.id.argbBt:
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
                final ArgbEvaluator evaluator = new ArgbEvaluator();
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int color = (int) evaluator.evaluate(animation.getAnimatedFraction(), Color.RED, Color.BLUE);
                        mArgbBt.setBackgroundColor(color);
                    }
                });
                valueAnimator.setDuration(3000);
                valueAnimator.start();
                break;
            case R.id.evaluatorBt:
                PointEvaluator.Point startPoint = new PointEvaluator.Point(mEvaluatorBt.getX(), mEvaluatorBt.getY());
                PointEvaluator.Point endPoint = new PointEvaluator.Point(mEvaluatorBt.getX() + 100, mEvaluatorBt.getY() + 100);
                ValueAnimator valueAnimator1 = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
                valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        PointEvaluator.Point point = (PointEvaluator.Point) animation.getAnimatedValue();
                        mEvaluatorBt.setX(point.getX());
                        mEvaluatorBt.setY(point.getY());
                    }
                });
                valueAnimator1.setDuration(3000);
                valueAnimator1.start();
                break;
        }
    }
}
