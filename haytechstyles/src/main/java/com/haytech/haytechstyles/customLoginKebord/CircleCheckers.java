package com.haytech.haytechstyles.customLoginKebord;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;

import com.haytech.haytechstyles.R;

public class CircleCheckers extends View implements View.OnClickListener {

    private Paint paintInnerColor;
    private Paint paintouterColor;
    private boolean isChecked = false;
    private float mRopf = 0f;
    private int innerColor;
    private int outerColor;

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

    public CircleCheckers(Context context) {
        super(context);
        init(context, null);
    }

    public CircleCheckers(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleCheckers(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CircleCheckers);
        innerColor = typedArray.getColor(R.styleable.CircleCheckers_morph_checked_inner_color, getResources().getColor(R.color.innerColor));
        outerColor = typedArray.getColor(R.styleable.CircleCheckers_morph_checked_outer_color,getResources().getColor(R.color.innerColor));

        paintInnerColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintInnerColor.setColor(innerColor);

        paintouterColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintouterColor.setColor(outerColor);
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float w = getWidth();
        float h = getHeight();
        float rab = Math.min(w, h) / 2f;

        Path path = new Path();
        path.addCircle(w / 2, h / 2, rab, Path.Direction.CCW);
        //path.addCircle(w/2,h/2,(rab/1.2f)*mRopf, Path.Direction.CW);
        canvas.drawPath(path, paintInnerColor);

        Path path2 = new Path();
        path2.addCircle(w / 2, h / 2, (rab / 1.95f) * mRopf, Path.Direction.CCW);
        canvas.drawPath(path2, paintouterColor);
        super.onDraw(canvas);
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
        Log.i("animator", "onAnimationUpdate: " + isChecked);
        paintouterColor.setColor(getInnerColor());
        paintInnerColor.setColor(getOuterColor());
        animator();
    }

    private void animator() {

        float to = isChecked ? 1f : 0f;
        final ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRopf = (float) animator.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }


}
