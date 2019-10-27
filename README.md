
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
import RNWalkMeSdk from 'react-native-walkme-sdk';

// TODO: What to do with the module?
RNWalkMeSdk;
```
