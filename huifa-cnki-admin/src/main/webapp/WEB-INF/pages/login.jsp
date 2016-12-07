<%--
  Created by IntelliJ IDEA.
  User: kchen
  Date: 2016/11/24
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户登录</title>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" type="text/css" rel="stylesheet"/>
    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui.css" rel="stylesheet"/>
    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/icon.css" rel="stylesheet"/>
    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/demo.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
</head>
<body>
<div style="margin:10%;"></div>
<a class="v-a" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=39371532&site=qq&menu=yes">
    <img border="0" src="http://wpa.qq.com/pa?p=2:39371532:51" alt="点击这里给我发消息" title="点击这里给我发消息"/>
</a>
<h3 class="v-h3">CNKI后台管理系统V1.0</h3>
<div style="margin-left:auto;margin-right:auto;width: 330px;">
    <div class="easyui-panel" title="用户登录" style="width:330px">
        <div style="padding: 15px;">
            <form id="loginForm" action="${pageContext.request.contextPath}/main/login.xhtml" method="post" data-options="novalidate:true">
                <table cellpadding="5">
                    <tr>
                        <td>登录账号</td>
                        <td><input class="easyui-validatebox" type="text" name="loginName" data-options="required:true"/></td>
                    </tr>
                    <tr>
                        <td>登录密码</td>
                        <td><input class="easyui-validatebox" type="password" name="password" data-options="required:true"/></td>
                    </tr>
                </table>
            </form>
            <div style="text-align:center;padding:10px">
                <%--<input type="submit" value="登录"/>--%>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#loginForm').submit()">淘宝代理登录</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#loginForm').submit()">淘宝代理登录</a>
                <%--<a href="javascript:void(0)" onclick="$('#dlg').dialog('open')">淘宝代理商注册</a>--%>
            </div>
        </div>
    </div>
</div>
</body>
</html>
