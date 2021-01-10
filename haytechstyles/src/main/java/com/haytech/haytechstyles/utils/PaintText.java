package com.haytech.haytechstyles.utils;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Typeface;


public class PaintText {


    public static PaintText getInstance() {
        if (instance == null)
            instance = new PaintText();
        return instance;
    }


    public static final int CENTER_TEXT = 1;
    public static final int LEFT_TEXT = 2;
    public static final int RIGHT_TEXT = 3;
    public static PaintText instance;



    public static Paint getPaintText(Context context, int textSize, int textColor, int statusText, Typeface typeface, boolean isBold) {


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
                AssetManager assetMgr = context.getAssets();
                paintText.setTypeface(Typeface.createFromAsset(assetMgr, "fonts/dana_fa_num_regular.ttf"));
            }
        }
        return paintText;
    }
}
