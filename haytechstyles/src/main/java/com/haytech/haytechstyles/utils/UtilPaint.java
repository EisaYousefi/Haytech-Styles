package com.haytech.haytechstyles.utils;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class UtilPaint {

    public static final int CENTER_TEXT = 1;
    public static final int LEFT_TEXT = 2;
    public static final int RIGHT_TEXT = 3;

    public static Paint getPaintText(float textSize, int textColor, int statusText, Typeface typeface, boolean isBold) {
        Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setTextSize(textSize);
        paintText.setColor(textColor);

        switch (statusText) {
            case CENTER_TEXT:
                paintText.setTextAlign(Paint.Align.CENTER);
                break;
            case LEFT_TEXT:
                paintText.setTextAlign(Paint.Align.LEFT);
                break;
            case RIGHT_TEXT:
                paintText.setTextAlign(Paint.Align.RIGHT);
                break;
        }
        if(typeface!=null){
            if(isBold){
                paintText.setFakeBoldText(false);
                paintText.setTypeface(Typeface.create(typeface, Typeface.BOLD));
            }else{
                paintText.setTypeface(typeface);
            }
        }
        return paintText;
    }

    public static Paint getPaint(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        return paint;
    }



    public static Rect bound(String txt, Paint paintText){
        final Rect bounds = new Rect();
        paintText.getTextBounds(txt, 0, txt.length(), bounds);
        return bounds;
    }

}
