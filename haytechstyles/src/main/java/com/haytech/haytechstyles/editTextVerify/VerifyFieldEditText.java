package com.haytech.haytechstyles.editTextVerify;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.IntDef;

import com.haytech.haytechstyles.R;
import com.haytech.haytechstyles.utils.UIUtilsHytechStyle;
import com.haytech.haytechstyles.utils.Utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import io.reactivex.subjects.PublishSubject;


public class VerifyFieldEditText extends View {
    private String TAG = "VerifyCodeView";
    private int mWidth;

    private int mHeight;
    //the code builder
    private StringBuilder codeBuilder;
    //the paint to draw solid lines
    private Paint shapePaint;
    //the paint to draw text
    private Paint textPaint;
    //text font
    private Typeface typeface = Typeface.DEFAULT;
    private OnTextChangListener listener;

    private int textColor = Color.CYAN;
    private int shapeColor = Color.BLACK;
    //how many words to show
    private int textSize = 4;
    //transparent line between solid lines
    private int blankLine;
    private int solidLine;
    //solid line's width
    private int lineWidth = 5;

    private PointF[] solidPoints;


    public static final int INPUT_CIRCLE = 0;

    public static final int INPUT_NO_LINE = 1;
    private float morf = 0;
    private boolean isChecked = false;
    private float circleRadius = 8;
    private int textDrawSize = 20;

    @IntDef({INPUT_CIRCLE, INPUT_NO_LINE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LineStyle {
    }

    @LineStyle
    private int shapeStyle = INPUT_CIRCLE;

    private int mLinePosY;

    public VerifyFieldEditText(Context context) {
        super(context);
        init(context, null);
    }

    public VerifyFieldEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public VerifyFieldEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VerifyFieldEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VerifyFieldEditText);
            textColor = typedArray.getColor(R.styleable.VerifyFieldEditText_vfe_text_color, textColor);
            shapeColor = typedArray.getColor(R.styleable.VerifyFieldEditText_vfe_circle_color, shapeColor);
            textDrawSize = typedArray.getInt(R.styleable.VerifyFieldEditText_vfe_text_draw_size, 20);
            circleRadius = typedArray.getInteger(R.styleable.VerifyFieldEditText_vfe_circle_radius, 8);
            textSize = typedArray.getInt(R.styleable.VerifyFieldEditText_vfe_text_count, textSize);
            if (textSize < 2) throw new IllegalArgumentException("Text size must more than 1!");
            lineWidth = typedArray.getDimensionPixelSize(R.styleable.VerifyFieldEditText_vfe_line_width, lineWidth);
            String font = typedArray.getString(R.styleable.VerifyFieldEditText_vfe_font);
            if (font != null)
                typeface = Typeface.createFromAsset(context.getAssets(), font);
            switch (typedArray.getInt(R.styleable.VerifyFieldEditText_vfe_line_style, INPUT_CIRCLE)) {
                case INPUT_CIRCLE:
                    shapeStyle = INPUT_CIRCLE;
                    break;

                case INPUT_NO_LINE:
                    shapeStyle = INPUT_NO_LINE;
                    break;
            }
            typedArray.recycle();
        }
        if (codeBuilder == null)
            codeBuilder = new StringBuilder();

        shapePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shapePaint.setColor(shapeColor);
        shapePaint.setAntiAlias(true);
        shapePaint.setStrokeWidth(lineWidth);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(typeface);

