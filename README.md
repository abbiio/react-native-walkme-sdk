
# react-native-walkme-sdk

## Getting started

`$ npm install react-native-walkme-sdk --save`

### Mostly automatic installation

`$ react-native link react-native-walkme-sdk`

### Manual installation

#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-walkme-sdk` and add `RNWalkMeSdk.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNWalkMeSdk.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNWalkMeSdkPackage;` to the imports at the top of the file
  - Add `new RNWalkMeSdkPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-walkme-sdk'
  	project(':react-native-walkme-sdk').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-walkme-sdk/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-walkme-sdk')
  	```
## Usage
```javascript
import WalkMeSDK from 'react-native-walkme-sdk';
...
/**
 * Starts WalkMe SDK.
 *
 * @param appId The Application Id provided by WalkMe
 * @param appSecretKey The Application Secret key provided by WalkMe
 *
 * To get your Application Id and Application Secret key, login to WalkMe console at https://console.mobile.walkme.com
 * and click the settings icon near your Application Name. 
 */
WalkMeSDK.start(appId, appSecretKey)

/**
 * Starts WalkMe SDK - FOR SELF-HOSTED APPS ONLY!
 *
 * @param appId The Application Id provided by WalkMe
 * @param appSecretKey The Application Secret key provided by WalkMe
 * @param selfHostedUrl The self hosted url
 *
 * To get your Application Id and Application Secret key, login to WalkMe console at https://console.mobile.walkme.com
 * and click the settings icon near your Application Name. 
 */
WalkMeSDK.startWithSelfHosted(appId, appSecretKey, selfHostedUrl)

/**
 * Restarts WalkMe SDK.
 *
 * This method can be called only after WalkMe start was called first.
 * Restart WalkMe SDK is allowed if old session is at least 1 minute old
 */
WalkMeSDK.restart()

/**
 * Stop WalkMe SDK.
 *
 * This method can be called only after WalkMe start was called first.
 * Stop WalkMe SDK is allowed if old session is at least 1 minute old
 */
WalkMeSDK.stop()


/**
 * Sends a Goal to WalkMe's Backend.
 * A Goal is a user action that can be used to target your users.
 *
 * Usage Example :@
 *
 * WalkMeSDK.sendGoal("goalName")
 * WalkMeSDK.sendGoal("goalName", {"x": 3, "y": "yes", "z": false})
 *
 * @param goalName the Goal name.
 * @param properties the Goal properties, key-value structured (if any).
 */
WalkMeSDK.sendGoal(goalName, properties)

/**
 * Sends a Tracked Event to WalkMe's Backend.
 * A Tracked Event is a user action that can be used to target your users.
 *
 * Usage Example :@
 *
 * WalkMeSDK.sendTrackedEvent("trackedEvent")
 * WalkMeSDK.sendTrackedEvent("trackedEvent", {"x": 3, "y": "yes", "z": false})
 *
 * @param trackedEventName the Tracked Event name.
 * @param properties the Tracked Event properties, key-value structured (if any).
 */
WalkMeSDK.sendTrackedEvent(trackedEventName, properties)


/**
 * Sets a user attribute
 *
 * @param key the attribute key
 * @param value the attribute value
 *
 * Usage Example:
 * WalkMeSDK.setUserAttribute("key", "value")
 *
 */
WalkMeSDK.setUserAttribute(key, value)

/**
 * Sets multiple user attributes
 *
 * @param attributes the user attributes
 *
 * @code
 * Usage Example:
 * WalkMeSDK.setUserAttributes({"x": 3, "y": "yes", "z": false})
 */
WalkMeSDK.setUserAttributes(attributes)

/**
 * Sets a private user attribute
 *
 * @param key the attribute key
 * @param value the attribute value
 *
 * Usage Example:
 * WalkMeSDK.setPrivateUserAttribute("key", "value")
 *
 */
WalkMeSDK.setPrivateUserAttribute(key, value)

/**
 * Sets multiple private user attributes
 *
 * @param attributes the private user attributes
 *
 * @code
 * Usage Example:
 * WalkMeSDK.setPrivateUserAttributes({"x": 3, "y": "yes", "z": false})
 */
WalkMeSDK.setPrivateUserAttributes(attributes)

/**
 * Clears all private user attributes
 */
WalkMeSDK.clearPrivateUserAttributes()

/**
 * Launches a campaign by trigger key after redirecting the user to the given deep link
 * Once invoked, the method will show the campaign WITHOUT any of its segments (if defined)
 *
 * @code
 * Usage Example :
 * WalkMeSDK.trigger("key", "myApp://home/signup")
 */
WalkMeSDK.trigger(key, deepLink)

/**
 * Launches a campaign by trigger key
 * Once invoked, the method will show the campaign WITHOUT any of its segments (if defined)
 *
 * @code
 * Usage Example :
 * WalkMeSDK.trigger("key")
 */
WalkMeSDK.triggerWithoutDeepLink(key)

/**
 * Sets user id
 *
 * @param userId the user id as NSString
 *
 * @code
 * Usage Example:
 * WalkMeSDK.setUserID("someUserID")
 */
WalkMeSDK.setUserID(userId)

/**
 * Set events that won't be sent
 *
 * @param events of type WMStatsEventType that won't be sent
 *
 * @code
 * Usage Example :
 * WalkMeSDK.setEventsFilter(["interaction", "view_transition"])
 *
 */
WalkMeSDK.setEventsFilter(events)

/**
 * Set ID for a specific screen.
 * When used, this should be called everytime the screen shows
 *
 * @param screenID the ID to set for a specific screen
 *
 */
WalkMeSDK.setScreenID(screenID)

/**
 * Set the language for your campaigns.
 * When used, the language param you pass should match the name of one of the languages you’ve set up in the console.
 *
 * @param language the language for which you want the SDK to display your campaigns
 *
 */
WalkMeSDK.setLanguage(language)
```
