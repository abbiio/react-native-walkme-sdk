
# react-native-walk-me-sdk

## Getting started

`$ npm install react-native-walk-me-sdk --save`

### Mostly automatic installation

`$ react-native link react-native-walk-me-sdk`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-walk-me-sdk` and add `RNWalkMeSdk.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNWalkMeSdk.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNWalkMeSdkPackage;` to the imports at the top of the file
  - Add `new RNWalkMeSdkPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-walk-me-sdk'
  	project(':react-native-walk-me-sdk').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-walk-me-sdk/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-walk-me-sdk')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNWalkMeSdk.sln` in `node_modules/react-native-walk-me-sdk/windows/RNWalkMeSdk.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Walk.Me.Sdk.RNWalkMeSdk;` to the usings at the top of the file
  - Add `new RNWalkMeSdkPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNWalkMeSdk from 'react-native-walk-me-sdk';

// TODO: What to do with the module?
RNWalkMeSdk;
```
  