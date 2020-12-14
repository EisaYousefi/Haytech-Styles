package com.haytech.haytechstyles.seekbar;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.haytech.haytechstyles.R;
import com.haytech.haytechstyles.utils.PaintText;
import com.haytech.haytechstyles.utils.SizeConverter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;


public class SeekBar extends View {

    private PublishSubject<Integer> mCallbackRx;
    private int thumbRadius = 0;
    private int shadowSize = 0;
    private long animatorDuration = 450L;
    private int colorText;
    private int shadowColor;
    private int colorTrack;
    private int thumbColor;
    private int marginBetweenTextAndSeekBar = 0;
    private int numberSections = 0;
    //track
    private int heightTrack = 0;
    //degree
    private int widthDegree = 0;
    private int textSize = 0;

    private ValueAnimator animator;
    private int heightText = 0;
    private int heightSeekBar = 0;
    private int marginLeft = 0;
    private int marginRight = 0;
    private int heightCircle = 0;
    private float xCircle = 0;
    private Paint paintText;
    public static Typeface fontText;
    private int width = 0;
    private int height = 0;
    private int lengthSection = 0;
    private Paint BackgroundFillPaint;
    private int heightDegree = 0;
    private List<String> listTitle = new ArrayList<>();

    private int xMin = 0;
    private int xMax = 0;


    public SeekBar(Context context) {
        super(context);
        init(context, null);
    }

    public SeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;

        heightDegree = heightTrack * 3;
        // marginBetweenTextAndSeekBar = (int) SizeConverter.dpToPx(getContext(), 45);
        heightCircle = thumbRadius;
        marginRight = marginLeft = shadowSize;
        lengthSection = (width - marginRight - marginLeft) / (numberSections - 1);
        xMin = marginLeft;
        xMax = width - marginLeft;

        if (xCircle == 0) {
            xCircle = xMin;
        }

        super.onSizeChanged(w, h, oldw, oldh);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {

        AssetManager assetMgr = getContext().getAssets();
        Resources resources = getResources();

        mCallbackRx = PublishSubject.create();

        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SeekBar, 0, 0);
        colorText = ta.getColor(R.styleable.SeekBar_textColor, resources.getColor(R.color.textColorSeekBar));
        shadowColor = ta.getColor(R.styleable.SeekBar_shadowColor, resources.getColor(R.color.shadowColor));
        thumbColor = ta.getColor(R.styleable.SeekBar_thumbColor, resources.getColor(R.color.thumbColor));
        numberSections = ta.getDimensionPixelSize(R.styleable.SeekBar_numberSections, 4);


        thumbRadius = (int) SizeConverter.dpToPx(getContext(), ta.getDimensionPixelSize(R.styleable.SeekBar_thumbRadius, 10));
        shadowSize = thumbRadius + (int) SizeConverter.dpToPx(getContext(), ta.getDimensionPixelSize(R.styleable.SeekBar_sizeShadow, 1));
        marginBetweenTextAndSeekBar = (int) SizeConverter.dpToPx(getContext(), ta.getDimensionPixelSize(R.styleable.SeekBar_marginTopBetweenSeekBarAndText, 18));
        heightTrack = (int) SizeConverter.dpToPx(getContext(), ta.getDimensionPixelSize(R.styleable.SeekBar_heightTrack, 2));
        widthDegree = (int) SizeConverter.dpToPx(getContext(), ta.getDimensionPixelSize(R.styleable.SeekBar_widthDegree, 1));
        textSize = (int) ta.getDimensionPixelSize(R.styleable.SeekBar_textSize, (int) SizeConverter.dpToPx(getContext(), 13));

        ta.recycle();


