package com.haytech;

import android.app.Application;
import android.content.Context;

import com.haytech.haytechstyles.R;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class HytechBaseApplication extends Application {

    private static HytechBaseApplication instance;
    private Context context;

    public static synchronized HytechBaseApplication getInstance() {
        return instance;
    }

    public static HytechBaseApplication getContext(Context context) {
        return ((HytechBaseApplication) context.getApplicationContext());
    }


    public void setInstance(HytechBaseApplication mInstance) {
        HytechBaseApplication.instance = mInstance;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        setInstance(this);
        calligraphy();
    }


    private void calligraphy() {
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                    .setDefaultFontPath("fonts/dana_fa_num_regular.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()
                ))
                .build());
    }


}
