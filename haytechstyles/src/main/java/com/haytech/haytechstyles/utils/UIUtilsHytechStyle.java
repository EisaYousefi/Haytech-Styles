package com.haytech.haytechstyles.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class UIUtilsHytechStyle {
	private  int screenWidth = 0;
	private  int screenHeight = 0;

	private static UIUtilsHytechStyle instance ;

	private UIUtilsHytechStyle() {
	}

	public static UIUtilsHytechStyle getInstance() {
		if (instance == null)
			instance = new UIUtilsHytechStyle();
		return instance;
	}

	public  int getWidth(Context context) {
		if (screenWidth <=0) {
			readScreenInfo(context);
		}
		return screenWidth;
	}
	
	
	public  int getHeight(Context context) {
		if (screenHeight <= 0) {
			readScreenInfo(context);
		}
		return screenHeight;
	}


	public  void readScreenInfo(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		 wm.getDefaultDisplay().getMetrics(outMetrics );
		 screenHeight =outMetrics.heightPixels;
		screenWidth = outMetrics.widthPixels;
	}

	public  float spToPx(Context ctx, float sp) {
		return sp * ctx.getResources().getDisplayMetrics().scaledDensity;
	}

	public  float pxToDp(final Context context, final float px) {
		return px / context.getResources().getDisplayMetrics().density;
	}

	public  float dpToPx(final Context context, final float dp) {
		return dp * context.getResources().getDisplayMetrics().density;
	}


	public  float getWidthMethod(float width , float number) {
		return width/number;
	}

	public  float getHeightMethod(float height , float number) {
		return height/number;
	}
	public  float getRadius(float radius , float number) {
		return radius/number;
	}

	public  float calculateMorph(float radius , float morph){
		return radius * morph ;
	}

	public  void animateTransferTypeButton(View view) {
		try {
			YoYo.with(Techniques.Shake)
					.duration(700)
					.repeat(1)
					.playOn(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
