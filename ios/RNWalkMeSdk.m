
#import "RNWalkMeSdk.h"
#import <WalkMeSDK/WalkMeSDK.h>

@implementation RNWalkMeSdk

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(start:(NSString *)key secret:(NSString *)secret)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [ABBI start:key withSecretKey:secret];
    });
}

RCT_EXPORT_METHOD(restart)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [ABBI restart];
    });
}

RCT_EXPORT_METHOD(stop)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [ABBI stop];
    });
}

RCT_EXPORT_METHOD(sendGoal:(NSString *)goalName withProperites:(NSDictionary *)properties)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [ABBI sendGoal:goalName withProperites:properties];
    });
}

RCT_EXPORT_METHOD(setUserAttributeWithKey:(NSString *)key andValue:(id)value)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [ABBI setUserAttributeWithKey:key andValue:value];
    });
}

RCT_EXPORT_METHOD(setUserAttributes:(NSDictionary *)attributes)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [ABBI setUserAttributes:attributes];
    });
}

RCT_EXPORT_METHOD(setPrivateUserAttributeWithKey:(NSString *)key andValue:(id)value)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [ABBI setPrivateUserAttributeWithKey:key andValue:value];
    });
}

RCT_EXPORT_METHOD(setPrivateUserAttributes:(NSDictionary *)attributes)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [ABBI setPrivateUserAttributes:attributes];
    });
}

RCT_EXPORT_METHOD(clearPrivateUserAttributes)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [ABBI clearPrivateUserAttributes];
    });
}

RCT_EXPORT_METHOD(setFlag:(NSNumber *)num)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [ABBI setFlag:num.intValue];
    });
}

RCT_EXPORT_METHOD(trigger:(NSString *)trigger withDeepLink:(NSString *)deepLink)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [ABBI trigger:trigger withDeepLink:deepLink];
    });
}

RCT_EXPORT_METHOD(setUserID:(NSString *)userId)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [ABBI setUserID:userId];
    });
}

RCT_EXPORT_METHOD(setEventsFilter:(NSArray *)events)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [ABBI setEventsFilter:events];
    });
}

RCT_EXPORT_METHOD(setScreenID:(NSString *)screenID)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [ABBI setScreenID:screenID];
    });
}

RCT_EXPORT_METHOD(setLanguage:(NSString *)language)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [ABBI setLanguage:language];
    });
}

@end
  
