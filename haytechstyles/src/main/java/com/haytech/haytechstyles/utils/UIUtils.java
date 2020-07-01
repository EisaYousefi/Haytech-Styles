package com.haytech.haytechstyles.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class UIUtils {
	private static int screenWidth = 0;
	private static int screenHeight = 0;
	public static int getWidth(Context context) {
		if (screenWidth <=0) {
			readScreenInfo(context);
		}
		return screenWidth;
	}
	
	
	public static int getHeight(Context context) {
		if (screenHeight <= 0) {
			readScreenInfo(context);
		}
		return screenHeight;
	}


	public static void readScreenInfo(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		 wm.getDefaultDisplay().getMetrics(outMetrics );
		 screenHeight =outMetrics.heightPixels;
		screenWidth = outMetrics.widthPixels;
	}

	public static float spToPx(Context ctx, float sp) {
		return sp * ctx.getResources().getDisplayMetrics().scaledDensity;
	}

	public static float pxToDp(final Context context, final float px) {
		return px / context.getResources().getDisplayMetrics().density;
	}

	public static float dpToPx(final Context context, final float dp) {
		return dp * context.getResources().getDisplayMetrics().density;
	}
}
