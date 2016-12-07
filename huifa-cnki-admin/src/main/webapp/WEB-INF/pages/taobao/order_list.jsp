<%--
  Created by IntelliJ IDEA.
  User: GuangRi
  Date: 2016/11/25
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" type="text/css" rel="stylesheet"/>
    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui.css" rel="stylesheet"/>
    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/icon.css" rel="stylesheet"/>
    <link type="text/css" href="${pageContext.request.contextPath}/resources/css/demo.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/jquery/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/taobao/order_list.js"></script>
    <title>淘宝代理订单查询</title>
</head>
<body>
<div id="divSearch">
    <div>
        <label>订单编号：</label>
        <input id="tid" class="easyui-textbox" prompt="请输入淘宝订单编号" style="width:150px;height:32px">
    </div>
    <div style="margin-top: 2px;">
        <label>买家昵称：</label><input id="buyerNick" class="easyui-textbox" prompt="请输入淘宝买家昵称" style="width:150px;height:32px;">
    </div>
    <div style="margin-top: 2px;">
        <label>卖家昵称：</label><input id="sellerNick" class="easyui-textbox" prompt="请输入淘宝卖家昵称" style="width:150px;height:32px;">
    </div>
    <div style="margin-top: 2px;margin-bottom: 2px;">
        <label>购买时间：</label><input id="startTime" class="easyui-datebox" style="height: 32px;">~<input id="endTime" class="easyui-datebox" style="height: 32px;">
        <a href="javascript:searchTaobaoOrder();" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px;height: 32px;">查询</a>
    </div>
</div>

<table id="taobaoOrderDataGrid"></table>
</body>
</html>
