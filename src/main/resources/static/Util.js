/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-16-20:31
 * @since 2020-12-16-20:31
 * 描述：
 */

let WTU_SI = "CN.WTUCloud-SI.WTU.LUYUNHAO.";

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
 * 签到接口
 */
function signIn() {
    httpGOLogin("/server/login/signIn", {}, function (data) {
        if (data.success) {
            alert("签到成功！");
            // 刷新
            parent.location.reload();
        } else {
            if (data.code === 201) {
                if (confirm("你已经在" + formatDateAndTimeToString(new Date(data.data.time)) + "时签过到了！\n是否覆盖签到？"))
                    reSign();
                return;
            }
            alert("签到失败！" + data.msg + "请重新签到");
        }
    }, "post");
}

/**
 * 重新签到
 */
function reSign() {
    httpGOLogin("/server/login/signIn", {}, function (data) {
        if (data.success) {
            alert("更新签到成功！");
            parent.location.reload();
        } else {
            alert("更新签到失败！" + data.msg + "请重新签到");
        }
    }, "put");
}

/**
 * 判断是否登录
 * @param success 是的回调 默认是存储 user
 * @param fail 否的回调 默认跳转到登录页面
 */
function isLogin(success, fail) {
    if (success === null || success === undefined)
        success = function (data) {
            saveLocal("user", data.data);
        }
    if (fail === null || fail === undefined)
        fail = function (data) {
            alert("请先登录！")
            // 保存当前被拦截的页面url
            saveLocal("url", window.location.href);
            window.location.href = "/login.html";
        }
    // 实际回调
    let check = function (data) {
        if (data.success)
            success(data);
        else
            fail(data);
    }
    // 请求
    httpGOLogin("/server/identity/info", {}, check, "GET");
}

/**
 * 退出登录
 */
function logout() {
    httpGOLogin("/server/identity/logout", {}, function (data) {
        if (!data.success) {
            alert("退出登录失败！" + data.msg);
            return;
        }
        saveLocal("user", null);
        window.location.href = "/index.html";
    });
}

/**
 * 登录
 * @param id id
 * @param name name
 * @param success 回调
 */
function doLogin(id, name, remember, success) {
    // 检验
    if (id.length !== 10) {
        alert("学号应该是10位数");
        return;
    }
    if (name.length < 1) {
        alert("请输入姓名");
        return;
    }
    if (remember != true)
        remember = false;
    // 默认回调
    if (success === null || success === undefined)
        success = function (result) {
            if (!result.success) {
                alert("登录失败！" + result.msg);
                return;
            }
            // 写入缓存
            saveLocal("user", result.data);
            let local = getLocal("url");
            // 如果有被拦截的就跳转
            if (local !== null) {
                saveLocal("url", null);
                window.location.href = local;
                return;
            }
            window.location.href = "home.html";
        };

    // 请求
    httpGo("/server/identity/login", {id: id, name: name, remember: remember}, success, "POST");
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
        if (date.code == 500) {
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
    let secs = date.getUTCSeconds();
    if (hours < 10) hours = "0" + hours;
    if (mins < 10) mins = "0" + mins;
    if (secs < 10) secs = "0" + secs;
    return formatDateToString(date) + " " + hours + ":" + mins + ":" + secs;
}

/**
 *
 * @param url 转向网页的地址;
 * @param name 网页名称，可为空;
 * @param iWidth 弹出窗口的宽度;
 * @param iHeight 弹出窗口的高度;
 */
function openWindow(url, name, iWidth, iHeight) {
    //window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
    let iTop = (window.screen.height - 30 - iHeight) / 2; //获得窗口的垂直位置;
    let iLeft = (window.screen.width - 10 - iWidth) / 2; //获得窗口的水平位置;
    if (name === null || name === undefined)
        name = "窗口";
    if (iHeight === null || iHeight === undefined)
        iHeight = window.screen.height / 2;
    if (iWidth === null || iWidth === undefined)
        iWidth = window.screen.height / 2;
    window.open(url, name, 'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}