# @zzzkk2009/react-native-baidu-map [![npm version](https://img.shields.io/npm/v/@zzzkk2009/react-native-baidu-map.svg?style=flat)](https://www.npmjs.com/package/@zzzkk2009/react-native-baidu-map)

Este proyecto se basa en [https://github.com/lovebing/react-native-baidu-map](https://github.com/lovebing/react-native-baidu-map), y en particular, gracias a lovebing!

Módulos y vistas del SDK de Baidu Map para React Native (Android & iOS), compatibles con react native 0.40+

Módulo React Native para Baidu Map, compatible con react native 0.40+

Similar al estilo de mapas de Didi, Mobike.

![Android](https://raw.githubusercontent.com/zzzkk2009/react-native-baidu-map/master/images/android.gif)

### Instalación  
npm install @zzzkk2009/react-native-baidu-map --save

### Importar

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
- Project navigator->Libraries->Add Files to  seleccionar node_modules/@zzzkk2009/react-native-baidu-map/ios/RCTBaiduMap.xcodeproj
- Project navigator->Build Phases->Link Binary With Libraries  agregar libRCTBaiduMap.a
- Project navigator->Build Settings->Search Paths， Framework search paths  agregar $(SRCROOT)/../node_modules/@zzzkk2009/react-native-baidu-map/ios/lib (seleccionar recursive)
- Project navigator->Build Settings->Search Paths， Header search paths  agregar $(SRCROOT)/../node_modules/@zzzkk2009/react-native-baidu-map/ios/lib (seleccionar recursive)
- Project navigator->Build Settings->Search Paths， Header search paths  agregar $(SRCROOT)/../node_modules/@zzzkk2009/react-native-baidu-map/ios/RCTBaiduMap (seleccionar recursive)
- Project navigator->Build Settings->Search Paths， Library search paths  agregar $(SRCROOT)/../node_modules/@zzzkk2009/react-native-baidu-map/ios/lib (seleccionar recursive)
- Project navigator->Build Phases->Link Binary With Libraries, agregar dependencias, node_modules/@zzzkk2009/react-native-baidu-map/ios/lib/BaiduMapAPI下的全部framework和thiridlibs下的全部.a文件,， 以及CoreLocation.framework和QuartzCore.framework、OpenGLES.framework、SystemConfiguration.framework、CoreGraphics.framework、Security.framework、libsqlite3.0.tbd（xcode7以前为 libsqlite3.0.dylib）、CoreTelephony.framework 、libstdc++.6.0.9.tbd（xcode7以前为libstdc++.6.0.9.dylib）、CoreTelephony.framework
- 工程右键添加"Add Files to '工程'" node_modules/@zzzkk2009/react-native-baidu-map/ios/lib/BaiduMapAPI/BaiduMapAPI_Map.framework/Resources/mapapi.bundle

- [Referencias adicionales a algunos puntos importantes en la documentación de Baidu Map LBS](http://lbsyun.baidu.com/index.php?title=iossdk/guide/buildproject)

##### AppDelegate.m inicialización
# import "RCTBaiduMapViewManager.h"
- (Bool)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{  
    ...  
    [RCTBaiduMapViewManager initSDK:@"api key"];  
    ...  
}

### Uso
Ver ejemplo  
import { MapView, MapTypes, MapModule, Geolocation } from '@zzzkk2009/react-native-baidu-map'

#### Props de MapView
| Nombre                   | Tipo   | Valor por defecto | Extra 
| -------------------------|:------:|:-----------------:|:-----:
| zoomControlsVisible      | bool   | true              | Solo Android
| trafficEnabled           | bool   | false             |
| baiduHeatMapEnabled      | bool   | false             |
| mapType                  | number | 1                 |
| zoom                     | number | 10                |
| center                   | object | null              | {latitude: 0, longitude: 0}
| marker                   | object | null              | {latitude: 0, longitude: 0, title: ''}
| markers                  | array  | []                | [marker, maker]
| onMapStatusChangeStart   | func   | undefined         | Solo Android
| onMapStatusChange        | func   | undefined         |
| onMapStatusChangeFinish  | func   | undefined         | Solo Android
| onMapLoaded              | func   | undefined         |
| onMapClick               | func   | undefined         |
| onMapDoubleClick         | func   | undefined         |
| onMarkerClick            | func   | undefined         |
| onMapPoiClick            | func   | undefined         |

#### Métodos de MapModule (Obsoleto)
    setMarker(double lat, double lng)
    setMapType(int mapType)
    moveToCenter(double lat, double lng, float zoom)
    Promise reverseGeoCode(double lat, double lng)
    Promise reverseGeoCodeGPS(double lat, double lng)
    Promise geocode(String city, String addr),
    Promise getCurrentPosition()
      
#### Métodos de Geolocalización

| Método                    | Resultado 
| ------------------------- | 
| Promise reverseGeoCode(double lat, double lng) | `{"address": "", "province": "", "city": "", "district": "", "streetName": "", "streetNumber": ""}`
| Promise reverseGeoCodeGPS(double lat, double lng) |  `{"address": "", "province": "", "city": "", "district": "", "streetName": "", "streetNumber": ""}`
| Promise geocode(String city, String addr) | {"latitude": 0.0, "longitude": 0.0}
| Promise getCurrentPosition() | IOS: `{"latitude": 0.0, "longitude": 0.0}` Android: `{"latitude": 0.0, "longitude": 0.0, "direction": -1, "altitude": 0.0, "radius": 0.0, "address": "", "countryCode": "", "country": "", "province": "", "cityCode": "", "city": "", "district": "", "street": "", "streetNumber": "", "buildingId": "", "buildingName": ""}`
