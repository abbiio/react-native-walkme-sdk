package com.walkme;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.annotation.Nullable;

public class RNWalkMeSdkWorkerHandler {

    private final String mThreadName;
    private @Nullable HandlerThread mWorkerHandlerThread;
    private @Nullable Handler mWorkerThread;
    private boolean mShouldReduceTasks = false;
    public RNWalkMeSdkWorkerHandler(String threadName) {
        this.mThreadName = threadName;
        init();
    }

    private void init() {
        try {
            mWorkerHandlerThread = new HandlerThread(mThreadName);
            mWorkerHandlerThread.start();
            mWorkerThread = new Handler(mWorkerHandlerThread.getLooper());
        } catch (Exception e) {
        }
    }

    public void runOnWorkerThread(Runnable runnable) {
        try {
            if (mWorkerHandlerThread != null && mWorkerThread != null) {
                if (!mWorkerHandlerThread.isAlive()) {
                    init();
                }
                if (mShouldReduceTasks && mWorkerThread.hasMessages(999)) {
                    return;
                }

                if (mWorkerHandlerThread.isAlive()) {
                    mWorkerThread.post(runnable);
                    if (mShouldReduceTasks) {
                        mWorkerThread.sendEmptyMessage(999);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void runOnWorkerThread(Runnable runnable, int delay) {
        if (mWorkerHandlerThread != null && mWorkerThread != null) {
            if (!mWorkerHandlerThread.isAlive()) {
                init();
            }
            if (mShouldReduceTasks && mWorkerThread.hasMessages(999)) {
                return;
            }
            mWorkerThread.postDelayed(runnable, delay);
            if (mShouldReduceTasks) {
                mWorkerThread.sendEmptyMessage(999);
            }
        }
    }

    public void quit() {
        try {
            if (mWorkerHandlerThread != null) {
                if (Build.VERSION.SDK_INT > 17) {
                    mWorkerHandlerThread.quitSafely();
                } else {
                    mWorkerHandlerThread.quit();
                }
            }
        } catch (Exception e) {
        }
    }

    @Nullable
    public Handler getHandler() {
        return mWorkerThread;
    }

}
