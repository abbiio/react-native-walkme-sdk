
package com.walkme;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import abbi.io.abbisdk.ABBI;

public class RNWalkMeSdkModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNWalkMeSdkModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNWalkMeSdk";
  }

  @ReactMethod
  public void start(String key, String secret) {
    ABBI.start(key, secret, reactContext);
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
    ABBI.sendGoal(goalName, properties);
  }

  // WARNING - DO NOT USE - Use setUserAttributes instead
  @ReactMethod
  public void setUserAttribute(String key, ReadableMap value) {}

  @ReactMethod
  public void setUserAttributes(ReadableMap object) {
    if (object != null) {
      ABBI.setUserAttributes(object.toHashMap());
    }
  }

  // WARNING - DO NOT USE - Use setPrivateUserAttributes instead
  @ReactMethod
  public void setPrivateUserAttribute(String key, ReadableMap value) {}

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
    ABBI.setEventsFilter(events);
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
