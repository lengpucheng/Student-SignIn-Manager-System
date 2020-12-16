/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-16-20:31
 * @since 2020-12-16-20:31
 * 描述：
 */

let WTU_SI = "CN.WTUCloud-SI.WTU.LUYUNHAO";

/**
 * 奖数据写入本地缓存
 * @param key key
 * @param value 数据
 */
function saveLocal(key, value) {
    let obj = JSON.stringify(value);
    localStorage.setItem(WTU_SI + key, obj);
}

/**
 * 从本地缓存读取数据
 * @param key key
 * @returns {any} 数据对象
 */
function getLocal(key) {
    return JSON.parse(localStorage.getItem(WTU_SI + key));
}


/**
 *  带登录验证的 Http 请求
 * @param url 地址，可以是相对地址
 * @param data 数据，形式为 map 键值对{a:"",b:""};
 * @param success 请求成功后的回调函数，会判断是否登录，否则会跳转登录页面
 * @param method  请求方式，默认GET，形式为”POST/GET/“
 * @param error   错误的回调，默认会提升网络错误
 */
function httpGOLogin(url, data, success, method, error) {
    // 添加未登录判断
    let successLogin = function (date) {
        if (date.code === 500) {
            // 未登录
            alert("请先登录！" + date.msg);
            // 跳转登陆界面
            window.location.href = "/login.html";
            return;
        }
        // 递归回调
        if (success !== undefined)
            success(date);
    }
    httpGo(url, data, successLogin, method, error);
}


/**
 *  通用的 Http 请求
 * @param url 地址，可以是相对地址
 * @param data 数据，形式为 map 键值对{a:"",b:""};
 * @param success 请求成功后的回调函数，默认为空
 * @param method  请求方式，默认GET，形式为”POST/GET/“
 * @param error   错误的回调，默认会提升网络错误
 */
function httpGo(url, data, success, method, error) {
    // 若success 为 null
    if (success === undefined)
        success = function () {
        };
    // 若method为null 默认GET
    if (method === undefined)
        method = "GET";
    // 若 error为null
    if (error === undefined)
        error = function () {
            alert("网络错误！请检查网络！");
        };

    // 发送请求
    $.ajax({
        type: method,
        url: url,
        data: data,
        success: success,
        error: error
    });
}


/**
 * 转换为 YYYY-MM-DD
 * @param date 日期 为空则 为当前
 * @returns {*}  YYYY-MM-DD
 */
function formatDateToString(date) {
    if (date === undefined || date === null) {
        date = new Date();
    }
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    if (month < 10) month = "0" + month;
    if (day < 10) day = "0" + day;
    return year + "-" + month + "-" + day;
}

/**
 * 转换为 YYYY-MM-DD HH MM SS
 * @param date 日期 为空则 为当前
 * @returns {*}  YYYY-MM-DD HH MM SS
 */
function formatDateAndTimeToString(date) {
    if (date === undefined || date === null) {
        date = new Date();
    }
    let hours = date.getHours();
    let mins = date.getMinutes();
    let secs = date.getSeconds();
    let msecs = date.getMilliseconds();
    if (hours < 10) hours = "0" + hours;
    if (mins < 10) mins = "0" + mins;
    if (secs < 10) secs = "0" + secs;
    if (msecs < 10) secs = "0" + msecs;
    return formatDateToString(date) + " " + hours + ":" + mins + ":" + secs + ":" + msecs;
}