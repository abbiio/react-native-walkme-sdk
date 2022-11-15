
#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#import "RCTEventEmitter.h"
#else
#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>
#endif

#import <WalkMeSDK/WalkMeSDK.h>

#define wmCampaignInfoEventDismissed    @"wmCampaignInfoEventDismissed"
#define wmCampaignInfoEventWillShow     @"wmCampaignInfoEventWillShow"
#define wmCampaignInfoEventAction       @"wmCampaignInfoEventAction"

@interface RNWalkMeSdk : RCTEventEmitter <RCTBridgeModule, WMCampaignInfoDelegate>

@end
  
