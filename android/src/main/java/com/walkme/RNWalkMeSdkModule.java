
package com.walkme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.app.Application;
import android.content.Context;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;

import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import abbi.io.abbisdk.ABBI;
import abbi.io.abbisdk.WMStartOptions;
import abbi.io.abbisdk.info.WMCampaignInfo;

public class RNWalkMeSdkModule extends ReactContextBaseJavaModule implements ABBI.WMCampaignInfoListener {
  public static final String wmCampaignInfoEventDismissed   = "wmCampaignInfoEventDismissed";
  public static final String wmCampaignInfoEventWillShow    = "wmCampaignInfoEventWillShow";
  public static final String wmCampaignInfoEventAction      = "wmCampaignInfoEventAction";

  private final ReactApplicationContext mReactContext;
  private RNWalkMeSDKUiManager mUiManager;


  public RNWalkMeSdkModule(ReactApplicationContext reactContext) {
    super(reactContext);
    mReactContext = reactContext;
  }

  @NonNull
  @Override
  public String getName() {
    return "RNWalkMeSdk";
  }

  @ReactMethod
  public void start(String key, String secret) {
      try {
        WMStartOptions options = createStartOptions(key, secret);
        if (options != null) {
            options.setCampaignInfoListener(this);
            ABBI.start(options);
        }
      } catch (Exception e) {
        Log.e("WalkMeSDK", "failed to start SDK " + e.getMessage());
      }
  }

  @ReactMethod
  public void startWithUiManager(String key, String secret) {
      try {
        WMStartOptions options = createStartOptions(key, secret);
        if (options != null) {
            options.setCampaignInfoListener(this);
            // in case we need to listen to UI changes in react native, for example relevant for stack navigation
            try {
              mUiManager = mReactContext.getNativeModule(RNWalkMeSDKUiManager.class);
              if (mUiManager != null) {
                mUiManager.startObserving();
              }
              options.setExternalUiListener(mUiManager);
            } catch (Exception e) {
              Log.e("WalkMeSDK", "failed to start observing UI manager " + e.getMessage());
            }
            ABBI.start(options);
        }
      } catch (Exception e) {
        Log.e("WalkMeSDK", "failed to start SDK with UI Manager " + e.getMessage());
      }
  }

  @ReactMethod
  public void startWithSelfHosted(String key, String secret, String selfHostedUrl) {
      try {
        WMStartOptions options = createStartOptions(key, secret);
        if (options != null) {
            options.setCampaignInfoListener(this);
            options.setSelfHostedURL(selfHostedUrl);
            ABBI.start(options);
        }
      } catch (Exception e) {
        Log.e("WalkMeSDK", "failed to start SDK with self hosted " + e.getMessage());
      }
  }

  @ReactMethod
  public void startWithNoCallback(String key, String secret) {
      try {
        WMStartOptions options = createStartOptions(key, secret);

        if (options != null) {
            options.setCampaignInfoListener(this);
            options.setNoCallback(true);
            ABBI.start(options);
        }
      } catch (Exception e) {
        Log.e("WalkMeSDK", "failed to start SDK with no callback flag true  " + e.getMessage());
      }
  }

  @Nullable
  private WMStartOptions createStartOptions(String appKey, String secret) {
      WMStartOptions options = null;

      Context appContext = getReactApplicationContext().getApplicationContext();
      if (appContext instanceof Application) {
          options = new WMStartOptions(appKey, secret, (Application) appContext);
      } else if (this.getReactApplicationContext().getCurrentActivity() != null) {
          options = new WMStartOptions(appKey, secret, this.getReactApplicationContext().getCurrentActivity());
      }

      return options;
  }

  @ReactMethod
  public void registerEventListener() {
    ABBI.setCampaignInfoListener(this);
  }

  @ReactMethod
  public void restart() {
    ABBI.restart();
  }

  @ReactMethod
  public void stop() {
    if (mUiManager != null) {
      mUiManager.stopObserving();
    }
    ABBI.stop();
  }

  @ReactMethod
  public void sendGoal(String goalName, ReadableMap properties) {
    ABBI.sendGoal(goalName, properties != null ? properties.toHashMap() : null);
  }

  @ReactMethod
  public void sendTrackedEvent(String goalName, ReadableMap properties) {
    ABBI.sendTrackedEvent(goalName, properties != null ? properties.toHashMap() : null);
  }

