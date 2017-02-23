'use strict'

import React, {Component} from 'react'
import {
  Dimensions,
  PixelRatio,
  StyleSheet
} from 'react-native'

export const PAGE_WIDTH = Dimensions.get('window').width
export const PAGE_HEIGHT = Dimensions.get('window').height

//当前的屏幕像素密度比例
export const PAGE_PIXEL_RATIO = PixelRatio.get()

const IPHONE6_PAGE_WIDTH = 375
const IPHONE6_PAGE_HEIGHT = 667

const RATIO_WIDTH = PAGE_WIDTH / IPHONE6_PAGE_WIDTH
const RATIO_HEIGHT = PAGE_HEIGHT / IPHONE6_PAGE_HEIGHT

export function em(value) {
  return Math.floor(+(RATIO_WIDTH * value).toFixed(2))
}

export function normalizeW(value) {
  return Math.floor(+(RATIO_WIDTH * value).toFixed(2))
}

export function normalizeH(value) {
  return Math.floor(+(RATIO_HEIGHT * value).toFixed(2))
}

/**
 * 根据像素比实现一像素边框
 */
export function normalizeBorder(borderWidth = 1) {

  if(1 === borderWidth) {
    return StyleSheet.hairlineWidth
  }

  if(__DEV__) {
    return borderWidth
  }
  return PAGE_PIXEL_RATIO >= 3
          ? borderWidth / 3
          : PAGE_PIXEL_RATIO >= 2
            ? borderWidth / 2
            : borderWidth
}
