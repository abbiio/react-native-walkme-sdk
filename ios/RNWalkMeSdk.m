
#import "RNWalkMeSdk.h"
#import <WalkMeSDK/WalkMeSDK.h>

@implementation RNWalkMeSdk

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(start:(NSString *)key secret:(NSString *)secret
                  callback:(RCTResponseSenderBlock)callback)
{
    
    dispatch_async(dispatch_get_main_queue(), ^{
        if (key == nil || secret == nil) {
            callback(@[@"key or secret should not be undefined"]);
        }
        else {
            [ABBI start:key withSecretKey:secret];
            callback(@[[NSNull null]]);
        }
    });
}

@end
  
