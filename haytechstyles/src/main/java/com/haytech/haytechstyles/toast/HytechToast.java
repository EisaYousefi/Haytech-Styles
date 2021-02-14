package com.haytech.haytechstyles.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.haytech.haytechstyles.R;

public class HytechToast extends Toast {

    public HytechToast(Context context) {
        super(context);
    }

    public static Toast makeText(Context context, CharSequence message, int duration, HytechTypeToast type, boolean icon) {
        Toast toast = new Toast(context);
        toast.setDuration(duration);
        View view = LayoutInflater.from(context).inflate(R.layout.toast_hytech_layout, null, false);
        TextView toastMessage = view.findViewById(R.id.toast_text);
        ConstraintLayout layout = view.findViewById(R.id.toast_type);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        ImageView imgToastIcon = view.findViewById(R.id.toast_icon);
        ImageView iconTop = view.findViewById(R.id.imageView4);
        toastMessage.setText(message);
        if (icon)
            iconTop.setVisibility(View.VISIBLE);
        else iconTop.setVisibility(View.GONE);
        switch (type) {
            case SUCCESS:
                layout.setBackgroundResource(R.drawable.toast_success_shape);
                imgToastIcon.setImageResource(R.drawable.toast_check_black_24dp);
                break;
            case WARNING:
                layout.setBackgroundResource(R.drawable.toast_warning_shape);
                imgToastIcon.setImageResource(R.drawable.toast_pan_tool_black_24dp);
                break;
            case ERROR:
                layout.setBackgroundResource(R.drawable.toast_error_shape);
                imgToastIcon.setImageResource(R.drawable.toast_clear_black_24dp);
                break;
            case INFO:
                layout.setBackgroundResource(R.drawable.toast_info_shape);
                imgToastIcon.setImageResource(R.drawable.toast_info_outline_black_24dp);
                break;
            case DEFAULT:
                layout.setBackgroundResource(R.drawable.toast_default_shape);
                imgToastIcon.setVisibility(View.GONE);
                break;
            case CONFUSING:
                layout.setBackgroundResource(R.drawable.toast_confusing_shape);
                imgToastIcon.setImageResource(R.drawable.toast_refresh_black_24dp);
                break;
            default:
                layout.setBackgroundResource(R.drawable.toast_default_shape);
                imgToastIcon.setVisibility(View.GONE);
                break;
        }
        toast.setView(view);
        return toast;
    }

}
