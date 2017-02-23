package com.zachary.reactnative.baidumap;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import java.util.List;

/**
 * Created by zachary on 2017/2/9.
 */

public class PoiSearchModule extends BaseModule
        implements OnGetPoiSearchResultListener, OnGetSuggestionResultListener {

    private PoiSearch mPoiSearch = null;
    private SuggestionSearch mSuggestionSearch = null;
    private BaiduMap mBaiduMap = null;
    private List<String> suggest;

    int searchType = 0;  // 搜索的类型，在显示时区分

    public PoiSearchModule(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
        initPoiSearch();
    }

    private void initPoiSearch() {
        // 初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);

        // 初始化建议搜索模块，注册建议搜索事件监听
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(this);
    }

    /**
     * 响应城市内搜索事件
     * @param city
     * @param keyword
     * @param pageNum
     */
    @ReactMethod
    public void searchInCityProcess(String city, String keyword, int pageNum) {
//        Log.i("searchInCityProcess=", city);
//        Log.i("searchInCityProcess=", keyword);
        searchType = 1;
        mPoiSearch.searchInCity((new PoiCitySearchOption())
                .city(city).keyword(keyword).pageNum(pageNum));
    }

    /**
     * 响应周边搜索事件
     * @param keyword
     * @param centerLat
     * @param centerLng
     * @param radius
     * @param pageNum
     */
    @ReactMethod
    public void  searchNearbyProcess(String keyword, double centerLat, double centerLng, int radius, int pageNum) {
        searchType = 2;
        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption().keyword(keyword)
                .sortType(PoiSortType.distance_from_near_to_far).location(new LatLng(centerLat, centerLng))
                .radius(radius).pageNum(pageNum);
        mPoiSearch.searchNearby(nearbySearchOption);
    }

    /**
     * 响应区域搜索事件
     * @param keyword
     * @param southwestLat
     * @param southwestLng
     * @param northeastLat
     * @param northeastLng
     */
    @ReactMethod
    public void searchBoundProcess(String keyword, double southwestLat, double southwestLng, double northeastLat, double northeastLng) {
        searchType = 3;
        LatLng southwest = new LatLng( southwestLat, southwestLng );
        LatLng northeast = new LatLng( northeastLat, northeastLng);
        LatLngBounds searchBound = new LatLngBounds.Builder().include(southwest).include(northeast).build();
        mPoiSearch.searchInBound(new PoiBoundSearchOption().bound(searchBound)
                .keyword(keyword));
    }

    /**
     * 获取POI搜索结果，包括searchInCity，searchNearby，searchInBound返回的搜索结果
     * @param result
     */
    @Override
    public void onGetPoiResult(PoiResult result) {
        WritableMap params = Arguments.createMap();
        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            params.putInt("errcode", -1);
            params.putString("message", "未找到结果");
        }
        else if(result.error == SearchResult.ERRORNO.NO_ERROR) {//成功
            params.putInt("errcode", 0);
            params.putString("message", "成功");
            WritableMap wMap = Arguments.createMap();
//            if(1 == searchType) {//城市搜索
//
//            }else if(2 == searchType) {//周边搜索
//
//            }else if(3 == searchType) {//区域搜索
//
//            }

            if(result != null) {
                wMap.putInt("currentPageCapacity", result.getCurrentPageCapacity()); //获取单页容量,单页容量可以通过检索参数指定
                wMap.putInt("currentPageNum", result.getCurrentPageNum()); //获取当前分页编号
                wMap.putInt("totalPageNum", result.getTotalPageNum()); //获取总分页数
                wMap.putInt("totalPoiNum", result.getTotalPoiNum()); //获取POI总数
                wMap.putInt("searchType", searchType);

                WritableArray poiAddrInfoArr = Arguments.createArray();
                if(result.isHasAddrInfo()) { //获取门址结果
                    for(int i = 0; i < result.getAllAddr().size(); i++) {
                        WritableMap poiAddrInfo = Arguments.createMap();
                        poiAddrInfo.putString("address", result.getAllAddr().get(i).address);
                        poiAddrInfo.putString("name", result.getAllAddr().get(i).name);
                        poiAddrInfo.putDouble("latitude", result.getAllAddr().get(i).location.latitude);
                        poiAddrInfo.putDouble("longitude", result.getAllAddr().get(i).location.longitude);
                        poiAddrInfoArr.pushMap(poiAddrInfo);
                    }
                }
                wMap.putArray("poiAddrInfos", poiAddrInfoArr);

                WritableArray allPoiInfoArr = Arguments.createArray();
                if(result.getAllPoi() != null) {
                    for(int i = 0; i < result.getAllPoi().size(); i++) { //获取Poi检索结果
                        WritableMap poiInfo = Arguments.createMap();
                        poiInfo.putString("name", result.getAllPoi().get(i).name);
                        poiInfo.putString("address", result.getAllPoi().get(i).address);
                        poiInfo.putString("phoneNum", result.getAllPoi().get(i).phoneNum);
                        poiInfo.putString("postCode", result.getAllPoi().get(i).postCode);
                        if(result.getAllPoi().get(i).type != null) {
                            poiInfo.putInt("type", result.getAllPoi().get(i).type.getInt());
                        }
                        poiInfo.putDouble("latitude", result.getAllPoi().get(i).location.latitude);
                        poiInfo.putDouble("longitude", result.getAllPoi().get(i).location.longitude);
                        poiInfo.putString("city", result.getAllPoi().get(i).city);
                        poiInfo.putBoolean("hasCaterDetails", result.getAllPoi().get(i).hasCaterDetails); //poi点是否有美食类详情页面
                        poiInfo.putBoolean("isPano", result.getAllPoi().get(i).isPano); //poi点附近是否有街景，可使用uid检索全景组件的全景数据
                        allPoiInfoArr.pushMap(poiInfo);
                    }
                }
                wMap.putArray("poiInfos", allPoiInfoArr);

                WritableArray cityInfoArr = Arguments.createArray();
                if(result.getSuggestCityList() != null) {
                    for(int i = 0; i < result.getSuggestCityList().size(); i++) { //返回城市列表页的结果数
                        WritableMap cityInfo = Arguments.createMap();
                        cityInfo.putString("city", result.getSuggestCityList().get(i).city);
                        cityInfo.putInt("num", result.getSuggestCityList().get(i).num); //搜索结果数量
                        cityInfoArr.pushMap(cityInfo);
                    }
                }
                wMap.putArray("cityInfos", cityInfoArr);

                params.putMap("poiResult", wMap);
            }
        }
        else if(result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
            // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
            String strInfo = "在";
            for (CityInfo cityInfo : result.getSuggestCityList()) {
                strInfo += cityInfo.city;
                strInfo += ",";
            }
            strInfo += "找到结果";
            params.putInt("errcode", -2);
            params.putString("message", strInfo);
        }

        sendEvent("onGetPoiResult", params);
    }

    /**
     * 获取POI详情搜索结果，得到searchPoiDetail返回的搜索结果
     * @param result
     */
    @Override
    public void onGetPoiDetailResult(PoiDetailResult result) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult result) {

    }

    /**
     * 获取在线建议搜索结果，得到requestSuggestion返回的搜索结果
     * @param result
     */
    @Override
    public void onGetSuggestionResult(SuggestionResult result) {

    }

    @Override
    public String getName() {
        return "BaiduPoiSearchModule";
    }
}
