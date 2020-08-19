package com.haytech.haytechstyles.filter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.haytech.haytechstyles.R;

public class FilterFieldEditText extends LinearLayout implements TextWatcher, View.OnClickListener {


    private ImageButton clearIcon,
            searchButton,
            voiceIcon,
            backIcon;
    private EditText filterBox;
    private ConstraintLayout iconContainer;
    private GradientDrawable textStateBorderBackgoundDrawable;
    private GradientDrawable emptyStateBorderBackgoundDrawable;

    //<editor-fold desc="MainMethod">
    public FilterFieldEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);

    }
    //</editor-fold>

    //<editor-fold desc="CreateMethod">
    private void initialize(final Context context, AttributeSet attrs) {
        inflateView();
        initStyle(context, attrs);
        initClickListener();
    }

    private void inflateView() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout rootView = (LinearLayout) layoutInflater.inflate(R.layout.view_search_edit_text, this, true);

        iconContainer = (ConstraintLayout) rootView.findViewById(R.id.clt_searchContainer);
        searchButton = (ImageButton) rootView.findViewById(R.id.btn_search);
        clearIcon = (ImageButton) rootView.findViewById(R.id.btn_clear);
        voiceIcon = (ImageButton) rootView.findViewById(R.id.btn_voice);
        backIcon = (ImageButton) rootView.findViewById(R.id.btn_back);

        filterBox = (EditText) rootView.findViewById(R.id.edt_search);

        voiceIcon.setVisibility(View.GONE);
        backIcon.setVisibility(View.GONE);
    }

    private void initStyle(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FilterFieldEditText);
        if (typedArray.hasValue(R.styleable.FilterFieldEditText_cv_sb_borderEmptyStateColor)) {
        }


        int layoutDirection = typedArray.getInt(R.styleable.FilterFieldEditText_cv_sb_layoutDirection, View.VISIBLE);

        int searchIconVisibility = typedArray.getInt(R.styleable.FilterFieldEditText_cv_sb_searchIconVisibility, View.VISIBLE);
        int searchIconScaleType = typedArray.getInt(R.styleable.FilterFieldEditText_cv_sb_searchIconScaleType, -1);
        int searchIconSource = typedArray.getResourceId(R.styleable.FilterFieldEditText_cv_sb_searchIconSource, R.drawable.ic_search_black_24dp);

        int clearIconVisibility = typedArray.getInt(R.styleable.FilterFieldEditText_cv_sb_clearIconVisibility, View.GONE);
        int clearIconScaleType = typedArray.getInt(R.styleable.FilterFieldEditText_cv_sb_clearIconScaleType, -1);
        int clearIconSource = typedArray.getResourceId(R.styleable.FilterFieldEditText_cv_sb_clearIconSource, R.drawable.ic_close_black_24dp);

        String filterBoxText = typedArray.getString(R.styleable.FilterFieldEditText_cv_sb_text);
//        int filterBoxHint = typedArray.getResourceId(R.styleable.FilterFieldEditText_cv_sb_hint, R.drawable.ic_close_black_24dp);
        String filterBoxHint = typedArray.getString(R.styleable.FilterFieldEditText_cv_sb_hint);
        int filterBoxTextColor = typedArray.getColor(R.styleable.FilterFieldEditText_cv_sb_textColor, filterBox.getCurrentTextColor());
        boolean filterBoxTextBold = typedArray.getBoolean(R.styleable.FilterFieldEditText_cv_sb_textBold, false);
        int filterBoxFont = typedArray.getResourceId(R.styleable.FilterFieldEditText_cv_sb_font, 0);
        int filterBoxTextSize = (int) typedArray.getDimension(R.styleable.FilterFieldEditText_cv_sb_textSize, 0);
        int filterBoxPaddingLeft = (int) typedArray.getDimension(R.styleable.FilterFieldEditText_cv_sv_textPaddingLeft, dpToPx(0));
        int filterBoxPaddingRight = (int) typedArray.getDimension(R.styleable.FilterFieldEditText_cv_sv_textPaddingRight, dpToPx(0));


        filterBox.setText(filterBoxText);
        filterBox.setHint(filterBoxHint);
        if (filterBoxTextColor != 0) filterBox.setTextColor(filterBoxTextColor);
        if (filterBoxTextSize > 0) filterBox.setTextSize(filterBoxTextSize);
        if (filterBoxFont > 0)
            filterBox.setTypeface(ResourcesCompat.getFont(getContext(), filterBoxFont), filterBoxTextBold ? Typeface.BOLD : Typeface.NORMAL);
        if (filterBoxTextBold && filterBoxFont == 0)
            filterBox.setTypeface(filterBox.getTypeface(), Typeface.BOLD);
        filterBox.setPadding(filterBoxPaddingLeft, dpToPx(0),filterBoxPaddingRight, dpToPx(0));
