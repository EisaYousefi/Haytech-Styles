package com.haytech.haytechstyles.utils;

import android.graphics.Path;
import android.graphics.RectF;

public class UtilRect {

    public static Path pathRect(PropertyRect propertyRect){

        final Path path = new Path();
        final RectF rect = new RectF(propertyRect.getLeft(), propertyRect.getTop(), propertyRect.getRight(), propertyRect.getBottom());


        if(propertyRect.isEqualCorner()){
            float corner = 0;
            if(propertyRect.getCornerTopLeft()!=0){
                corner = propertyRect.getCornerTopLeft();
            }else if (propertyRect.getCornerTopRight()!=0){
                corner = propertyRect.getCornerTopRight();
            }else if (propertyRect.getCornerBottomLeft()!=0){
                corner = propertyRect.getCornerBottomLeft();
            }else if (propertyRect.getCornerBottomRight()!=0){
                corner = propertyRect.getCornerBottomRight();
            }
            path.addRoundRect(rect,
                    cornerRect(corner,corner,corner,corner),
                    Path.Direction.CW);
        }else{
            path.addRoundRect(rect,
                    cornerRect(propertyRect.getCornerTopLeft(),propertyRect.getCornerTopRight(),propertyRect.getCornerBottomRight(),propertyRect.getCornerBottomLeft()),
                    Path.Direction.CW);
        }
        return path;

    }

    private static float[] cornerRect(float tLeft, float tRight, float bRight, float bLeft) {
        return new float[]{
                tLeft, tLeft, // Top left radius in px
                tRight, tRight, // Top right radius in px
                bRight, bRight, // Bottom right radius in px
                bLeft, bLeft  // Bottom left radius in px
        };
    }
}
