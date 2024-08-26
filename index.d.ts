declare module "react-native-walkme-sdk" {

    const RNWalkMeSDK: {
        start: (key: string, secret: string) => void;
        startWithUiManager: (key: string, secret: string) => void;
        campaignEventEmitter: NativeEventEmitter;
        restart: () => void;
        stop: () => void;
        sendGoal: (goalName: string, properties: any) => void;
        setUserAttribute: (key: string, value: string) => void;
        setUserAttributes: (attributes: any) => void;
        setPrivateUserAttribute: (key: string, value: string) => void;
        setPrivateUserAttributes: (attributes: any) => void;
        clearPrivateUserAttributes: () => void;
        setFlag: (num: number) => void;
        trigger: (trigger: string, deepLink: string) => void;
        setUserID: (userID: string) => void;
        setEventsFilter: (events: any) => void;
        setScreenID: (screenID: string) => void;
        setLanguage: (language: string) => void;
        registerEventListener: () => void;
    };

    export default appsFlyer;
}

