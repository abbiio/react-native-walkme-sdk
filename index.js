
import { NativeModules, NativeEventEmitter, Platform } from 'react-native';

const { RNWalkMeSdk } = NativeModules;

const wmCampaignInfoEventEmitter = new NativeEventEmitter(RNWalkMeSdk);

const WalkMeSDK = {
    start: function(key, secret) {
        RNWalkMeSdk.start(key, secret);
    },
    startWithSelfHosted: function(key, secret, selfHostedUrl) {
        RNWalkMeSdk.startWithSelfHosted(key, secret, selfHostedUrl);
    },
    startWithUiManager: function(key, secret) {
        RNWalkMeSdk.startWithUiManager(key, secret);
    },
    startWithNoCallback: function(key, secret) {
        RNWalkMeSdk.startWithNoCallback(key, secret);
    },
    campaignEventEmitter: wmCampaignInfoEventEmitter,
    restart: function() {
        RNWalkMeSdk.restart();
    },
    stop: function() {
        RNWalkMeSdk.stop();
    },
    sendGoal: function(goalName, properties) {
        console.warn("sendGoal method is deprecated and will be removed in future versions. Please use sendTrackedEvent instead.");
        RNWalkMeSdk.sendGoal(goalName, properties);
    },
    sendTrackedEvent: function(eventName, properties) {
        RNWalkMeSdk.sendTrackedEvent(eventName, properties);
    },
    setUserAttribute: function(key, value) {
        if (Platform.OS === 'ios') {
            RNWalkMeSdk.setUserAttribute(key, value);
        }
        else {
          console.log("WalkMeSDK: Unsupported - Use setUserAttributes instead");
        }
    },
    setUserAttributes: function(attributes) {
        RNWalkMeSdk.setUserAttributes(attributes);
    },
    setPrivateUserAttribute: function(key, value) {
        if (Platform.OS === 'ios') {
            RNWalkMeSdk.setPrivateUserAttribute(key, value);
        }
        else {
          console.log("WalkMeSDK: Unsupported - Use setPrivateUserAttributes instead");
        }
    },
    setPrivateUserAttributes: function (attributes) {
        RNWalkMeSdk.setPrivateUserAttributes(attributes);
    },
    clearPrivateUserAttributes: function() {
        RNWalkMeSdk.clearPrivateUserAttributes();
    },
    setFlag: function(num) {
        RNWalkMeSdk.setFlag(num);
    },
    trigger: function (trigger, deepLink) {
        RNWalkMeSdk.trigger(trigger, deepLink);
    },
    triggerWithoutDeepLink: function (trigger) {
        RNWalkMeSdk.triggerWithoutDeepLink(trigger);
    },
    setUserID: function(userID) {
        RNWalkMeSdk.setUserID(userID);
    },
    setEventsFilter: function(events) {
        RNWalkMeSdk.setEventsFilter(events);
    },
    setScreenID: function(screenID) {
        RNWalkMeSdk.setScreenID(screenID);
    },
    setLanguage: function(language) {
        RNWalkMeSdk.setLanguage(language);
    },
    registerEventListener: function() {
        RNWalkMeSdk.registerEventListener();
    }
};

export default WalkMeSDK;
