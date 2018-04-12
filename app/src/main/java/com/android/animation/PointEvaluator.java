package com.android.animation;

import android.animation.TypeEvaluator;

public class PointEvaluator implements TypeEvaluator<PointEvaluator.Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        float resultX = startValue.getX() + fraction * (endValue.getX() - startValue.getX());
        float resultY = startValue.getY() + fraction * (endValue.getY() - startValue.getX());
        return new Point(resultX, resultY);
    }

    public static class Point {
        private float x;
        private float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}