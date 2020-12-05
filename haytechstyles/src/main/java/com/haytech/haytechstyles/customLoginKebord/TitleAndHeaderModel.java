package com.haytech.haytechstyles.customLoginKebord;

import android.content.Context;

import com.haytech.haytechstyles.R;

public class TitleAndHeaderModel {

    private String tvLabel ;
    private String tvHeaderTitle ;

    private String tvLabelRepeatNewPass2;
    private String tvHeaderRepeatNewPass2;

    private String tvLabelCreateNewForPassChange3;
    private String tvHeaderCreateNewForPassChange3;
    private String tvLabelRepeatNewForPassChange3;
    private String tvHeaderRepeatNewForPassChange3;
    private Context context;

    public TitleAndHeaderModel(Context context) {
        this.context = context;
    }

    public String getTvLabel() {
        return tvLabel;
    }

    public void setTvLabel(String tvLabel) {
        this.tvLabel = tvLabel;
    }

    public String getTvHeaderTitle() {
        return tvHeaderTitle;
    }

    public void setTvHeaderTitle(String tvHeaderTitle) {
        this.tvHeaderTitle = tvHeaderTitle;
    }

    public String getTvLabelRepeatNewPass2() {
        return tvLabelRepeatNewPass2;
    }

    public void setTvLabelRepeatNewPass2(String tvHeaderNewPass2) {
        this.tvLabelRepeatNewPass2 = tvHeaderNewPass2;
    }

    public String getTvHeaderRepeatNewPass2() {
        return tvHeaderRepeatNewPass2;
    }

    public void setTvHeaderRepeatNewPass2(String tvHeaderRepeatNewPass2) {
        this.tvHeaderRepeatNewPass2 = tvHeaderRepeatNewPass2;
    }



    public String getTvLabelCreateNewForPassChange3() {
        return tvLabelCreateNewForPassChange3;
    }

    public void setTvLabelCreateNewForPassChange3(String tvLabelCreateNewForPassChange3) {
        this.tvLabelCreateNewForPassChange3 = tvLabelCreateNewForPassChange3;
    }

    public String getTvHeaderCreateNewForPassChange3() {
        return tvHeaderCreateNewForPassChange3;
    }

    public void setTvHeaderCreateNewForPassChange3(String tvHeaderCreateNewForPassChange3) {
        this.tvHeaderCreateNewForPassChange3 = tvHeaderCreateNewForPassChange3;
    }

    public String getTvLabelRepeatNewForPassChange3() {
        return tvLabelRepeatNewForPassChange3;
    }

    public void setTvLabelRepeatNewForPassChange3(String tvLabelRepeatNewForPassChange3) {
        this.tvLabelRepeatNewForPassChange3 = tvLabelRepeatNewForPassChange3;
    }

    public String getTvHeaderRepeatNewForPassChange3() {
        return tvHeaderRepeatNewForPassChange3;
    }

    public void setTvHeaderRepeatNewForPassChange3(String tvHeaderRepeatNewForPassChange3) {
        this.tvHeaderRepeatNewForPassChange3 = tvHeaderRepeatNewForPassChange3;
    }

    public void  setDataTitleAndHeader(){

        setTvLabelRepeatNewPass2(context.getResources().getString(R.string.repeat_pass));
        setTvHeaderRepeatNewPass2(context.getResources().getString(R.string.accept_password));

        setTvLabelCreateNewForPassChange3(context.getResources().getString(R.string.new_pass));
        setTvHeaderCreateNewForPassChange3(context.getResources().getString(R.string.new_pass));
        setTvLabelRepeatNewForPassChange3(context.getResources().getString(R.string.repeat_pass));
        setTvHeaderRepeatNewForPassChange3(context.getResources().getString(R.string.accept_password));
    }
}
