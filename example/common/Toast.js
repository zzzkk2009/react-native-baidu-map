import Toast from 'react-native-root-toast'

export function show(text, options) {
  Toast.show(text, {
    duration: options && options.duration || 2000,
    position: Toast.positions.CENTER,
    shadow: true,
    animation: true,
    hideOnPress: true,
    delay: 0,
    onShow: () => {
        // calls on toast\`s appear animation start
    },
    onShown: () => {
        // calls on toast\`s appear animation end.
    },
    onHide: () => {
        // calls on toast\`s hide animation start.
    },
    onHidden: () => {
        // calls on toast\`s hide animation end.
      if(options && options.onHidden && typeof options.onHidden == 'function') {
        options.onHidden()
      }
    }
  })
}