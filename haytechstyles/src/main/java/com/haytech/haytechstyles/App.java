package com.haytech.haytechstyles;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class App extends Application {

    private static Lifecycle lifecycle;

    private Context mcontext;

    private static App mInstance;

    public static synchronized App getInstance() {
        return mInstance;
    }

    public void setmInstance(App mInstance) {
        App.mInstance = mInstance;
    }


    public Context getMcontext() {
        return mcontext;
    }

    public static App get(Context context) {
        return ((App) context.getApplicationContext());
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = getApplicationContext();
        setmInstance(this);


//        lifecycle = ProcessLifecycleOwner.get().getLifecycle();
//        lifecycle.addObserver(new Observer());
    }


    class Observer implements LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void onCreate() {
            Log.d("Observer111", ": onCreate");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        void onStop() {
            Log.d("Observer111", ": onStop");
        }

    }




}