//
//  BaseModule.m
//  RCTBaiduMap
//
//  Created by zachary on 2/11/2017.
//  Copyright © 2017 zachary. All rights reserved.
//

#import "BaseModule.h"

@implementation BaseModule

@synthesize bridge = _bridge;

-(NSMutableDictionary *)getEmptyBody {
    NSMutableDictionary *body = @{}.mutableCopy;
    return body;
}

-(void)sendEvent:(NSString *)name body:(NSMutableDictionary *)body {
    [self.bridge.eventDispatcher sendDeviceEventWithName:name body:body];
}


@end
