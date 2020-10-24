package com.haytech.haytechstyles.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class UtilImage {

    public static Bitmap getResizedBitmap(Bitmap srcBitmap, int requiredWidth, int requiredHeight) {
        try {
            Matrix matrix = new Matrix();
            RectF srcRect = new RectF(0, 0, srcBitmap.getWidth(), srcBitmap.getHeight());
            RectF dstRect = new RectF(0, 0, requiredWidth, requiredHeight);
            matrix.setRectToRect(srcRect, dstRect, Matrix.ScaleToFit.CENTER);

            return Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, true);
        } catch (Exception e) {
            return null;
        }
    }


    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}
