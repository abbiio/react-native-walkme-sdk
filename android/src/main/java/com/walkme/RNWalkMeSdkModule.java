
package com.walkme;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

import java.util.ArrayList;

import abbi.io.abbisdk.ABBI;

public class RNWalkMeSdkModule extends ReactContextBaseJavaModule {

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
      ABBI.start(key, secret, this.getCurrentActivity());
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
}
