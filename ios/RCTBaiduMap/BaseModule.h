//
//  BaseModule.h
//  RCTBaiduMap
//
//  Created by zachary on 2/11/2017.
//  Copyright © 2017 zachary. All rights reserved.
//

#ifndef BaseModule_h
#define BaseModule_h

#import <React/RCTBridgeModule.h>
#import <React/RCTEventDispatcher.h>
#import <React/RCTBridge.h>

#import <BaiduMapAPI_Base/BMKBaseComponent.h>
#import <BaiduMapAPI_Map/BMKMapComponent.h>
#import <BaiduMapAPI_Search/BMKSearchComponent.h>
#import <BaiduMapAPI_Utils/BMKUtilsComponent.h>

@interface BaseModule : NSObject <RCTBridgeModule> {
    UINavigationController *navigationController;
    BMKMapManager* _mapManager;
}

-(void)sendEvent:(NSString *)name body:(NSMutableDictionary *)body;

-(NSMutableDictionary *)getEmptyBody;

@end

#endif /* BaseModule_h */