  @ReactMethod
  public void setUserAttributes(ReadableMap object) {
    if (object != null) {
      ABBI.setUserAttributes(object.toHashMap());
    }
  }

  @ReactMethod
  public void setPrivateUserAttributes(ReadableMap object) {
    if (object != null) {
      ABBI.setPrivateUserAttributes(object.toHashMap());
    }
  }

  @ReactMethod
  public void clearPrivateUserAttributes() {
    ABBI.clearPrivateUserAttributes();
  }

  @ReactMethod
  public void setFlag(int flag) {
    ABBI.setFlag(flag);
  }

  @ReactMethod
  public void trigger(String trigger, String deeplink) {
    ABBI.trigger(trigger, deeplink);
  }

  @ReactMethod
  public void triggerWithoutDeepLink(String trigger) {
    ABBI.trigger(trigger);
  }

  @ReactMethod
  public void setUserID(String userId) {
    ABBI.setUserId(userId);
  }

  @ReactMethod
  public void setEventsFilter(ReadableArray events) {
    if (events != null) {
      ArrayList<String> eventsAsString = new ArrayList<>();
      ArrayList<Object> objectArrayList = events.toArrayList();

      for (int i = 0; i < objectArrayList.size(); i++) {
        Object value = objectArrayList.get(i);
        if (value instanceof String) {
          eventsAsString.add((String) value);
        }
      }
      ABBI.setEventsFilter(eventsAsString);
    }
  }

  @ReactMethod
  public void setScreenID(String screenID) {
    ABBI.setScreenID(screenID);
  }

  @ReactMethod
  public void setLanguage(String language) {
    ABBI.setLanguage(language);
  }

  private void sendEvent(ReactContext reactContext,
                         String eventName,
                         @Nullable WritableMap params) {
    reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit(eventName, params);
  }

  public void onCampaignDismissed(WMCampaignInfo wmCampaignInfo) {
    WritableMap params = Arguments.createMap();

    if (wmCampaignInfo.getCampaignId() != null) {
      params.putString("campaign_id", wmCampaignInfo.getCampaignId());
    }

    if (wmCampaignInfo.getCampaignCta() != null) {
      params.putString("cta", wmCampaignInfo.getCampaignCta());
    }

    if (wmCampaignInfo.getCampaignCtaId() != null) {
      params.putString("cta_id", wmCampaignInfo.getCampaignCtaId());
    }

    if (wmCampaignInfo.getCampaignData() != null && !wmCampaignInfo.getCampaignData().isEmpty()) {
      WritableMap data = Arguments.createMap();
      for (Map.Entry<String, Object> entry : wmCampaignInfo.getCampaignData().entrySet()) {
        data.putString(entry.getKey(), entry.getValue().toString());
      }

      params.putMap("data", data);
    }

    sendEvent(this.getReactApplicationContext(), wmCampaignInfoEventDismissed, params);
  }

  public void onCampaignAction(WMCampaignInfo wmCampaignInfo, String actionType, String[] args, ABBI.WMCampaignActionListener wmCampaignActionListener) {
    WritableMap params = Arguments.createMap();

    if (actionType != null) {
      params.putString("action_type", actionType);
    }

    if (args != null && args.length > 0) {
      WritableArray argsArray = Arguments.createArray();
      for (String s: args) {
        argsArray.pushString(s);
      }

      params.putArray("args", argsArray);
    }

    sendEvent(this.getReactApplicationContext(), wmCampaignInfoEventAction, params);
  }

  public void onCampaignPresented(WMCampaignInfo wmCampaignInfo) {
    WritableMap params = Arguments.createMap();

    if (wmCampaignInfo.getCampaignId() != null) {
      params.putString("campaign_id", wmCampaignInfo.getCampaignId());
    }

    if (wmCampaignInfo.getCampaignData() != null && !wmCampaignInfo.getCampaignData().isEmpty()) {
      WritableMap data = Arguments.createMap();
      for (Map.Entry<String, Object> entry : wmCampaignInfo.getCampaignData().entrySet()) {
        data.putString(entry.getKey(), entry.getValue().toString());
      }

      params.putMap("data", data);
    }

    sendEvent(this.getReactApplicationContext(), wmCampaignInfoEventWillShow, params);
  }
    
  @ReactMethod
  public void addListener(String eventName) {
      // Keep: Required for RN built-in Event Emitter Calls.
  }

  @ReactMethod
  public void removeListeners(Integer count) {
        // Keep: Required for RN built-in Event Emitter Calls.
  }

}
