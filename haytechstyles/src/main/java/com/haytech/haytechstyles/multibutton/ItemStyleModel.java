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

    private long id;
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
                .setLabelTextSize(15)
                .setLabelVisibility(View.VISIBLE)
                .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setMarginLeft(0)
                .setMarginTop(0)
                .setMarginRight(0)
                .setMarginBottom(0)
                .setLabelPaddingLeft(16)
                .setLabelPaddingTop(16)
                .setLabelPaddingRight(16)
                .setLabelPaddingBottom(16)
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
                .setLabelTextSize(15)
                .setLabelVisibility(View.VISIBLE)
                .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setMarginLeft(0)
                .setMarginTop(0)
                .setMarginRight(0)
                .setMarginBottom(0)
                .setLabelPaddingLeft(16)
                .setLabelPaddingTop(16)
                .setLabelPaddingRight(16)
                .setLabelPaddingBottom(16)
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

    public static ItemStyleModel getUnSelectedItemStyleByName(Context context, String labelText, long id) {
        Builder builder = new Builder();
        builder
                .setId(id)
                .setLabelText(labelText)
                .setLabelTextColor(context.getResources().getColor(R.color.blue))
                .setLabelTextSize(15)
                .setLabelVisibility(View.VISIBLE)
                .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setMarginLeft(0)
                .setMarginTop(0)
                .setMarginRight(0)
                .setMarginBottom(0)
                .setLabelPaddingLeft(16)
                .setLabelPaddingTop(16)
                .setLabelPaddingRight(16)
                .setLabelPaddingBottom(16)
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

    public static ItemStyleModel getUnSelectedItemStyleByName(Context context, ItemStyleModel itemStyleModel) {
        Builder builder = new Builder();
        if (itemStyleModel.getId() != 0) builder.setId(itemStyleModel.getId());
        else builder.setId(0);
        if (itemStyleModel.getLabelText() != null)
            builder.setLabelText(itemStyleModel.getLabelText());
        else builder.setLabelText("");
        if (itemStyleModel.getLabelTextColor() != 0)
            builder.setLabelTextColor(itemStyleModel.getLabelTextColor());
        else builder.setLabelTextColor(context.getResources().getColor(R.color.blue));
        if (itemStyleModel.getLabelTextSize() != 0)
            builder.setLabelTextSize(itemStyleModel.getLabelTextSize());
        else builder.setLabelTextSize(20);
        if (itemStyleModel.getLabelVisibility() != 0)
            builder.setLabelVisibility(itemStyleModel.getLabelVisibility());
        else builder.setLabelVisibility(View.VISIBLE);
        if (itemStyleModel.getWidth() != 0) builder.setWidth(itemStyleModel.getWidth());
        else builder.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        if (itemStyleModel.getHeight() != 0) builder.setHeight(itemStyleModel.getHeight());
        else builder.setHeight(dp2px(context, 48));
        if (itemStyleModel.getMarginLeft() != 0)
            builder.setMarginLeft(itemStyleModel.getMarginLeft());
        else builder.setMarginLeft(0);
        if (itemStyleModel.getMarginTop() != 0) builder.setMarginTop(itemStyleModel.getMarginTop());
        else builder.setMarginTop(0);
        if (itemStyleModel.getMarginRight() != 0)
            builder.setMarginRight(itemStyleModel.getMarginRight());
        else builder.setMarginRight(0);
        if (itemStyleModel.getMarginBottom() != 0)
            builder.setMarginBottom(itemStyleModel.getMarginBottom());
        else builder.setMarginBottom(0);
        if (itemStyleModel.getLabelPaddingLeft() != 0)
            builder.setLabelPaddingLeft(itemStyleModel.getLabelPaddingLeft());
        else builder.setLabelPaddingLeft(10);
        if (itemStyleModel.getLabelPaddingTop() != 0)
            builder.setLabelPaddingTop(itemStyleModel.getLabelPaddingTop());
        else builder.setLabelPaddingTop(10);
        if (itemStyleModel.getLabelPaddingRight() != 0)
            builder.setLabelPaddingRight(itemStyleModel.getLabelPaddingRight());
        else builder.setLabelPaddingRight(10);
        if (itemStyleModel.getLabelPaddingBottom() != 0)
            builder.setLabelPaddingBottom(itemStyleModel.getLabelPaddingBottom());
        else builder.setLabelPaddingBottom(10);
        if (itemStyleModel.getBackgroundDrawable() != null)
            builder.setBackgroundDrawable(itemStyleModel.getBackgroundDrawable());
        else
            builder.setBackgroundDrawable(getBorderBackgroundDrawable(context, 12, Color.TRANSPARENT));
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
                .setLabelTextSize(15)
                .setLabelVisibility(View.VISIBLE)
                .setLabelPaddingLeft(16)
                .setLabelPaddingTop(16)
                .setLabelPaddingRight(16)
                .setLabelPaddingBottom(16)
                .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setMarginLeft(0)
                .setMarginTop(0)
                .setMarginRight(0)
                .setMarginBottom(0)
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
        this.id = builder.id;
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

    public long getId() {
        return id;
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
        private long id;
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

        public Builder setId(long id) {
            this.id = id;
            return this;
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
            itemStyleModel.id = this.id;
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
