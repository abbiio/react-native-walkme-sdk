
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
    [ABBI start:key withSecretKey:secret];
}

RCT_EXPORT_METHOD(restart)
{
    [ABBI restart];
}

RCT_EXPORT_METHOD(stop)
{
    [ABBI stop];
}

RCT_EXPORT_METHOD(sendGoal:(NSString *)goalName properites:(NSDictionary *)properties)
{
    [ABBI sendGoal:goalName withProperites:properties];
}

RCT_EXPORT_METHOD(setUserAttribute:(NSString *)key value:(id)value)
{
    [ABBI setUserAttributeWithKey:key andValue:value];
}

RCT_EXPORT_METHOD(setUserAttributes:(NSDictionary *)attributes)
{
    [ABBI setUserAttributes:attributes];
}

RCT_EXPORT_METHOD(setPrivateUserAttribute:(NSString *)key value:(id)value)
{
    [ABBI setPrivateUserAttributeWithKey:key andValue:value];
}

RCT_EXPORT_METHOD(setPrivateUserAttributes:(NSDictionary *)attributes)
{
    [ABBI setPrivateUserAttributes:attributes];
}

RCT_EXPORT_METHOD(clearPrivateUserAttributes)
{
    [ABBI clearPrivateUserAttributes];
}

RCT_EXPORT_METHOD(setFlag:(nonnull NSNumber *)num)
{
    [ABBI setFlag:num.intValue];
}

RCT_EXPORT_METHOD(trigger:(NSString *)trigger deepLink:(NSString *)deepLink)
{
    [ABBI trigger:trigger withDeepLink:deepLink];
}

RCT_EXPORT_METHOD(setUserID:(NSString *)userId)
{
    [ABBI setUserID:userId];
}

RCT_EXPORT_METHOD(setEventsFilter:(NSArray *)events)
{
    [ABBI setEventsFilter:events];
}

RCT_EXPORT_METHOD(setScreenID:(NSString *)screenID)
{
    [ABBI setScreenID:screenID];
}

RCT_EXPORT_METHOD(setLanguage:(NSString *)language)
{
    [ABBI setLanguage:language];
}

@end
