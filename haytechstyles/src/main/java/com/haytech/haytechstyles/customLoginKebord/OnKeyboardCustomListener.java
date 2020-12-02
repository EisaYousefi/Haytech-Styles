package com.haytech.haytechstyles.customLoginKebord;

import android.view.View;

public interface OnKeyboardCustomListener {

    interface CreateNewPass {
        void validPass();
    }

    interface LoginApp {
        void validPass();
        void counterErrorPass();
    }
    interface  ChangePass{
        void validPass();
    }

    interface OnClickKey{
        void tvFingerClick(View view);
        void imgFingerClick(View view);
        interface BtnOnClick{
            void btnDismissDialogClick();
        }
    }

}