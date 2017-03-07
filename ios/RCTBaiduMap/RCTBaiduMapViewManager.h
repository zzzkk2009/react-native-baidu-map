//
//  RCTBaiduMapViewManager.h
//  RCTBaiduMap
//
//  Created by zachary on 2/11/2017.
//  Copyright © 2017 zachary. All rights reserved.
//

#ifndef RCTBaiduMapViewManager_h
#define RCTBaiduMapViewManager_h

#import "RCTBaiduMapView.h"

@interface RCTBaiduMapViewManager : RCTViewManager<BMKMapViewDelegate>

+(void)initSDK:(NSString *)key;

-(void)sendEvent:(RCTBaiduMapView *) mapView params:(NSDictionary *) params;

@end

#endif /* RCTBaiduMapViewManager_h */
