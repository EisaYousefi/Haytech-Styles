package com.haytech.haytechstyles.editTextVerify;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.haytech.haytechstyles.R;
import com.haytech.haytechstyles.utils.SizeConverter;


public class EditTextVerify extends View implements View.OnClickListener {

    private static final long DEFAULT_DURATION = 300;
    private CheckerListener listener;
    private Paint paintEmptyTextColor;
    private Paint paintFullTextColor;
    private boolean isChecked = false;
    private float morf = 1f;
    private String valueNumber = "";
    private long duration = DEFAULT_DURATION;
    private int emptyTextColor;
    private int fullTextColor;
    private String textNumber = "0";
    private float testSize ;

    public int getEmptyTextColor() {
        return emptyTextColor;
    }

    public void setEmptyTextColor(int emptyTextColor) {
        this.emptyTextColor = emptyTextColor;
    }

    public int getFullTextColor() {
        return fullTextColor;
    }

    public void setFullTextColor(int fullTextColor) {
        this.fullTextColor = fullTextColor;
    }

    public EditTextVerify(Context context) {
        super(context);
        init(context, null);
    }

    public EditTextVerify(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EditTextVerify(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.EditTextVerify);
        emptyTextColor = typedArray.getColor(R.styleable.EditTextVerify_etv_text_full_color, getResources().getColor(R.color.text_empty_color));
        fullTextColor = typedArray.getColor(R.styleable.EditTextVerify_etv_text_full_color,  getResources().getColor(R.color.text_full_color));
        testSize = typedArray.getFloat(R.styleable.EditTextVerify_etv_text_size , 30f);
        typedArray.recycle();

        paintEmptyTextColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintEmptyTextColor.setColor(emptyTextColor);

        paintFullTextColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintFullTextColor.setColor(fullTextColor);
        setOnClickListener(this);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (int) SizeConverter.dpToPx(getContext(), 25);
        int height = (int) SizeConverter.dpToPx(getContext(), 25);
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

        float w = getWidth();
        float h = getHeight();
        float radius = Math.min(w, h) / 5f;
            if (!isChecked) {
                //یک دایره به جای عدد رسم می کند
                Path path = new Path();
                path.addCircle(w / 2, h / 2, radius * morf, Path.Direction.CCW);
                canvas.drawPath(path, paintEmptyTextColor);

                drawCenter(canvas, paintFullTextColor, "");

            } else {

                //داریه را محو میکند و بجایش عدد تایپ میکند
                Path path = new Path();
                path.addCircle(w / 2, h / 2, radius * morf, Path.Direction.CCW);
                canvas.drawPath(path, paintEmptyTextColor);
                if (morf == 0) {
                    drawCenter(canvas, paintFullTextColor, getValueNumber());
                }
            }
            super.onDraw(canvas);
        }


    Rect r;
    String s = "";

    //برای نوشتن عدد در وسط کاستوم ویو
    private void drawCenter(Canvas canvas, Paint paint, String text) {
            if (r == null)
                r = new Rect();
            canvas.getClipBounds(r);
            int cHeight = r.height();
            int cWidth = r.width();
            paint.setTextAlign(Paint.Align.LEFT);
            paint.getTextBounds(text, 0, text.length(), r);
            float x = cWidth / 2f - r.width() / 2f - r.left;
            float y = cHeight / 2f + r.height() / 2f - r.bottom;
            paint.setTextSize(SizeConverter.spToPx(getContext(), testSize));
            paint.setColor(fullTextColor);
            canvas.drawText(text, x, y, paint);
            if (!text.equals("")) {
                setTextNumber(text);
            }

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
        animator();
    }

    //جهت ترسیم کردن دایره به صورت مورف انیمیشن
    public void animator() {
        float to = isChecked ? 0f : 1f;
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getValueNumber() {
        return valueNumber;
    }

    public void setValueNumber(String valueNumber) {
        this.valueNumber = valueNumber;
    }

    public String getTextNumber() {
        return textNumber;
    }

    public void setTextNumber(String textNumber) {
        this.textNumber = textNumber;
    }

    public void setListener(CheckerListener listener) {
        this.listener = listener;
    }

    public interface CheckerListener {
        void check(boolean b);

        boolean getChecked();
    }


}
