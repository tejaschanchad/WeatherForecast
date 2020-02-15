package com.tejas.weatherforecast.view.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

public class CustomProgressBarView extends View {

    private static final int ARC_START_ANGLE = 275;

    private static final float THICKNESS_SCALE = 0.07f;

    private Bitmap mBitmap;
    private Canvas mCanvas;

    private RectF mCircleOuterBounds;
    private RectF mCircleInnerBounds;

    private Paint mCirclePaint;
    private Paint mEraserPaint;

    private float mCircleSweepAngle;
    private float mEraseCircleSweepAngle;
    private ValueAnimator mTimerAnimator;

    public CustomProgressBarView(Context context) {
        this(context, null);
    }

    public CustomProgressBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        int circleColor = Color.BLACK;

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(circleColor);

        mEraserPaint = new Paint();
        mEraserPaint.setAntiAlias(true);
        mEraserPaint.setColor(Color.TRANSPARENT);
        mEraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw || h != oldh) {
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mBitmap.eraseColor(Color.TRANSPARENT);
            mCanvas = new Canvas(mBitmap);
        }

        super.onSizeChanged(w, h, oldw, oldh);
        updateBounds();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas.drawColor(0, PorterDuff.Mode.CLEAR);

        if (mCircleSweepAngle > 0f) {
            mCanvas.drawArc(mCircleOuterBounds, ARC_START_ANGLE, mCircleSweepAngle, true, mCirclePaint);
            mCanvas.drawOval(mCircleInnerBounds, mEraserPaint);
            mCanvas.drawArc(mCircleOuterBounds, 265, mEraseCircleSweepAngle, true, mEraserPaint);
        }
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    public void start(long duration) {
        stop();
        mTimerAnimator = ValueAnimator.ofFloat(0f, 1f);
        mTimerAnimator.setDuration(duration);
        mTimerAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mTimerAnimator.setRepeatCount(Animation.INFINITE);
        mTimerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                drawProgress((float) animation.getAnimatedValue());
            }
        });
        mTimerAnimator.start();
    }

    public void stop() {
        if (mTimerAnimator != null && mTimerAnimator.isRunning()) {
            mTimerAnimator.cancel();
            mTimerAnimator = null;

            drawProgress(0f);
        }
    }

    private void drawProgress(float progress) {
        mCircleSweepAngle  =(360 * progress);
        if(progress < 0.5){
            mEraseCircleSweepAngle = (float) (325 * Math.sin(progress));
        }else if(progress > 0.5 && progress <0.58){
            mEraseCircleSweepAngle = (float) (345 * Math.sin(progress));
        }else if(progress > 0.6 && progress <0.68){
            mEraseCircleSweepAngle = (float) (350 * Math.sin(progress));
        } else {
            mEraseCircleSweepAngle = (float) (305 * Math.asin(progress));
        }
        if(mEraseCircleSweepAngle >280){
            mEraseCircleSweepAngle = (float) (300 * Math.asin(progress));
        }
        if(mEraseCircleSweepAngle >300){
            mEraseCircleSweepAngle = (float) (290 * Math.asin(progress));
        }
        if(mEraseCircleSweepAngle >360){
            mEraseCircleSweepAngle = (float) (395 * Math.asin(progress));
        }
        invalidate();
    }

    private void updateBounds() {
        final float thickness = getWidth() * THICKNESS_SCALE;

        mCircleOuterBounds = new RectF(0, 0, getWidth(), getHeight());
        mCircleInnerBounds = new RectF(
                mCircleOuterBounds.left + thickness,
                mCircleOuterBounds.top + thickness,
                mCircleOuterBounds.right - thickness,
                mCircleOuterBounds.bottom - thickness);

        invalidate();
    }
}
