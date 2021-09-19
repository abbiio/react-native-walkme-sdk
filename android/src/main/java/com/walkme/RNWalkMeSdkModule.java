
package com.walkme;

import android.support.annotation.Nullable;
import android.util.Log;

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

  public RNWalkMeSdkModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "RNWalkMeSdk";
  }

  @ReactMethod
  public void start(String key, String secret) {
    if (this.getCurrentActivity() != null) {
      WMStartOptions options = new WMStartOptions(key, secret, this.getCurrentActivity());
      options.setCampaignInfoListener(this);
      ABBI.start(options);
    }
    else {
      Log.d("WalkMeSDK","Activity is null");
    }
  }

  @ReactMethod
  public void restart() {
    ABBI.restart();
  }

  @ReactMethod
  public void stop() {
    ABBI.stop();
  }

  @ReactMethod
  public void sendGoal(String goalName, ReadableMap properties) {
    ABBI.sendGoal(goalName, properties != null ? properties.toHashMap() : null);
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

  @Override
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

  @Override
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
}
