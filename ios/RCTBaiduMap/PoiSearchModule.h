//
//  PoiSearchModule.h
//  HLife
//
//  Created by zachary on 2017/2/11.
//  Copyright © 2017年 Simors. All rights reserved.
//

#ifndef PoiSearchModule_h
#define PoiSearchModule_h

#import "BaseModule.h"
#import "RCTBaiduMapViewManager.h"
#import <BaiduMapAPI_Map/BMKMapComponent.h>
#import <BaiduMapAPI_Search/BMKSearchComponent.h>

@interface PoiSearchModule : BaseModule <BMKPoiSearchDelegate> {
  int searchType;
}

@end

#endif /* PoiSearchModule_h */
