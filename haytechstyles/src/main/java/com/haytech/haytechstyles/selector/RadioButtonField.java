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
import com.haytech.haytechstyles.utils.UIUtilsHytechStyle;

public class RadioButtonField extends View implements View.OnClickListener {

    private static final int DEFAULT_DURATION = 200;
    private static final float DEFAULT_INNER_WIDTH = 1.8f;
    private static final float DEFAULT_OUTER_WIDTH = 1.1f;
    private static final float DEFAULT_INNER_FULL_WIDTH = 1;
    private CheckerListener listener;
    private Paint paintInnerColor;
    private Paint paintOuterColor;
    private boolean isChecked = false;
    private float morph = 0f;
    private int duration = DEFAULT_DURATION;
    private int innerColor;
    private int outerColor;
    private int typeCircle = 0;
    private float innerWidth = DEFAULT_INNER_WIDTH;
    private float innerWidthFull = DEFAULT_INNER_FULL_WIDTH;
    private float outerWidth = DEFAULT_OUTER_WIDTH;

    public int getInnerColor() {
        return innerColor;
    }

    public void setInnerColor(int innerColor) {
        this.innerColor = innerColor;
        paintInnerColor.setColor(getInnerColor());
        invalidate();
    }

    public int getOuterColor() {
        return outerColor;
    }

    public void setOuterColor(int outerColor) {
        this.outerColor = outerColor;
        paintOuterColor.setColor(getOuterColor());
        invalidate();
    }

    public Paint getPaintOuterColor() {
        return paintOuterColor;
    }

    public void setPaintOuterColor(Paint paintOuterColor) {
        this.paintOuterColor = paintOuterColor;
        invalidate();
    }

    public RadioButtonField(Context context) {
        super(context);
        init(context, null);
    }

    public RadioButtonField(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RadioButtonField(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs );
    }

    private void init(Context context, @Nullable AttributeSet attrs ) {

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RadioButtonField);
        innerColor = typedArray.getColor(R.styleable.RadioButtonField_mtc_checked_inner_color, getResources().getColor(R.color.innerColor));
        outerColor = typedArray.getColor(R.styleable.RadioButtonField_mtc_checked_outer_color, getResources().getColor(R.color.outerColor));
        duration = typedArray.getInt(R.styleable.RadioButtonField_mtc_duration, DEFAULT_DURATION);
        innerWidth = typedArray.getFloat(R.styleable.RadioButtonField_mtc_inner_width, DEFAULT_INNER_WIDTH);
        outerWidth = typedArray.getFloat(R.styleable.RadioButtonField_mtc_outer_width, DEFAULT_OUTER_WIDTH);
        typeCircle = typedArray.getInt(R.styleable.RadioButtonField_mtc_type, 0);
        typedArray.recycle();

        paintInnerColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintInnerColor.setColor(getInnerColor());

        paintOuterColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintOuterColor.setColor(getOuterColor());
        setOnClickListener(this);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (int) UIUtilsHytechStyle.getInstance().dpToPx(getContext(), 25);
        int height = (int) UIUtilsHytechStyle.getInstance().dpToPx(getContext(), 25);
        setMeasuredDimension(manageDimension(widthMeasureSpec, width), manageDimension(heightMeasureSpec, height));
    }

    private int manageDimension(int measureSpace, int valueDefault) {
        int valueFinal = 0;
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
        float width = getWidth();
        float height = getHeight();
        float radius = Math.min(width, height) / 2f;
        @SuppressLint("DrawAllocation")
        Path pathOuter = new Path();
        @SuppressLint("DrawAllocation")
        Path pathInner = new Path();

        switch (typeCircle) {
            case 0:
                drawStrokeCircle(canvas, width, height, radius, pathOuter, pathInner);
                break;
            case 1:
                drawFillAndStrokeCircle(canvas, width, height, radius, pathOuter, pathInner);
                break;
            case 2:
                drawFill(canvas, width, height, radius, pathOuter, pathInner);
                break;
        }

        super.onDraw(canvas);
    }

    private void drawStrokeCircle(Canvas canvas, float width, float height, float radius, Path pathOuter, Path pathInner) {
        pathOuter.addCircle(UIUtilsHytechStyle.getInstance().getWidthMethod(width , 2), UIUtilsHytechStyle.getInstance().getHeightMethod(height , 2), radius, Path.Direction.CCW);
        pathOuter.addCircle(UIUtilsHytechStyle.getInstance().getWidthMethod(width , 2f), UIUtilsHytechStyle.getInstance().getHeightMethod(height , 2f),
                UIUtilsHytechStyle.getInstance().getRadius(radius , outerWidth), Path.Direction.CW);
        canvas.drawPath(pathOuter, paintOuterColor);

        pathInner.addCircle(UIUtilsHytechStyle.getInstance().getWidthMethod(width , 2), UIUtilsHytechStyle.getInstance().getHeightMethod(height , 2),
                UIUtilsHytechStyle.getInstance().calculateMorph(UIUtilsHytechStyle.getInstance().getRadius(radius  ,  innerWidth) , morph), Path.Direction.CCW);
        canvas.drawPath(pathInner, paintInnerColor);
    }
    private void drawFillAndStrokeCircle(Canvas canvas, float width, float height, float radius, Path pathOuter, Path pathInner) {
        pathOuter.addCircle(UIUtilsHytechStyle.getInstance().getWidthMethod(width  , 2), UIUtilsHytechStyle.getInstance().getHeightMethod(height , 2), radius, Path.Direction.CCW);
        pathOuter.addCircle(UIUtilsHytechStyle.getInstance().getWidthMethod(width , 2), UIUtilsHytechStyle.getInstance().getHeightMethod(height , 2), UIUtilsHytechStyle.getInstance().getRadius(radius  , outerWidth), Path.Direction.CW);
        canvas.drawPath(pathOuter, paintOuterColor);

        pathInner.addCircle(UIUtilsHytechStyle.getInstance().getWidthMethod(width , 2), UIUtilsHytechStyle.getInstance().getHeightMethod(height , 2), radius, Path.Direction.CCW);
        if (morph != 0) {
            pathInner.addCircle(UIUtilsHytechStyle.getInstance().getWidthMethod(width , 2), UIUtilsHytechStyle.getInstance().getHeightMethod(height ,2),
                    UIUtilsHytechStyle.getInstance().calculateMorph((UIUtilsHytechStyle.getInstance().getRadius(radius , innerWidth)) , morph), Path.Direction.CW);
        } else {
            pathInner.addCircle(UIUtilsHytechStyle.getInstance().getWidthMethod(width , 2), UIUtilsHytechStyle.getInstance().getHeightMethod(height , 2),
                    UIUtilsHytechStyle.getInstance().getRadius(radius , outerWidth), Path.Direction.CW);
        }
        canvas.drawPath(pathInner, paintInnerColor);
    }

    private void drawFill(Canvas canvas, float width, float height, float radius, Path pathOuter, Path pathInner) {
        pathOuter.addCircle(UIUtilsHytechStyle.getInstance().getWidthMethod(width , 2), UIUtilsHytechStyle.getInstance().getHeightMethod(height , 2), radius, Path.Direction.CCW);
        pathOuter.addCircle(UIUtilsHytechStyle.getInstance().getWidthMethod(width , 2f), UIUtilsHytechStyle.getInstance().getHeightMethod(height , 2f),
                UIUtilsHytechStyle.getInstance().getRadius(radius , outerWidth), Path.Direction.CW);
        canvas.drawPath(pathOuter, paintOuterColor);

        pathInner.addCircle(UIUtilsHytechStyle.getInstance().getWidthMethod(width , 2), UIUtilsHytechStyle.getInstance().getHeightMethod(height , 2),
                UIUtilsHytechStyle.getInstance().calculateMorph(UIUtilsHytechStyle.getInstance().getRadius(radius  ,innerWidthFull  ) , morph), Path.Direction.CCW);
        canvas.drawPath(pathInner, paintInnerColor);
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
        final ValueAnimator animator = ValueAnimator.ofFloat(morph, to);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                morph = (float) animator.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    public float getInnerWidth() {
        return innerWidth;
    }

    public void setInnerWidth(float innerWidth) {
        this.innerWidth = innerWidth;
    }

    public float getInnerWidthFull() {
        return innerWidthFull;
    }

    public void setInnerWidthFull(float innerWidthFull) {
        this.innerWidthFull = innerWidthFull;
    }

    public int getTypeCircle() {
        return typeCircle;
    }

    public void setTypeCircle(int typeCircle) {
        this.typeCircle = typeCircle;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getOuterWidth() {
        return outerWidth;
    }

    public void setOuterWidth(float outerWidth) {
        this.outerWidth = outerWidth;
    }

    public void setListener(CheckerListener listener) {
        this.listener = listener;
    }

    public interface CheckerListener {
        void check(boolean b);
        void getChecked();
    }

}