        fontText = Typeface.createFromAsset(assetMgr, "fonts/iransansmobile.ttf");
        paintText = PaintText.getPaintText(textSize, colorText, PaintText.CENTER_TEXT, fontText, false);
        //Paint Background
        BackgroundFillPaint = new Paint();
        BackgroundFillPaint.setAntiAlias(true);
        BackgroundFillPaint.setColor(Color.GREEN);
        BackgroundFillPaint.setStyle(Paint.Style.FILL);
        BackgroundFillPaint.setShadowLayer(1, 0, 0, Color.BLACK);
    }


    public SeekBar setFont(Typeface font) {
        this.fontText = font;
        return this;
    }


    public SeekBar setTitle(List<String> listTitle) {
        this.listTitle = listTitle;
        return this;
    }


    public SeekBar setTimeAnimate(long animatorDuration) {
        this.animatorDuration = animatorDuration;
        return this;
    }

    public void build() {
        invalidate();
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        int heightTopTrack = 0;

        for (int i = 0; i < numberSections; i++) {
            Rect bounds = new Rect();
            paintText.getTextBounds(getTextTitle(i), 0, getTextTitle(i).length(), bounds);
            heightText = (int) (bounds.height() * 1.1 + getPaddingTop());
            heightTopTrack = marginBetweenTextAndSeekBar + heightText;

            final Path path2 = new Path();
            RectF rect2 = null;

            if (i == 0) {
                rect2 = new RectF(marginLeft, heightTopTrack, marginLeft + widthDegree, heightDegree + heightTopTrack);
                canvas.drawText(getTextTitle(i), (float) (marginLeft + bounds.width() * 0.5), heightText, paintText);
            } else if (i == numberSections - 1) {
                rect2 = new RectF(marginLeft + lengthSection * i - widthDegree, heightTopTrack, width - marginLeft, heightDegree + heightTopTrack);
                canvas.drawText(getTextTitle(i), (float) (marginLeft + lengthSection * i - bounds.width() * 0.5), heightText, paintText);
            } else {
                rect2 = new RectF(marginLeft + lengthSection * i - widthDegree, heightTopTrack, lengthSection * i + marginLeft, heightDegree + heightTopTrack);
                canvas.drawText(getTextTitle(i), marginLeft + lengthSection * i, heightText, paintText);
            }

            path2.addRoundRect(rect2, getCorner(widthDegree), Path.Direction.CW);
            canvas.drawPath(path2, getPaint(colorText));
        }

        final Path path = new Path();
        RectF rect = new RectF(marginLeft, heightTrack + heightTopTrack, width - marginRight, heightTrack * 2 + heightTopTrack);

        path.addRoundRect(rect, getCorner(heightTrack), Path.Direction.CW);
        canvas.drawPath(path, getPaint(colorText));

        drawCircle(canvas, heightTopTrack);


    }

    private void drawCircle(Canvas canvas, int heightTopTrack) {
        canvas.drawCircle((float) (xCircle), (float) (heightTrack + heightTopTrack), (float) (shadowSize), getPaintCircle(shadowColor));
        canvas.drawCircle((float) (xCircle), (float) (heightTrack + heightTopTrack), (float) (thumbRadius), getPaintCircle(Color.WHITE));
    }

    private String getTextTitle(int i) {
        if (listTitle.size() != 0) {
            return listTitle.get(i);
        } else {
            return "";
        }
    }


    private Paint getPaint(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        return paint;
    }


    private float[] getCorner(float sizeCorner) {

        return new float[]{
                sizeCorner, sizeCorner, // Top left radius in px
                sizeCorner, sizeCorner, // Top right radius in px
                sizeCorner, sizeCorner, // Bottom right radius in px
                sizeCorner, sizeCorner  // Bottom left radius in px
        };

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        if (x <= xMin) {
            x = xMin;
        } else if (x >= xMax) {
            x = xMax;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            for (int i = 0; i < numberSections; i++) {
                if (x >= xMin && x < xMin + lengthSection * 0.5) {
                    x = xMin;
                    break;
                } else if (x >= (xMin + lengthSection * i - lengthSection * 0.5) && x < (xMin + lengthSection * i + lengthSection * 0.5)) {
                    x = xMin + lengthSection * i;
                    break;
                }
            }
            calculateViewAnimation(x);
            postInvalidate();
            return true;

        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

            xCircle = (int) x;
            invalidate();
            return true;

        } else if (event.getAction() == MotionEvent.ACTION_UP) {

            for (int i = 0; i < numberSections; i++) {
                if (x >= xMin && x < xMin + lengthSection * 0.5) {
                    x = xMin;
                    mCallbackRx.onNext(i);
                    break;
                } else if (x >= (xMin + lengthSection * i - lengthSection * 0.5) && x < (xMin + lengthSection * i + lengthSection * 0.5)) {
                    x = xMin + lengthSection * i;
                    mCallbackRx.onNext(i);
                    break;
                }
            }
            calculateViewAnimation(x);

            return true;
        }

        return true;
    }

    private Paint getPaintCircle(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    public PublishSubject<Integer> getCallbackSubjectRx() {
        return mCallbackRx;
    }

    private void calculateViewAnimation(float xTouch) {

        float prevValue = xCircle;

        // cancel last animation
        if (animator != null) {
            animator.cancel();
        }

        animator = ValueAnimator.ofFloat(prevValue, xTouch);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        float delta = (float) Math.abs(xTouch - prevValue);
        long duration = (long) (delta * animatorDuration / xMax);


        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                xCircle = (float) valueAnimator.getAnimatedValue();
                SeekBar.this.postInvalidate();
            }
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.start();
    }


    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //  super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec((int) SizeConverter.dpToPx(getContext(),50),MeasureSpec.EXACTLY));


        int desiredWidth = (int) SizeConverter.dpToPx(getContext(), 350);
        int desiredHeight = (int) SizeConverter.dpToPx(getContext(), 50);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }


}
