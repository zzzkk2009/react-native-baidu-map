import {
  requireNativeComponent,
  View,
  NativeModules,
  Platform,
  DeviceEventEmitter
} from 'react-native';

import React, {
  Component,
  PropTypes
} from 'react';

import MapTypes from './MapTypes';

export default class MapView extends Component {
  static propTypes = {
    ...View.propTypes,
    zoomControlsVisible: PropTypes.bool,
    trafficEnabled: PropTypes.bool,
    baiduHeatMapEnabled: PropTypes.bool,
    mapType: PropTypes.number,
    zoom: PropTypes.number,
    center: PropTypes.object,
    marker: PropTypes.object,
    markers: PropTypes.array,
    childrenPoints: PropTypes.array,
    onMapStatusChangeStart: PropTypes.func,
    onMapStatusChange: PropTypes.func,
    onMapStatusChangeFinish: PropTypes.func,
    onRegionDidChangeAnimated: PropTypes.func,
    onMapLoaded: PropTypes.func,
    onMapClick: PropTypes.func,
    onMapDoubleClick: PropTypes.func,
    onMarkerClick: PropTypes.func,
    onMapPoiClick: PropTypes.func
  };

  static defaultProps = {
    zoomControlsVisible: false,
    trafficEnabled: false,
    baiduHeatMapEnabled: false,
    mapType: MapTypes.NORMAL,
    childrenPoints: [],
    marker: null,
    markers: [],
    center: null,
    zoom: 15
  };

  constructor() {
    super();
  }

  _onChange(event) {
    // console.log('_onChange.event====', event)
    // console.log('_onChange.event.nativeEvent====', event.nativeEvent)
    if (typeof this.props[event.nativeEvent.type] === 'function') {
      this.props[event.nativeEvent.type](event.nativeEvent.params);
    }
  }

  render() {
    return <BaiduMapView {...this.props} onChange={this._onChange.bind(this)}/>;
  }
}

const BaiduMapView = requireNativeComponent('RCTBaiduMapView', MapView, {
  nativeOnly: {onChange: true}
});
