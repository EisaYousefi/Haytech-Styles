package com.haytech.haytechstyles.multibutton;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.haytech.haytechstyles.R;

public class ItemStyleModel {


    private String labelText;
    private int labelTextColor;
    private float labelTextSize;
    private int labelVisibility;
    private Typeface labelTypeface;
    private int labelPaddingLeft;
    private int labelPaddingTop;
    private int labelPaddingRight;
    private int labelPaddingBottom;
    private int labelBackgroundColor;//
    private int labelBackgroundResource;//
    private int position;
    private int width;
    private int height;
    private int marginLeft;
    private int marginTop;
    private int marginRight;
    private int marginBottom;
    private int backgroundColor;
    private GradientDrawable backgroundDrawable;
    private boolean isSelectedItem;
    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;
    private Builder builder;


    private ItemStyleModel(Builder builder) {
        setBuilder(builder);
    }

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
                context.getResources().getDisplayMetrics());
    }

    public static ItemStyleModel getUnSelectedItemStyle(Context context) {
        Builder builder = new Builder();
        builder.setLabelText("")
                .setLabelTextColor(context.getResources().getColor(R.color.blue))
                .setLabelTextSize(20)
                .setLabelVisibility(View.VISIBLE)
                .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setHeight(dp2px(context, 48))
                .setMarginLeft(10)
                .setMarginTop(20)
                .setMarginRight(10)
                .setMarginBottom(20)
                .setLabelPaddingLeft(16)
                .setLabelPaddingTop(8)
                .setLabelPaddingRight(16)
                .setLabelPaddingBottom(8)
                .setBackgroundDrawable(getBorderBackgroundDrawable(context, 12, Color.TRANSPARENT));
//                .setLabelTypeface(Typeface.DEFAULT)
//                .setLabelPaddingLeft(8)
//                .setLabelPaddingTop(8)
//                .setLabelPaddingRight(8)
//                .setLabelPaddingBottom(8)
//                .setLabelBackgroundColor(Color.TRANSPARENT)
//                .setLabelBackgroundResource(R.drawable.ic_close_black_24dp)
//                .setBackgroundColor(Color.TRANSPARENT);
        return builder.build();
    }
    public static ItemStyleModel getUnSelectedItemStyleByName(Context context, String labelText) {
        Builder builder = new Builder();
        builder.setLabelText(labelText)
                .setLabelTextColor(context.getResources().getColor(R.color.blue))
                .setLabelTextSize(20)
                .setLabelVisibility(View.VISIBLE)
                .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setHeight(dp2px(context, 48))
                .setMarginLeft(10)
                .setMarginTop(20)
                .setMarginRight(10)
                .setMarginBottom(20)
                .setLabelPaddingLeft(16)
                .setLabelPaddingTop(8)
                .setLabelPaddingRight(16)
                .setLabelPaddingBottom(8)
                .setBackgroundDrawable(getBorderBackgroundDrawable(context, 12, Color.TRANSPARENT));
//                .setLabelTypeface(Typeface.DEFAULT)
//                .setLabelPaddingLeft(8)
//                .setLabelPaddingTop(8)
//                .setLabelPaddingRight(8)
//                .setLabelPaddingBottom(8)
//                .setLabelBackgroundColor(Color.TRANSPARENT)
//                .setLabelBackgroundResource(com.example.babnowidgets.R.drawable.ic_close_black_24dp)
//                .setBackgroundColor(Color.TRANSPARENT);
        return builder.build();
    }


    public static ItemStyleModel getSelectedItemStyle(Context context) {
        Builder builder = new Builder();
        builder.setSelectedItem(true)
                .setLabelText("")
                .setLabelTextColor(Color.WHITE)
                .setLabelTextSize(20)
                .setLabelVisibility(View.VISIBLE)
                .setLabelPaddingLeft(16)
                .setLabelPaddingTop(8)
                .setLabelPaddingRight(16)
                .setLabelPaddingBottom(8)
                .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setHeight(dp2px(context, 48))
                .setMarginLeft(10)
                .setMarginTop(5)
                .setMarginRight(10)
                .setMarginBottom(5)
                .setBackgroundDrawable(getBorderBackgroundDrawable(context, 12, context.getResources().getColor(R.color.blue)));
//              .setLabelTypeface(Typeface.DEFAULT)
//              .setLabelBackgroundColor(Color.TRANSPARENT)
//              .setLabelBackgroundResource(com.example.babnowidgets.R.drawable.ic_close_black_24dp)
//              .setPosition(i)
//              .setBackgroundColor(Color.GRAY);
        return builder.build();
    }

    public static GradientDrawable getBorderBackgroundDrawable(Context context, float borderRadius, int color) {
        GradientDrawable gd = new GradientDrawable();
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setSize(dp2px(context, 48), dp2px(context, 48));
        gd.setColor(color); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(dp2px(context, borderRadius));

        return gd;
    }

    public Builder getBuilder() {
        return builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
        this.labelText = builder.labelText;
        this.labelTextColor = builder.labelTextColor;
        this.labelTextSize = builder.labelTextSize;
        this.labelVisibility = builder.labelVisibility;
        this.labelTypeface = builder.labelTypeface;
        this.labelPaddingLeft = builder.labelPaddingLeft;
        this.labelPaddingTop = builder.labelPaddingTop;
        this.labelPaddingRight = builder.labelPaddingRight;
        this.labelPaddingBottom = builder.labelPaddingBottom;
        this.labelBackgroundColor = builder.labelBackgroundColor;
        this.labelBackgroundResource = builder.labelBackgroundResource;
        this.position = builder.position;
        this.width = builder.width;
        this.height = builder.height;
        this.marginLeft = builder.marginLeft;
        this.marginTop = builder.marginTop;
        this.marginRight = builder.marginRight;
        this.marginBottom = builder.marginBottom;
        this.backgroundColor = builder.backgroundColor;
        this.backgroundDrawable = builder.backgroundDrawable;
        this.isSelectedItem = builder.isSelectedItem;
        this.paddingLeft = builder.paddingLeft;
        this.paddingTop = builder.paddingTop;
        this.paddingRight = builder.paddingRight;
        this.paddingBottom = builder.paddingBottom;
    }

    public String getLabelText() {
        return labelText;
    }

    public int getLabelTextColor() {
        return labelTextColor;
    }

    public float getLabelTextSize() {
        return labelTextSize;
    }

    public int getLabelVisibility() {
        return labelVisibility;
    }

    public Typeface getLabelTypeface() {
        return labelTypeface;
    }

    public int getLabelPaddingLeft() {
        return labelPaddingLeft;
    }

    public int getLabelPaddingTop() {
        return labelPaddingTop;
    }

    public int getLabelPaddingRight() {
        return labelPaddingRight;
    }

    public int getLabelPaddingBottom() {
        return labelPaddingBottom;
    }

    public int getLabelBackgroundColor() {
        return labelBackgroundColor;
    }

    public int getLabelBackgroundResource() {
        return labelBackgroundResource;
    }

    public int getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public GradientDrawable getBackgroundDrawable() {
        return backgroundDrawable;
    }

    public boolean isSelectedItem() {
        return isSelectedItem;
    }


    public int getPaddingLeft() {
        return paddingLeft;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }

    public static class Builder {
        private String labelText;
        private int labelTextColor;
        private float labelTextSize;
        private int labelVisibility;
        private Typeface labelTypeface;
        private int labelPaddingLeft;
        private int labelPaddingTop;
        private int labelPaddingRight;
        private int labelPaddingBottom;
        private int labelBackgroundColor;//
        private int labelBackgroundResource;//
        private int position;
        private int width;
        private int height;
        private int marginLeft;
        private int marginTop;
        private int marginRight;
        private int marginBottom;
        private int backgroundColor;
        private GradientDrawable backgroundDrawable;
        private boolean isSelectedItem;
        private int paddingLeft;
        private int paddingTop;
        private int paddingRight;
        private int paddingBottom;

        public Builder() {
        }

        public Builder setLabelText(String labelText) {
            this.labelText = labelText;
            return this;
        }

        public Builder setLabelTextColor(int labelTextColor) {
            this.labelTextColor = labelTextColor;
            return this;
        }

        public Builder setLabelTextSize(float labelTextSize) {
            this.labelTextSize = labelTextSize;
            return this;
        }

        public Builder setLabelVisibility(int labelVisibility) {
            this.labelVisibility = labelVisibility;
            return this;
        }

        public Builder setLabelTypeface(Typeface labelTypeface) {
            this.labelTypeface = labelTypeface;
            return this;
        }

        public Builder setLabelPaddingLeft(int labelPaddingLeft) {
            this.labelPaddingLeft = labelPaddingLeft;
            return this;
        }

        public Builder setLabelPaddingTop(int labelPaddingTop) {
            this.labelPaddingTop = labelPaddingTop;
            return this;
        }

        public Builder setLabelPaddingRight(int labelPaddingRight) {
            this.labelPaddingRight = labelPaddingRight;
            return this;
        }

        public Builder setLabelPaddingBottom(int labelPaddingBottom) {
            this.labelPaddingBottom = labelPaddingBottom;
            return this;
        }

        public Builder setLabelBackgroundColor(int labelBackgroundColor) {
            this.labelBackgroundColor = labelBackgroundColor;
            return this;
        }

        public Builder setLabelBackgroundResource(int labelBackgroundResource) {
            this.labelBackgroundResource = labelBackgroundResource;
            return this;
        }

        public Builder setPosition(int position) {
            this.position = position;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setMarginLeft(int marginLeft) {
            this.marginLeft = marginLeft;
            return this;
        }

        public Builder setMarginTop(int marginTop) {
            this.marginTop = marginTop;
            return this;
        }

        public Builder setMarginRight(int marginRight) {
            this.marginRight = marginRight;
            return this;
        }

        public Builder setMarginBottom(int marginBottom) {
            this.marginBottom = marginBottom;
            return this;
        }

        public Builder setBackgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder setBackgroundDrawable(GradientDrawable backgroundDrawable) {
            this.backgroundDrawable = backgroundDrawable;
            return this;
        }

        public Builder setSelectedItem(boolean selectedItem) {
            this.isSelectedItem = selectedItem;
            return this;
        }


        public Builder setPaddingLeft(int paddingLeft) {
            this.paddingLeft = paddingLeft;
            return this;
        }

        public Builder setPaddingTop(int paddingTop) {
            this.paddingTop = paddingTop;
            return this;
        }

        public Builder setPaddingRight(int paddingRight) {
            this.paddingRight = paddingRight;
            return this;
        }

        public Builder setPaddingBottom(int paddingBottom) {
            this.paddingBottom = paddingBottom;
            return this;
        }

        public ItemStyleModel build() {
            return new ItemStyleModel(this);
        }

        public ItemStyleModel update(ItemStyleModel itemStyleModel) {
            itemStyleModel.labelText = this.labelText;
            itemStyleModel.labelTextColor = this.labelTextColor;
            itemStyleModel.labelTextSize = this.labelTextSize;
            itemStyleModel.labelVisibility = this.labelVisibility;
            itemStyleModel.labelTypeface = this.labelTypeface;
            itemStyleModel.labelPaddingLeft = this.labelPaddingLeft;
            itemStyleModel.labelPaddingTop = this.labelPaddingTop;
            itemStyleModel.labelPaddingRight = this.labelPaddingRight;
            itemStyleModel.labelPaddingBottom = this.labelPaddingBottom;
            itemStyleModel.labelBackgroundColor = this.labelBackgroundColor;
            itemStyleModel.labelBackgroundResource = this.labelBackgroundResource;
            itemStyleModel.position = this.position;
            itemStyleModel.width = this.width;
            itemStyleModel.height = this.height;
            itemStyleModel.marginLeft = this.marginLeft;
            itemStyleModel.marginTop = this.marginTop;
            itemStyleModel.marginRight = this.marginRight;
            itemStyleModel.marginBottom = this.marginBottom;
            itemStyleModel.backgroundColor = this.backgroundColor;
            itemStyleModel.backgroundDrawable = this.backgroundDrawable;
            itemStyleModel.isSelectedItem = this.isSelectedItem;
            itemStyleModel.paddingLeft = this.paddingLeft;
            itemStyleModel.paddingTop = this.paddingTop;
            itemStyleModel.paddingRight = this.paddingRight;
            itemStyleModel.paddingBottom = this.paddingBottom;
            return itemStyleModel;
        }
    }
}
