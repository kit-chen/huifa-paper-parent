<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/26
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>淘宝代理商账户信息</title>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" type="text/css" rel="stylesheet"/>
    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui.css" rel="stylesheet"/>
    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/icon.css" rel="stylesheet"/>
    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/demo.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
    <title>账户信息</title>
</head>
<body>
<div style="float: left;">
    <div style="float: left;">
        <div class="easyui-panel" title="账户信息" style="width:800px;">
            <div style="padding:10px 20px 20px 20px">
                <form id="userInfoFM" action="${pageContext.request.contextPath}/seller/saveUserInfo.xhtml"
                      method="post">
                    <table cellpadding="8" cellspacing="8">
                        <tr>
                            <input type="hidden" value="${seller.id}" name="id">
                            <td>买家提交检测长地址:</td>
                            <td>
                                 <c:if test="${not empty seller.domain}">
                                     http://${seller.domain}.${domain}
                                 </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>宝贝购买地址:</td>
                            <td><input class="easyui-textbox" name="buyLink" data-options="required:true"
                                       value="${seller.buyLink}" style="width: 370px"/></td>
                        </tr>
                        <tr>
                            <td>账户余额:</td>
                            <td>${seller.balance}</td>
                        </tr>
                        <tr>
                            <td>店铺地址:</td>
                            <td><input class="easyui-textbox" name="shopUrl" data-options="required:true"
                                       value="${seller.shopUrl}" style="width: 370px"/></td>
                        </tr>
                        <tr>
                            <td>卖家昵称:</td>
                            <td><input class="easyui-textbox" name="sellerNick" data-options="required:true"
                                       value="${seller.sellerNick}" style="width: 370px"/></td>
                        </tr>
                        <tr>
                            <td>真实姓名:</td>
                            <td><input class="easyui-textbox" name="realName" data-options="required:true"
                                       value="${seller.realName}" style="width: 370px"/></td>
                        </tr>
                        <tr>
                            <td>电话:</td>
                            <td><input class="easyui-textbox" name="tel" data-options="required:true"
                                       value="${seller.tel}" style="width: 370px"/></td>
                        </tr>
                        <tr>
                            <td>QQ:</td>
                            <td><input class="easyui-textbox" name="qq" data-options="required:true"
                                       value="${seller.qq}" style="width: 370px"/></td>
                        </tr>
                    </table>
                    <div style="text-align:center;padding:10px">
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#userInfoFM').submit()">保存</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>