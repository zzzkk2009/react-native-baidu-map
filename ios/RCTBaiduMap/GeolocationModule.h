//
//  GoelocationModule.h
//  RCTBaiduMap
//
//  Created by zachary on 2/11/2017.
//  Copyright © 2017 zachary. All rights reserved.
//

#ifndef GeolocationModule_h
#define GeolocationModule_h


#import <BaiduMapAPI_Location/BMKLocationService.h>

#import "BaseModule.h"
#import "RCTBaiduMapViewManager.h"

@interface GeolocationModule : BaseModule <BMKGeoCodeSearchDelegate> {
}

@end

#endif /* GeolocationModule_h */
