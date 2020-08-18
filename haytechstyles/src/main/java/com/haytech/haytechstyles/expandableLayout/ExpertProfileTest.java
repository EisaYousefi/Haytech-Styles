package com.haytech.haytechstyles.expandableLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;


import com.haytech.haytechstyles.R;
import com.haytech.haytechstyles.selector.RadioButtonField;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

@Keep
public class ExpertProfileTest extends ConstraintLayout implements RadioButtonField.CheckerListener {

    private ExpandableLayout expandableLayout;
    private RadioButtonField textChecker;
    private ConstraintLayout parentLayout;
    private TextView expertName;
    private TextView InquiryٔNumber;
    private TextView date ;

    private OnReadMoreClickedListener clickedListener;
    private boolean isChecked = false;

    public ExpertProfileTest(Context context) {
        super(context);
        initView(context, null);
    }

    public ExpertProfileTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ExpertProfileTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

//    public MotherWeekInfoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        initView(context, attrs);
//    }

    private void initView(@NonNull final Context context, AttributeSet attrs) {

        LayoutInflater.from(context).inflate(R.layout.profile_expert_test, this);
        ViewCompat.setLayoutDirection(this, ViewCompat.LAYOUT_DIRECTION_LTR);
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        //  setBackground(getResources().getDrawable(R.drawable.expandable_layout_bkg_select_1));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) getLayoutParams();
        params.gravity = Gravity.TOP;
        setLayoutParams(params);


        expandableLayout = findViewById(R.id.expandable_layout);

        TextView readMore = findViewById(R.id.tv_read_more);
        parentLayout = findViewById(R.id.constraint_parent_layout);
        expertName = findViewById(R.id.tv_expert_name);
        InquiryٔNumber = findViewById(R.id.tv_inquery_number);
        date = findViewById(R.id.tv_date);
        textChecker = findViewById(R.id.myTextChecker);
        textChecker.setListener(this);

        readMore.setOnClickListener(v1 -> {
            if (clickedListener != null) {
                clickedListener.onReadMoreClicked(v1);
            }
        });
        readMore.setTextSize(COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_normal_btn_12sp));
    }

    public TextView getExpertName() {
        return expertName;
    }

    public void setExpertName(@NonNull String expertName) {
        this.expertName.setText(expertName);
    }

    public TextView getInquiryٔNumber() {
        return InquiryٔNumber;
    }

    public void setInquiryٔNumber(String inquiryٔNumber) {
        this.InquiryٔNumber.setText(inquiryٔNumber);
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date.setText(date);
    }

    public ExpandableLayout getExpandableLayout() {
        return expandableLayout;
    }

    public void setExpandableLayout(ExpandableLayout expandableLayout) {
        this.expandableLayout = expandableLayout;
    }

    public RadioButtonField getTextChecker() {
        return textChecker;
    }

    public void setTextChecker(RadioButtonField textChecker) {
        this.textChecker = textChecker;
    }

    public void setName(TextView name) {
        this.expertName = name;
    }

    public void setFamil(TextView famil) {
        this.InquiryٔNumber = famil;
    }

    public OnReadMoreClickedListener getClickedListener() {
        return clickedListener;
    }

    public void setClickedListener(OnReadMoreClickedListener clickedListener) {
        this.clickedListener = clickedListener;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public ConstraintLayout getParentLayout() {
        return parentLayout;
    }

    public void setParentLayout(ConstraintLayout parentLayout) {
        this.parentLayout = parentLayout;
    }

    public void setCheckBox() {
       // expandableLayout.toggle();
        if (expandableLayout.getExpansion() == 0) {
            parentLayout.setBackground(getResources().getDrawable(R.drawable.back_card_view_select_archive));
        } else {
            parentLayout.setBackground(getResources().getDrawable(R.drawable.back_card_view_select_archive));
        }
    }


    @Override
    public void check(boolean b) {
        expandableLayout.toggle();
        if (expandableLayout.getExpansion() == 0) {
            parentLayout.setBackground(getResources().getDrawable(R.drawable.back_card_view_un_select_archive));
        } else {
            parentLayout.setBackground(getResources().getDrawable(R.drawable.back_card_view_un_select_archive));
        }
    }
    @Override
    public void getChecked() {
    }
}