        setFocusableInTouchMode(true); // allows the keyboard to pop up on
        // touch down
    }

    public Paint getTextPaint() {
        return textPaint;
    }

    public void setTextPaint(int textPaint) {
         this.textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
         this.textPaint.setColor(textPaint);
         this.textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
         this.textPaint.setTextAlign(Paint.Align.CENTER);
         this.textPaint.setTypeface(typeface);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        requestFocus();//must have focus to show the keyboard
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // touch down
//            Log.d(TAG, "ACTION_DOWN");
            // show the keyboard so we can enter text
            Utils.showKeyboard(getContext(), VerifyFieldEditText.this);
        }
        return true;
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        //define keyboard to number keyboard
        BaseInputConnection fic = new BaseInputConnection(this, false) {
            @Override
            public boolean deleteSurroundingText(int beforeLength, int afterLength) {
                return sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL)) && sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
            }
        };
        outAttrs.actionLabel = null;
        outAttrs.inputType = InputType.TYPE_CLASS_NUMBER;
        outAttrs.imeOptions = EditorInfo.IME_ACTION_DONE;
        return fic;

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Log.i(TAG," keycode == "+keyCode);
        if (codeBuilder == null) codeBuilder = new StringBuilder();
        //67 is backspace,7-16 are 0-9
        if (keyCode == 67 && codeBuilder.length() > 0) {
            codeBuilder.deleteCharAt(codeBuilder.length() - 1);
            if (listener != null) {
                Utils.hideKeyboard((Activity) getContext());
            }
            invalidate();

        } else if (keyCode >= 7 && keyCode <= 16 && codeBuilder.length() < textSize) {
            codeBuilder.append(keyCode - 7);
            if (listener != null) {
                listener.afterTextChanged(codeBuilder.toString());
            }
            invalidate();
        }
        //hide soft keyboard
        if (codeBuilder.length() >= textSize || keyCode == 66) {
            Utils.hideKeyboard((Activity) (getContext()));
           verifyCode.onNext(codeBuilder.toString());
        }
        return super.onKeyDown(keyCode, event);
    }

    public void animator() {
        float to = isChecked ? 0f : 1f;
        final ValueAnimator animator = ValueAnimator.ofFloat(morf, to);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                morf = (float) animator.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        Log.i(TAG,"onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = UIUtilsHytechStyle.getInstance().getInstance().getWidth(getContext()) * 2 / 3;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            mHeight = UIUtilsHytechStyle.getInstance().getWidth(getContext()) / 4;
        }

        //calculate line's length
        blankLine = mWidth / (4 * textSize - 1);    //short one
        solidLine = mWidth / (4 * textSize - 1) * 3;  //long one

        if (textPaint != null) {
            textPaint.setTextSize(solidLine + textDrawSize);
        }
        calculateStartAndEndPoint(textSize);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawShape(canvas);
    }

    private void drawShape(Canvas canvas) {
        if (codeBuilder == null) return;
        int inputLength = codeBuilder.length();
        Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        //text's vertical center is view's center
        int baseShape = (int) UIUtilsHytechStyle.getInstance().getHeightMethod(mHeight, 2) + (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        switch (shapeStyle) {
            case INPUT_CIRCLE:
                mLinePosY = (int) UIUtilsHytechStyle.getInstance().getHeightMethod(mHeight, 2);
                for (int i = 0; i < textSize; i++) {
                    if (inputLength > i) {
                        canvas.drawText(codeBuilder.toString(), i, i + 1, solidPoints[i].y - (float) solidLine / 2, baseShape, textPaint);
                    } else {
                        canvas.drawCircle((UIUtilsHytechStyle.getInstance().getWidthMethod(getWidth(), textSize) - (UIUtilsHytechStyle.getInstance().getWidthMethod(getWidth(), (textSize + 4)))) + solidPoints[i].x,
                                UIUtilsHytechStyle.getInstance().getHeightMethod(getHeight(), 2), UIUtilsHytechStyle.getInstance().getHeightMethod(getHeight(), circleRadius), shapePaint);
                    }
                }

                break;
            case INPUT_NO_LINE:
                mLinePosY = baseShape + lineWidth;
                for (int i = 0; i < textSize; i++) {
                    if (inputLength > i) {
                        canvas.drawText(codeBuilder.toString(), i, i + 1, solidPoints[i].y - (float) solidLine / 2, baseShape, textPaint);
                    } else {
                        canvas.drawLine(solidPoints[i].x, mLinePosY, (float) solidPoints[i].y, mLinePosY, shapePaint);
                    }
                }
                break;

        }
    }

    //get verify code string
    public String getText() {
        return codeBuilder != null ? codeBuilder.toString() : "";
    }


    //set verify code (must less than 4 letters)
    public void setText(String code) {
        if (code == null)
            throw new NullPointerException("Code must not null!");
        if (code.length() > textSize) {
            throw new IllegalArgumentException(String.format("Code must less than %d letters!", textSize));
        }
        codeBuilder = new StringBuilder();
        codeBuilder.append(code);
        invalidate();
    }

    public interface OnTextChangListener {
        void afterTextChanged(String text);
    }


    // calculate every points
    private void calculateStartAndEndPoint(int textSize) {
        solidPoints = new PointF[textSize];
        for (int i = 1; i <= textSize; i++) {
            solidPoints[i - 1] = new PointF((i - 1) * blankLine + (i - 1) * solidLine, (i - 1) * blankLine + i * solidLine);
        }
    }

    public void setListener(OnTextChangListener listener) {
        this.listener = listener;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(@ColorRes int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    // set the code's length
    public void setTextSize(int textSize) {
        if (textSize < 2) throw new IllegalArgumentException("Text size must more than 1!");
        this.textSize = textSize;
    }


    public void setFont(Typeface typeface) {
        this.typeface = typeface;
    }


    public void setFont(String path) {
        typeface = Typeface.createFromAsset(getContext().getAssets(), path);
    }

    public void setShapeStyle(@LineStyle int shapeStyle) {
        this.shapeStyle = shapeStyle;
    }

    private PublishSubject<String> verifyCode  = PublishSubject.create();

    public PublishSubject<String> getVerifyCode() {
        return verifyCode;
    }
}
