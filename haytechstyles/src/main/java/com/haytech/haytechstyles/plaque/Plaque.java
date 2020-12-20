package com.haytech.haytechstyles.plaque;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.haytech.haytechstyles.R;
import com.haytech.haytechstyles.utils.PropertyRect;
import com.haytech.haytechstyles.utils.SizeConverter;
import com.haytech.haytechstyles.utils.UtilImage;
import com.haytech.haytechstyles.utils.UtilPaint;
import com.haytech.haytechstyles.utils.UtilRect;


public class Plaque extends View {

    private int width = 0;
    private int height = 0;
    private float rateWightToHeight = 0.18821f;
    private float margin1 = 0;
    private float margin1dot5 = 0;
    private float radius = 0;
    private int colorBackground = 0;
    private Paint paintText;
    private Paint paintTextWhite;
    private Bitmap iconFlag = null;
    private Bitmap iconTextIran = null;
    private Typeface typeface;

    private ModelPlaque modelPlaque;

    public Plaque(Context context) {
        super(context);
        init(context, null);
    }

    public Plaque(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Plaque(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setTexts(ModelPlaque modelPlaque) {
        this.modelPlaque = modelPlaque;
        invalidate();
    }

    public ModelPlaque getTexts() {
        return modelPlaque;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Plaque(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        modelPlaque = new ModelPlaque("12","الف","342","22");

        AssetManager assetMgr = getContext().getAssets();
        typeface = Typeface.createFromAsset(assetMgr, "fonts/dana_fa_num_bold.ttf");

        this.iconFlag = BitmapFactory.decodeResource(this.getResources(), R.drawable.flag);
        this.iconTextIran = UtilImage.getBitmapFromVectorDrawable(getContext(), R.drawable.ic_text_iran);

        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // We only need to call this just one time for this image resizing purpose, but first check the version of Android
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // NOTE: At this point, you have the actual width and height of the CustomView
                iconFlag = UtilImage.getResizedBitmap(iconFlag, (int) (getWidth() * 0.1), (int) (getWidth() * 0.078));
                iconTextIran = UtilImage.getResizedBitmap(iconTextIran, (int) (getWidth() * 0.13), (int) (getWidth() * 0.078));
            }
        });

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = (int) (width * rateWightToHeight);

        margin1 = (float) (width * 1.5 / 162);
        margin1dot5 = (float) (width * 1.5 / 162);
        radius = width / 40;
        paintText = UtilPaint.getPaintText((float) (width * 0.09), Color.BLACK, UtilPaint.LEFT_TEXT, typeface, false);
        paintTextWhite = UtilPaint.getPaintText((float) (width * 0.026), Color.WHITE, UtilPaint.LEFT_TEXT, typeface, false);

        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Resources resources = getResources();
        final PropertyRect propertyRect = new PropertyRect();
        propertyRect.setLeft(0);
        propertyRect.setRight(width);
        propertyRect.setTop(0);
        propertyRect.setBottom((height));
        propertyRect.setEqualCorner(true);
        propertyRect.setCornerTopLeft(radius);
        canvas.drawPath(UtilRect.pathRect(propertyRect), UtilPaint.getPaint(resources.getColor(R.color.backgroundPlaque)));

        final PropertyRect propertyRect2 = new PropertyRect();
        propertyRect2.setLeft(margin1);
        propertyRect2.setRight((float) ((width * 0.78234)));
        propertyRect2.setTop(margin1);
        propertyRect2.setBottom(((width) * rateWightToHeight - margin1dot5));
        propertyRect2.setEqualCorner(true);
        propertyRect2.setCornerTopLeft(radius);
        canvas.drawPath(UtilRect.pathRect(propertyRect2), UtilPaint.getPaint(resources.getColor(R.color.white)));

        final PropertyRect propertyRect3 = new PropertyRect();
        propertyRect3.setLeft((float) (width - width * 0.2 - margin1));
        propertyRect3.setRight((float) ((width) - margin1));
        propertyRect3.setTop(margin1);
        propertyRect3.setBottom(((width) * rateWightToHeight - margin1dot5));
        propertyRect3.setEqualCorner(true);
        propertyRect3.setCornerTopLeft(radius);
        canvas.drawPath(UtilRect.pathRect(propertyRect3), UtilPaint.getPaint(resources.getColor(R.color.white)));

        final PropertyRect propertyRect4 = new PropertyRect();
        propertyRect4.setLeft(margin1);
        propertyRect4.setRight((float) ((width * 0.14)));
        propertyRect4.setTop(margin1);
        propertyRect4.setBottom(((width) * rateWightToHeight - margin1dot5));
        propertyRect4.setEqualCorner(false);
        propertyRect4.setCornerTopLeft(radius);
        propertyRect4.setCornerBottomLeft(radius);
        canvas.drawPath(UtilRect.pathRect(propertyRect4), UtilPaint.getPaint(resources.getColor(R.color.blue)));

        String txt1 = "I.R.";
        String txt2 = "IRAN";

        canvas.drawText(modelPlaque.getTxtPart1(), (float) (width * 0.17), (float) (width * rateWightToHeight * 0.5 + UtilPaint.bound(modelPlaque.getTxtPart1(), paintText).height() / 2), paintText);
        canvas.drawText(modelPlaque.getTxtPart2(), (float) (width * 0.36), (float) (width * rateWightToHeight * 0.5 + UtilPaint.bound(modelPlaque.getTxtPart2(), paintText).height() / 2), paintText);
        canvas.drawText(modelPlaque.getTxtPart3(), (float) (width * 0.57), (float) (width * rateWightToHeight * 0.5 + UtilPaint.bound(modelPlaque.getTxtPart3(), paintText).height() / 2), paintText);
        canvas.drawText(modelPlaque.getTxtPart4(), (float) (width * 0.85), (float) (width * rateWightToHeight * 0.5 + UtilPaint.bound(modelPlaque.getTxtPart4(), paintText).height() / 1.1), paintText);
        canvas.drawText(txt1, (float) (width * 0.03), (float) (width * 0.13), paintTextWhite);
        canvas.drawText(txt2, (float) (width * 0.03), (float) (width * 0.17), paintTextWhite);

        canvas.drawBitmap(iconFlag, (float) (width * 0.03), (float) (width * 0.03), null);
        canvas.drawBitmap(iconTextIran, (float) (width * 0.834), (float) (width * 0.03), null);
    }


    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // setMeasuredDimension(width, height);
        // super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec((int) SizeConverter.dpToPx(getContext(),80),MeasureSpec.EXACTLY));
       // super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
        int width = (int) SizeConverter.dpToPx(getContext(),162);
        int height = (int) SizeConverter.dpToPx(getContext(), 30.42f);

        setMeasuredDimension(manageDimension(widthMeasureSpec, width,false), manageDimension(widthMeasureSpec,height, true));
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int manageDimension(int measureSpace, int valueDefault,boolean isHeight) {
        int valueFinal = 0;
        int valueSize = MeasureSpec.getSize(measureSpace);
        int valueMode = MeasureSpec.getMode(measureSpace);

        if(isHeight){
            valueSize = (int) (MeasureSpec.getSize(measureSpace)* rateWightToHeight * 1.01);
            height = valueSize;
        }

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

}
