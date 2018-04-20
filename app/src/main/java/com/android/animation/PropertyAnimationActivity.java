package com.android.animation;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class PropertyAnimationActivity extends AppCompatActivity {

    private static final String TAG = PropertyAnimationActivity.class.getSimpleName();
    private static final String IMAGE_URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524202080430&di=c595a9d9789e4fbc7d95934a9f699dd6&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2Fattachments2%2F201408%2F14%2F141434jnfneinjeneqhj9t.jpg";
    private static final float SCALE_INITIAL = 1.3f;

    private Person mPerson;
    private Button mPersonBt;
    private Button mArgbBt;
    private Button mEvaluatorBt;
    private ImageView mImage;

    private int mTargetImageWidth;
    private int mTargetImageHeight;
    private int mScreenWidth;
    private int mScreenHeight;
    private Transformation mTransformation;

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
        mTransformation = new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {
                mTargetImageWidth = source.getWidth();
                mTargetImageHeight = source.getHeight();
                Log.d(TAG, "targetWidth=" + mTargetImageWidth + ",source.targetHeight=" + mTargetImageHeight);

                Matrix matrix = mImage.getImageMatrix();
                RectF drawableRect = new RectF(0, 0, mTargetImageWidth, mTargetImageHeight);
                RectF viewRect = new RectF(0, 0, mScreenWidth * SCALE_INITIAL, mScreenHeight * SCALE_INITIAL);
                matrix.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.CENTER);
                mImage.setImageMatrix(matrix);

                setScaleAnimation();
                return source;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };
//        Picasso.with(PropertyAnimationActivity.this)
//                .load(IMAGE_URL)
//                .transform(mTransformation)
//                .into(mImage);
        setScaleAnimation();
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

    private class MyScaleAnimatorListener implements ValueAnimator.AnimatorUpdateListener {

        private Matrix mPrimaryMatrix;

        public MyScaleAnimatorListener(Matrix matrix) {
            mPrimaryMatrix = matrix;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
//            float scale = (Float) animation.getAnimatedValue();
//            Matrix matrix = new Matrix(mPrimaryMatrix);
//            RectF drawableRect = new RectF(0, 0, mTargetImageWidth, mTargetImageHeight);
//            RectF viewRect = new RectF(0, 0, mScreenWidth * scale, mScreenHeight * scale);
//            matrix.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.START);
//            mImage.setImageMatrix(matrix);

            float scale = (Float) animation.getAnimatedValue();
            Matrix matrix = new Matrix(mPrimaryMatrix);
            RectF drawableRect = new RectF(0, 0, 1080, 1920);
            RectF viewRect = new RectF(0, 0, 720 * scale, 1280 * scale);
            matrix.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.CENTER);
            mImage.setImageMatrix(matrix);
        }
    }

    public void setScaleAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(SCALE_INITIAL, 1.0f);
        animator.addUpdateListener(new MyScaleAnimatorListener(mImage.getImageMatrix()));
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setStartDelay(500);
        animator.start();
    }

}
