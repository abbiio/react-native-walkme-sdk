package com.walkme;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerListener;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.UIManagerModule;

import java.util.concurrent.atomic.AtomicBoolean;

import abbi.io.abbisdk.ABBI;

@ReactModule(name = RNWalkMeSDKUiManager.NAME)
public class RNWalkMeSDKUiManager extends ReactContextBaseJavaModule implements UIManagerListener, ABBI.WMExternalUiListener{

    public static final String NAME = "RNWalkMeSDKUiManager";

    private final ReactApplicationContext mReactContext;
    private final Handler mUiThreadHandler;
    private final RNWalkMeSdkWorkerHandler mWorkerHandler;
    private AtomicBoolean mIsViewChanged = new AtomicBoolean(false);

    private ABBI.WMExternalUiDelegate mDelegate;

    public RNWalkMeSDKUiManager(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mReactContext = reactContext;
        this.mUiThreadHandler = new Handler(Looper.getMainLooper());
        mWorkerHandler = new RNWalkMeSdkWorkerHandler("RNWalkMeSDKUiManager");
    }

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void startObserving() {
        final UIManagerModule uiManagerModule = mReactContext.getNativeModule(UIManagerModule.class);
        mUiThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (uiManagerModule != null) {
                    uiManagerModule.addUIManagerEventListener(RNWalkMeSDKUiManager.this);
                }
            }
        });
    }

    @ReactMethod
    public void stopObserving() {
        mWorkerHandler.quit();
        final UIManagerModule uiManagerModule = mReactContext.getNativeModule(UIManagerModule.class);
        mUiThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (uiManagerModule != null) {
                    uiManagerModule.removeUIManagerEventListener(RNWalkMeSDKUiManager.this);
                }
            }
        });
    }

    @Override
    public void didDispatchMountItems(UIManager uiManager) {
    }

    public void didMountItems(UIManager uiManager) {
    }

    @Override
    public void didScheduleMountItems(UIManager uiManager) {
    }

    @Override
    public void willDispatchViewUpdates(UIManager uiManager) {
        if (!mIsViewChanged.get()) {
            mWorkerHandler.runOnWorkerThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mIsViewChanged.set(false);
                        if (mDelegate != null) {
                            mDelegate.onExternalViewChanged();
                        }
                    } catch (Exception e) {
                        Log.e("WalkMeSDK", "Failed to call to onExternalViewChanged: " + e.getMessage());
                    }
                }
            }, 1000);
        }
        mIsViewChanged.set(true);
    }

    public void willMountItems(UIManager uiManager) {
    }

    @Override
    public void setExternalDelegate(ABBI.WMExternalUiDelegate delegate) {
        mDelegate = delegate;
    }
}