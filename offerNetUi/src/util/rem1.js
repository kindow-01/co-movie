/**

 * 配置 rem 缩放比例

 */

(function (doc, win) {

    let resizeEvt = 'orientationcyunhange' in window ? 'orientationchange' : 'resize';

    let recalc = function () {

        // 获取当前设备的窗口宽度

        var clientWidth = doc.documentElement.clientWidth;

        if (!clientWidth) return;

       

        // 动态设置全局字体大小

        doc.documentElement.style.fontSize = 100 * (clientWidth /1980) + 'px';

        // console.log('RemChange.js   动态设置全局字体大小    fontSize=',docEl.style.fontSize)

    };

    if (!doc.addEventListener) return;

    // 绑定事件

    win.addEventListener(resizeEvt, recalc, false);

    doc.addEventListener('DOMContentLoaded', recalc, false);

})(document, window);
