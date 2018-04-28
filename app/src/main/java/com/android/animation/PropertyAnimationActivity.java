package com.android.animation;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class PropertyAnimationActivity extends AppCompatActivity {

    private static final String TAG = PropertyAnimationActivity.class.getSimpleName();
    private static final String IMAGE_URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524202080430&di=c595a9d9789e4fbc7d95934a9f699dd6&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201408%2F14%2F141434jnfneinjeneqhj9t.jpg";
    private static final float SCALE_INITIAL = 1.3f;

    private Person mPerson;
    private Button mPersonBt;
    private Button mArgbBt;
    private Button mEvaluatorBt;
    private ImageView mImage;

    private int mScreenWidth;
    private int mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        mPerson = new Person();
        mPerson.setName("张三");
        mPersonBt = findViewById(R.id.personBt);
        mArgbBt = findViewById(R.id.argbBt);
        mEvaluatorBt = findViewById(R.id.evaluatorBt);
        mImage = findViewById(R.id.image);
        mScreenWidth = Utils.getScreenWidth(PropertyAnimationActivity.this);
        mScreenHeight = Utils.getScreenHeight(PropertyAnimationActivity.this);
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                //加载成功后会得到一个bitmap,可以自定义操作
                mImage.setImageBitmap(bitmap);
                setScaleWHAnimation();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                // 加载失败进行相应处理
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        mImage.setTag(target);
        Picasso.with(PropertyAnimationActivity.this)
                .load(IMAGE_URL)
                .placeholder(R.drawable.bg_homepage)
                .into(target);
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

    public void setScaleWHAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(SCALE_INITIAL, 1.0f);
        animator.addUpdateListener(new MyScaleLayoutAnimatorListener((ScaleImageView) mImage));
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setStartDelay(500);
        animator.start();
    }

    private class MyScaleLayoutAnimatorListener implements ValueAnimator.AnimatorUpdateListener {

        private ScaleImageView mImageView;

        public MyScaleLayoutAnimatorListener(ScaleImageView imageView) {
            mImageView = imageView;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            float scale = (Float) animation.getAnimatedValue();
            mImageView.setLayout((int) (mScreenWidth * scale), (int) (mScreenHeight * scale));
            mImageView.requestLayout();
        }
    }
}