//
        textStateBorderBackgoundDrawable = getBorderBackgoundDrawable(
                typedArray.getColor(R.styleable.FilterFieldEditText_cv_sb_borderTextStateColor, Color.TRANSPARENT),
                typedArray.getDimension(R.styleable.FilterFieldEditText_cv_sb_borderTextStateSize, dpToPx(0)),
                typedArray.getDimension(R.styleable.FilterFieldEditText_cv_sb_borderTextStateRadius, dpToPx(0)),
                typedArray.getDimension(R.styleable.FilterFieldEditText_cv_sb_borderTextStateDashWith, dpToPx(0)),
                typedArray.getDimension(R.styleable.FilterFieldEditText_cv_sb_borderTextStateDashGap, dpToPx(0))
        );

        emptyStateBorderBackgoundDrawable = getBorderBackgoundDrawable(
                typedArray.getColor(R.styleable.FilterFieldEditText_cv_sb_borderEmptyStateColor, Color.TRANSPARENT),
                typedArray.getDimension(R.styleable.FilterFieldEditText_cv_sb_borderEmptyStateSize, dpToPx(0)),
                typedArray.getDimension(R.styleable.FilterFieldEditText_cv_sb_borderEmptyStateRadius, dpToPx(0)),
                typedArray.getDimension(R.styleable.FilterFieldEditText_cv_sb_borderEmptyStateDashWith, dpToPx(0)),
                typedArray.getDimension(R.styleable.FilterFieldEditText_cv_sb_borderEmptyStateDashGap, dpToPx(0))
        );

        iconContainer.setBackground(emptyStateBorderBackgoundDrawable);
        searchButton.setVisibility(searchIconVisibility);
        clearIcon.setVisibility(clearIconVisibility);

        searchButton.setImageResource(searchIconSource);
        clearIcon.setImageResource(clearIconSource);

        ImageView.ScaleType[] types = ImageView.ScaleType.values();
        if (searchIconScaleType > -1) searchButton.setScaleType(types[searchIconScaleType]);
        if (searchIconScaleType > -1) clearIcon.setScaleType(types[clearIconScaleType]);

        typedArray.recycle();

    }

    private GradientDrawable getBorderBackgoundDrawable(
            int borderColor,
            float borderSize,
            float borderRadius,
            float borderDashWith,
            float borderDashGap
    ) {
        GradientDrawable gd = new GradientDrawable();
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setSize(dpToPx(48), dpToPx(48));
        gd.setColor(Color.TRANSPARENT); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(borderRadius);
        gd.setStroke((int) borderSize, borderColor, borderDashWith, borderDashGap);

        return gd;
    }

    private void initClickListener() {
        clearIcon.setOnClickListener(this);
        voiceIcon.setOnClickListener(this);
        backIcon.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        filterBox.addTextChangedListener(this);
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
    //</editor-fold>

    //<editor-fold desc="OverrideMethod">
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    //</editor-fold>

    //<editor-fold desc="ImplementMethod">
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (!charSequence.toString().isEmpty()) {
            clearIcon.setVisibility(View.VISIBLE);
            iconContainer.setBackground(textStateBorderBackgoundDrawable);
        } else {
            clearIcon.setVisibility(View.GONE);
            iconContainer.setBackground(emptyStateBorderBackgoundDrawable);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_clear) {
            clearIcon.setVisibility(View.GONE);
            iconContainer.setBackground(emptyStateBorderBackgoundDrawable);
            filterBox.setText("");
        } else if (view.getId() == R.id.btn_search) {
            if (!filterBox.getText().toString().trim().isEmpty())
                clearIcon.setVisibility(View.VISIBLE);
            filterBox.requestFocus();
            filterBox.setFocusableInTouchMode(true);
        } else if (view.getId() == R.id.btn_back) {

        } else if (view.getId() == R.id.btn_voice) {

        }
    }
    //</editor-fold>

    //<editor-fold desc="SetterAndGetterMethod">
    public ImageButton getClearIcon() {
        return clearIcon;
    }

    public void setClearIcon(ImageButton clearIcon) {
        this.clearIcon = clearIcon;
    }

    public ImageButton getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(ImageButton searchButton) {
        this.searchButton = searchButton;
    }

    public ImageButton getVoiceIcon() {
        return voiceIcon;
    }

    public void setVoiceIcon(ImageButton voiceIcon) {
        this.voiceIcon = voiceIcon;
    }

    public ImageButton getBackIcon() {
        return backIcon;
    }

    public void setBackIcon(ImageButton backIcon) {
        this.backIcon = backIcon;
    }

    public EditText getFilterBox() {
        return filterBox;
    }

    public void setFilterBox(EditText filterBox) {
        this.filterBox = filterBox;
    }

    public ConstraintLayout getIconContainer() {
        return iconContainer;
    }

    public void setIconContainer(ConstraintLayout iconContainer) {
        this.iconContainer = iconContainer;
    }

    public void setText(String text) {
        if (filterBox != null)
            filterBox.setText(text);
    }

    public String getText() {
        if (filterBox != null)
            return filterBox.getText().toString();
        else return null;
    }
    //</editor-fold>


    //<editor-fold desc="MainMethod">
    //</editor-fold>
    //<editor-fold desc="CreateMethod">
//</editor-fold>
    //<editor-fold desc="OverrideMethod">
    //</editor-fold>
    //<editor-fold desc="ImplementMethod">
    //</editor-fold>
    //<editor-fold desc="SetterAndGetterMethod">
    //</editor-fold>
    //<editor-fold desc="InnerClass">
    //</editor-fold>

}
