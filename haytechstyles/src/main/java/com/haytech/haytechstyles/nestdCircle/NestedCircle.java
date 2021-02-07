package com.haytech.haytechstyles.nestdCircle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.haytech.haytechstyles.R;

public class NestedCircle extends View {

    private Paint paintInnerCircle;
    private Paint paintCenterCircle;
    private Paint paintOuterCircle;
    private int outerColor;
    private int centerColor;
    private int innerColor;
    private int radiusInnerCircles = 3;

    public NestedCircle(Context context) {
        super(context);
        init(context, null);
    }

    public NestedCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NestedCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.NestedCircle);
        outerColor = typedArray.getColor(R.styleable.NestedCircle_nc_background_outer, getResources().getColor(R.color.outerCircle));
        centerColor = typedArray.getColor(R.styleable.NestedCircle_nc_background_center, getResources().getColor(R.color.centerCircle));
        innerColor = typedArray.getColor(R.styleable.NestedCircle_nc_background_inner, getResources().getColor(R.color.innerCircle));
        radiusInnerCircles = typedArray.getInt(R.styleable.NestedCircle_nc_radius_inner_center_circle, 3);
        typedArray.recycle();
        paintOuterCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintOuterCircle.setColor(outerColor);

        paintCenterCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCenterCircle.setColor(centerColor);

        paintInnerCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintInnerCircle.setColor(innerColor);
    }

    public int getOuterColor() {
        return outerColor;
    }

    public void setOuterColor(int outerColor) {
        paintOuterCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintOuterCircle.setColor(outerColor);
    }

    public int getCenterColor() {
        return centerColor;
    }

    public void setCenterColor(int centerColor) {
        paintCenterCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCenterCircle.setColor(centerColor);
    }

    public int getInnerColor() {
        return innerColor;
    }

    public void setInnerColor(int innerColor) {

        paintInnerCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintInnerCircle.setColor(innerColor);
    }

    public void setColors(int innerColor ,int centerColor , int outerColor ){
        paintOuterCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintOuterCircle.setColor(outerColor);

        paintCenterCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCenterCircle.setColor(centerColor);

        paintInnerCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintInnerCircle.setColor(innerColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 70;
        int height =70;
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
        super.onDraw(canvas);
        canvas.drawCircle((float) getWidth()/2, (float) getHeight()/2, Math.min(getWidth() / 2, getHeight() / 2), paintOuterCircle);
        canvas.drawCircle((float)getWidth()/2, (float)getHeight()/2, Math.min(getWidth() / 2*2/radiusInnerCircles,
                getHeight() /  2*2/radiusInnerCircles), paintCenterCircle);
        canvas.drawCircle((float)getWidth()/2, (float)getHeight()/2, Math.min(getWidth() / 2 /radiusInnerCircles,
                getHeight() / 2 /radiusInnerCircles), paintInnerCircle);
    }
}
