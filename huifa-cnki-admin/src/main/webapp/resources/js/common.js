//获取当前网址，如 http://localhost:8080/demo/xx/xx.jsp
var curWwwPath = window.document.location.href;
//获取主机地址之后的目录，如： demo/xx.jsp
var pathName = window.document.location.pathname;
var pos = curWwwPath.indexOf(pathName);
//获取主机地址，如： http://localhost:8080
var localhostPaht = curWwwPath.substring(0, pos);
//获取带"/"的项目名，如：/demo
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
var PageCommon = {contextPath: localhostPaht + projectName};
var mobilenoReg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;

$(document).ready(function () {
    $(document).ajaxStart(onStart).ajaxComplete(onComplete).ajaxSuccess(onSuccess);
});

function onStart(event) {
}

function onComplete(event, xhr, settings) {
    if (xhr.status == 403) {
        alert("与服务器断开连接，请重新登录！");
        window.location.href = PageCommon.contextPath + "/main/login.xhtml";
    }
}

function onSuccess(event, xhr, settings) {
}

$.event.special.valuechange = {
    teardown: function (namespaces) {
        $(this).unbind('.valuechange');
    },
    handler: function (e) {
        $.event.special.valuechange.triggerChanged($(this));
    },
    add: function (obj) {
        $(this).on('keyup.valuechange cut.valuechange paste.valuechange input.valuechange', obj.selector, $.event.special.valuechange.handler)
    },
    triggerChanged: function (element) {
        var current = element[0].contentEditable === 'true' ? element.html() : element.val()
            , previous = typeof element.data('previous') === 'undefined' ? element[0].defaultValue : element.data('previous')
        if (current !== previous) {
            element.trigger('valuechange', [element.data('previous')])
            element.data('previous', current)
        }
    }
};

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};

/**
 * 设置jquery easyui 日期格式
 * @param date
 * @returns {string}
 */
function myFormatter(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}
/**
 * 设置jquery easyui 日期格式
 * @param s
 * @returns {Date}
 */
function myParser(s) {
    if (!s)
        return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0], 10);
    var m = parseInt(ss[1], 10);
    var d = parseInt(ss[2], 10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
        return new Date(y, m - 1, d);
    } else {
        return new Date();
    }
}

function isInteger(obj) {
    return typeof obj === 'number' && obj % 1 === 0
}

function isTaobaoOrder(orderNum) {
    return /^([1-9][0-9]*)$/.test(orderNum);
}

function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}