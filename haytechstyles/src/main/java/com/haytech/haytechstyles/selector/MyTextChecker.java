package com.haytech.haytechstyles.selector;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.haytech.haytechstyles.R;
import com.haytech.haytechstyles.utils.UIUtils;

public class MyTextChecker extends View implements View.OnClickListener {

    private static final int DEFAULT_DURATION = 200;
    private static final float DEFAULT_INNER_WIDTH = 1.95f;
    private static final float DEFAULT_OUTER_WIDTH = 1.1f;
    private CheckerListener listener;
    private Paint paintInnerColor;
    private Paint paintOuterColor;
    private boolean isChecked = false;
    private float morf = 0f;
    private int duration = DEFAULT_DURATION;
    private int innerColor;
    private int outerColor;
    private int typeCircle = 0;
    private float innerWidth = DEFAULT_INNER_WIDTH;
    private float outerWidth = DEFAULT_OUTER_WIDTH;
    private int valueFinal;

    public int getInnerColor() {
        return innerColor;
    }

    public void setInnerColor(int innerColor) {
        this.innerColor = innerColor;
    }

    public int getOuterColor() {
        return outerColor;
    }

    public void setOuterColor(int outerColor) {
        this.outerColor = outerColor;
    }

    public MyTextChecker(Context context) {
        super(context);
        init(context, null);
    }

    public MyTextChecker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyTextChecker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs );
    }

    private void init(Context context, @Nullable AttributeSet attrs ) {

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyTextChecker);
        innerColor = typedArray.getColor(R.styleable.MyTextChecker_mtc_checked_inner_color, getResources().getColor(R.color.innerColor));
        outerColor = typedArray.getColor(R.styleable.MyTextChecker_mtc_checked_outer_color, getResources().getColor(R.color.outerColor));
        duration = typedArray.getInt(R.styleable.MyTextChecker_mtc_duration, DEFAULT_DURATION);
        innerWidth = typedArray.getFloat(R.styleable.MyTextChecker_mtc_inner_width, DEFAULT_INNER_WIDTH);
        outerWidth = typedArray.getFloat(R.styleable.MyTextChecker_mtc_outer_width, DEFAULT_OUTER_WIDTH);
        typeCircle = typedArray.getInt(R.styleable.MyTextChecker_mtc_type, 0);
        typedArray.recycle();

        paintInnerColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintInnerColor.setColor(innerColor);

        paintOuterColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintOuterColor.setColor(outerColor);
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (int) UIUtils.dpToPx(getContext(), 25);
        int height = (int) UIUtils.dpToPx(getContext(), 25);
        setMeasuredDimension(manageDimension(widthMeasureSpec, width), manageDimension(heightMeasureSpec, height));
    }

    private int manageDimension(int measureSpace, int valueDefault) {
        valueFinal = 0;
        int valueSize = MeasureSpec.getSize(measureSpace);
        int valueMode = MeasureSpec.getMode(measureSpace);
        switch (valueMode) {
            case MeasureSpec.EXACTLY:
                valueFinal = valueSize;
                break;
            case MeasureSpec.AT_MOST:
                valueFinal = Math.min(valueSize, valueDefault);
                break;
            case MeasureSpec.UNSPECIFIED:
                valueFinal = valueDefault;
        }
        return valueFinal;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float w = getWidth();
        float h = getHeight();
        float rab = Math.min(w, h) / 2f;
        @SuppressLint("DrawAllocation")
        Path path = new Path();
        @SuppressLint("DrawAllocation")
        Path path2 = new Path();

        switch (typeCircle) {
            case 0:
                drawStrokeCircle(canvas, w, h, rab, path, path2);
                break;
            case 1:
                drawFillAndStrokeCircle(canvas, w, h, rab, path, path2);
                break;
        }

        super.onDraw(canvas);
    }

    private void drawStrokeCircle(Canvas canvas, float w, float h, float rab, Path path, Path path2) {
        path.addCircle(w / 2, h / 2, rab, Path.Direction.CCW);
        path.addCircle(w / 2, h / 2, (rab / outerWidth), Path.Direction.CW);
        canvas.drawPath(path, paintOuterColor);
        path2.addCircle(w / 2, h / 2, (rab / innerWidth) * morf, Path.Direction.CCW);
        canvas.drawPath(path2, paintInnerColor);
    }

    private void drawFillAndStrokeCircle(Canvas canvas, float w, float h, float rab, Path path, Path path2) {
        path.addCircle(w / 2, h / 2, rab, Path.Direction.CCW);
        path.addCircle(w / 2, h / 2, (rab / outerWidth), Path.Direction.CW);
        canvas.drawPath(path, paintOuterColor);
        path2.addCircle(w / 2, h / 2, rab, Path.Direction.CCW);
        if (morf != 0) {
            path2.addCircle(w / 2, h / 2, (rab / innerWidth) * morf, Path.Direction.CW);
        } else {
            path2.addCircle(w / 2, h / 2, (rab / outerWidth), Path.Direction.CW);
        }
        canvas.drawPath(path2, paintInnerColor);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public void onClick(View v) {
        isChecked = !isChecked;
        setChecked(isChecked);
        if (listener != null) {
            listener.check(isChecked);
            listener.getChecked();
        }
        Log.i("animator", "onAnimationUpdate: " + isChecked);
        animator();
    }

    public void animator() {
        float to = isChecked ? 1f : 0f;
        final ValueAnimator animator = ValueAnimator.ofFloat(morf, to);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                morf = (float) animator.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setListener(CheckerListener listener) {
        this.listener = listener;
    }

    public interface CheckerListener {
        void check(boolean b);
        void getChecked();
    }


}
