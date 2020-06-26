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
import com.haytech.haytechstyles.selector.MyTextChecker;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

@Keep
public class ExpertProfile extends ConstraintLayout implements MyTextChecker.CheckerListener {

    private ExpandableLayout expandableLayout;
    private MyTextChecker textChecker;
    private ConstraintLayout parentLayout;
    private TextView name;
    private TextView famil;

    private OnReadMoreClickedListener clickedListener;
    private boolean isChecked = false;

    public ExpertProfile(Context context) {
        super(context);
        initView(context, null);
    }

    public ExpertProfile(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ExpertProfile(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

//    public MotherWeekInfoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        initView(context, attrs);
//    }

    private void initView(@NonNull final Context context, AttributeSet attrs) {

        LayoutInflater.from(context).inflate(R.layout.profile_expert, this);
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
        name = findViewById(R.id.name);
        famil = findViewById(R.id.famil);
        textChecker = findViewById(R.id.myTextChecker);
        textChecker.setListener(this);

        readMore.setOnClickListener(v1 -> {
            if (clickedListener != null) {
                clickedListener.onReadMoreClicked(v1);
            }
        });
        readMore.setTextSize(COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_normal_btn_12sp));
    }

    public TextView getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name.setText(name);
    }

    public TextView getFamil() {
        return famil;
    }

    public void setFamil(String famil) {
        this.famil.setText(famil);
    }

    public ExpandableLayout getExpandableLayout() {
        return expandableLayout;
    }

    public void setExpandableLayout(ExpandableLayout expandableLayout) {
        this.expandableLayout = expandableLayout;
    }

    public MyTextChecker getTextChecker() {
        return textChecker;
    }

    public void setTextChecker(MyTextChecker textChecker) {
        this.textChecker = textChecker;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public void setFamil(TextView famil) {
        this.famil = famil;
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
            parentLayout.setBackground(getResources().getDrawable(R.drawable.aaaa));
        } else {
            parentLayout.setBackground(getResources().getDrawable(R.drawable.bbb));
        }
    }


    @Override
    public void check(boolean b) {
        expandableLayout.toggle();
        if (expandableLayout.getExpansion() == 0) {
            parentLayout.setBackground(getResources().getDrawable(R.drawable.aaaa));
        } else {
            parentLayout.setBackground(getResources().getDrawable(R.drawable.bbb));
        }
    }
    @Override
    public boolean getChecked() {
        return false;
    }
}


