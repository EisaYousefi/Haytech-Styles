package com.haytech.haytechstyles.utils;



public class PropertyRect {
    private boolean isEqualCorner = true;
    private float CornerTopLeft = 0;
    private float CornerTopRight = 0;
    private float CornerBottomLeft = 0;
    private float CornerBottomRight = 0;
    private float left = 0;
    private float top = 0;
    private float right = 0;
    private float bottom  = 0;


    public boolean isEqualCorner() {
        return isEqualCorner;
    }

    public void setEqualCorner(boolean equalCorner) {
        isEqualCorner = equalCorner;
    }

    public float getCornerTopLeft() {
        return CornerTopLeft;
    }

    public void setCornerTopLeft(float cornerTopLeft) {
        CornerTopLeft = cornerTopLeft;
    }

    public float getCornerTopRight() {
        return CornerTopRight;
    }

    public void setCornerTopRight(float cornerTopRight) {
        CornerTopRight = cornerTopRight;
    }

    public float getCornerBottomLeft() {
        return CornerBottomLeft;
    }

    public void setCornerBottomLeft(float cornerBottomLeft) {
        CornerBottomLeft = cornerBottomLeft;
    }

    public float getCornerBottomRight() {
        return CornerBottomRight;
    }

    public void setCornerBottomRight(float cornerBottomRight) {
        CornerBottomRight = cornerBottomRight;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }
}
