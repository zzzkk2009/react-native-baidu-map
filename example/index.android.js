/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

import BaiduMapDemo from './BaiduMapDemo'

export default class example extends Component {
  render() {
    return (
      <BaiduMapDemo />
    );
  }
}

AppRegistry.registerComponent('example', () => example);
