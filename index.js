
import { NativeModules } from 'react-native';

const { RNWalkMeSdk } = NativeModules;

const WalkMeSDK = {
    start: function(key, secret) {
        RNWalkMeSdk.start(key, secret);
    },
    restart: function() {
        RNWalkMeSdk.restart();
    },
    stop: function() {
        RNWalkMeSdk.stop();
    },
    sendGoal: function(goalName, properties) {
        RNWalkMeSdk.sendGoal(goalName, properties);
    },
    setUserAttribute: function(key, value) {
        RNWalkMeSdk.setUserAttribute(key, value);
    },
    setUserAttributes: function(attributes) {
        RNWalkMeSdk.setUserAttributes(attributes);
    },
    setPrivateUserAttribute: function(key, value) {
        RNWalkMeSdk.setPrivateUserAttribute(key, value);
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
    }
};

export default WalkMeSDK;
