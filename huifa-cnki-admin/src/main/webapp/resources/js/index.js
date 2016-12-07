$(function () {
    baseMenu();
    tabClose();
    tabCloseEven();
    var userType = $("input[name='userType']").val();
    if(userType == 'taobao'){
        $('#tabs').tabs('add', {
            title: '首页',
            content: '<iframe src="'+PageCommon.contextPath+'/seller/sellerCenter.xhtml" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>',
            closable: false,
            extractor: function (data) {
                return data;
            }
        });
    }
});


/**
 * 显示基础菜单
 */
function baseMenu() {
    $("#baseBtn").css("color", "#FF0000");
    $("#systemBtn").css("color", "#000000");
    $("#systemMenu").hide();
    $("#baseMenu").show();
}


function addTab(subtitle, url) {
    if (!$('#tabs').tabs('exists', subtitle)) {
        $('#tabs').tabs('add', {
            title: subtitle,
            content: createFrame(url),
            closable: true,
            width: $('#mainPanle').width() - 10,
            height: $('#mainPanle').height() - 26
        });
    } else {
        $('#tabs').tabs('select', subtitle);
    }
    tabClose();
}

function createFrame(url) {
    var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
        + url + '" style="width:100%;height:100%;"></iframe>';
    return s;
}

function tabClose() {
    /*双击关闭TAB选项卡*/
    $(".tabs-inner").dblclick(function () {
        var subtitle = $(this).children("span").text();
        $('#tabs').tabs('close', subtitle);
    })

    $(".tabs-inner").bind('contextmenu', function (e) {
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY,
        });
        var subtitle = $(this).children("span").text();
        $('#mm').data("currtab", subtitle);
        return false;
    });
}

//绑定右键菜单事件
function tabCloseEven() {
    //关闭当前
    $('#mm-tabclose').click(function () {
        var currtab_title = $('#mm').data("currtab");
        $('#tabs').tabs('close', currtab_title);
    });
    //全部关闭
    $('#mm-tabcloseall').click(function () {
        $('.tabs-inner span').each(function (i, n) {
            var t = $(n).text();
            $('#tabs').tabs('close', t);
        });
    });

    //关闭除当前之外的TAB
    $('#mm-tabcloseother').click(function () {
        var currtab_title = $('#mm').data("currtab");
        $('.tabs-inner span').each(function (i, n) {
            var t = $(n).text();
            if (t != currtab_title)
                $('#tabs').tabs('close', t);
        });
    });
    //关闭当前右侧的TAB
    $('#mm-tabcloseright').click(function () {
        var nextall = $('.tabs-selected').nextAll();
        if (nextall.length == 0) {
            //msgShow('系统提示','后边没有啦~~','error');
            alert('后边没有啦~~');
            return false;
        }
        nextall.each(function (i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            $('#tabs').tabs('close', t);
        });
        return false;
    });
    //关闭当前左侧的TAB
    $('#mm-tabcloseleft').click(function () {
        var prevall = $('.tabs-selected').prevAll();
        if (prevall.length == 0) {
            alert('到头了，前边没有啦~~');
            return false;
        }
        prevall.each(function (i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            $('#tabs').tabs('close', t);
        });
        return false;
    });

    //退出
    $("#mm-exit").click(function () {
        $('#mm').menu('hide');
    });
}

function affirm() {
    $('#updatePwd').form('submit', {
        dataType: 'json',
        url: PageCommon.contextPath + '/admin/updatePwd.xhtml',
        onSubmit: function () {
            return $(this).form('enableValidation').form('validate');
        },
        success: function (data) {
            var result = JSON.parse(data);
            if (result.state == 'success') {
                window.location.href = PageCommon.contextPath + "/admin/login.xhtml";
            } else if (result.state == 'pwdError') {
                alert("原密码不正确");
            } else if (result.state == 'newPwdError') {
                alert("新旧密码不能一样");
            } else if (result.state == 'confirmError') {
                alert("新密码与确认密码不一致");
            } else if (result.state == 'fail') {
                alert("修改密码出错，请联系技术！");
            }
        },
        fail: function (data) {
            $.messager.alert("请求失败", "添加数据时，请求失败，服务器出错，请重试。");
        }
    });
}

function agentAffirm() {
    $('#updatePwd').form('submit', {
        dataType: 'json',
        url: PageCommon.contextPath + '/agent/updatePwd.xhtml',
        onSubmit: function () {
            return $(this).form('enableValidation').form('validate');
        },
        success: function (data) {
            var result = JSON.parse(data);
            if (result.state == 'success') {
                window.location.href = PageCommon.contextPath + "/admin/login.xhtml";
            } else if (result.state == 'pwdError') {
                alert("原密码不正确");
            } else if (result.state == 'newPwdError') {
                alert("新旧密码不能一样");
            } else if (result.state == 'confirmError') {
                alert("新密码与确认密码不一致");
            } else if (result.state == 'fail') {
                alert("修改密码出错，请联系技术！");
            }
        },
        fail: function (data) {
            $.messager.alert("请求失败", "添加数据时，请求失败，服务器出错，请重试。");
        }
    });
}