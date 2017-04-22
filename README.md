# @zzzkk2009/react-native-baidu-map [![npm version](https://img.shields.io/npm/v/@zzzkk2009/react-native-baidu-map.svg?style=flat)](https://www.npmjs.com/package/@zzzkk2009/react-native-baidu-map)

该项目基于[https://github.com/lovebing/react-native-baidu-map](https://github.com/lovebing/react-native-baidu-map)库进行修改，在此特别感谢lovebing!

Baidu Map SDK modules and view for React Native(Android & IOS), support react native 0.40+

百度地图 React Native 模块，支持 react native 0.40+

类似滴滴,摩拜单车地图效果.

![Android](https://raw.githubusercontent.com/zzzkk2009/react-native-baidu-map/master/images/android.gif)

### Install 安装
    npm install @zzzkk2009/react-native-baidu-map --save
### Import 导入

#### Android Studio
- settings.gradle `
include ':zzzkk2009-react-native-baidu-map'
project(':zzzkk2009-react-native-baidu-map').projectDir = new File(settingsDir, '../node_modules/@zzzkk2009/react-native-baidu-map/android')`

- build.gradle `compile project(':zzzkk2009-react-native-baidu-map')`

- MainApplication`new BaiduMapPackage(getApplicationContext())`
- AndroidManifest.xml `<meta-data
            android:name="com.baidu.lbsapi.API_KEY" android:value="xx"/>`
            
- AndroidManifest.xml `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
                       <uses-permission android:name="android.permission.INTERNET"/>
                       <uses-permission android:name="com.android.launcher.permission.READ_SETTINGS" />
                       <uses-permission android:name="android.permission.WAKE_LOCK"/>
                       <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
                       <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
                       <uses-permission android:name="android.permission.GET_TASKS" />
                       <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
                       <uses-permission android:name="android.permission.WRITE_SETTINGS" />`            

#### Xcode
- Project navigator->Libraries->Add Files to 选择 node_modules/@zzzkk2009/react-native-baidu-map/ios/RCTBaiduMap.xcodeproj
- Project navigator->Build Phases->Link Binary With Libraries 加入 libRCTBaiduMap.a
- Project navigator->Build Settings->Search Paths， Framework search paths 添加 $(SRCROOT)/../node_modules/@zzzkk2009/react-native-baidu-map/ios/lib (选择recursive)
- Project navigator->Build Settings->Search Paths， Header search paths 添加 $(SRCROOT)/../node_modules/@zzzkk2009/react-native-baidu-map/ios/lib (选择recursive)
- Project navigator->Build Settings->Search Paths， Header search paths 添加 $(SRCROOT)/../node_modules/@zzzkk2009/react-native-baidu-map/ios/RCTBaiduMap (选择recursive)
- Project navigator->Build Settings->Search Paths， Library search paths 添加 $(SRCROOT)/../node_modules/@zzzkk2009/react-native-baidu-map/ios/lib (选择recursive)
- Project navigator->Build Phases->Link Binary With Libraries, 添加依赖, node_modules/@zzzkk2009/react-native-baidu-map/ios/lib/BaiduMapAPI下的全部framework和thiridlibs下的全部.a文件,， 以及CoreLocation.framework和QuartzCore.framework、OpenGLES.framework、SystemConfiguration.framework、CoreGraphics.framework、Security.framework、libsqlite3.0.tbd（xcode7以前为 libsqlite3.0.dylib）、CoreTelephony.framework 、libstdc++.6.0.9.tbd（xcode7以前为libstdc++.6.0.9.dylib）、CoreTelephony.framework
- 工程右键添加"Add Files to '工程'" node_modules/@zzzkk2009/react-native-baidu-map/ios/lib/BaiduMapAPI/BaiduMapAPI_Map.framework/Resources/mapapi.bundle

- [其它一些注意事项可参考百度地图LBS文档](http://lbsyun.baidu.com/index.php?title=iossdk/guide/buildproject)

##### AppDelegate.m init 初始化
    #import "RCTBaiduMapViewManager.h"
    - (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
    {
        ...
        [RCTBaiduMapViewManager initSDK:@"api key"];
        ...
    }
  
### Usage 使用方法
    见example
    
    import { MapView, MapTypes, MapModule, Geolocation } from '@zzzkk2009/react-native-baidu-map'

#### MapView Props 属性
| Name                    | Type  | Default  | Extra 
| ----------------------- |:-----:| :-------:| -------
| zoomControlsVisible     | bool  | true     | Android only
| trafficEnabled          | bool  | false    |
| baiduHeatMapEnabled     | bool  | false    |
| mapType                 | number| 1        |
| zoom                    | number| 10       |
| center                  | object| null     | {latitude: 0, longitude: 0}
| marker                  | object| null     | {latitude: 0, longitude: 0, title: ''}
| markers                 | array | []       | [marker, maker]
| onMapStatusChangeStart  | func  | undefined| Android only
| onMapStatusChange       | func  | undefined|
| onMapStatusChangeFinish | func  | undefined| Android only
| onMapLoaded             | func  | undefined|
| onMapClick              | func  | undefined|
| onMapDoubleClick        | func  | undefined|
| onMarkerClick           | func  | undefined|
| onMapPoiClick           | func  | undefined|

#### MapModule Methods (Deprecated)
    setMarker(double lat, double lng)
    setMapType(int mapType)
    moveToCenter(double lat, double lng, float zoom)
    Promise reverseGeoCode(double lat, double lng)
    Promise reverseGeoCodeGPS(double lat, double lng)
    Promise geocode(String city, String addr),
    Promise getCurrentPosition()
      
#### Geolocation Methods

| Method                    | Result 
| ------------------------- | -------
| Promise reverseGeoCode(double lat, double lng) | `{"address": "", "province": "", "city": "", "district": "", "streetName": "", "streetNumber": ""}`
| Promise reverseGeoCodeGPS(double lat, double lng) |  `{"address": "", "province": "", "city": "", "district": "", "streetName": "", "streetNumber": ""}`
| Promise geocode(String city, String addr) | {"latitude": 0.0, "longitude": 0.0}
| Promise getCurrentPosition() | IOS: `{"latitude": 0.0, "longitude": 0.0}` Android: `{"latitude": 0.0, "longitude": 0.0, "direction": -1, "altitude": 0.0, "radius": 0.0, "address": "", "countryCode": "", "country": "", "province": "", "cityCode": "", "city": "", "district": "", "street": "", "streetNumber": "", "buildingId": "", "buildingName": ""}`
