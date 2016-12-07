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
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/taobao/taobao_agent_list.js"></script>
    <style>
        .rechargeMoney {
            border: 1px solid #aeacac;
            height: 35px;
            line-height: 35px;
            font-size: 14px;
            padding-left: 5px;
            box-sizing: border-box;
            display: inline-block;
            vertical-align: middle;
        }
    </style>
    <title>淘宝代理管理</title>
</head>
<body>
<div id="divSearch">
    <div style="margin-top: 2px;">
        <label>淘宝昵称：</label><input id="sellerNick" class="easyui-textbox" prompt="请输入淘宝代理昵称" style="width:150px;height:32px;">
    </div>
    <div style="margin-top: 2px;margin-bottom: 2px;">
        <label>授权时间：</label><input id="startTime" class="easyui-datebox" style="height: 32px;">~<input id="endTime" class="easyui-datebox" style="height: 32px;">
        <a href="javascript:searchTaobaoAgent();" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px;height: 32px;">查询</a>
    </div>
</div>

<table id="taobaoAgentDataGrid"></table>

<div id="dlgUpdateBalance" class="easyui-dialog" closed="true" title="更新账户余额"
     style="width:350px;height:250px;">
    <p style="margin-left: 15px;"><label>可用币数余额：<b id="currentBalance">0</b>&nbsp;金币</label></p>
    <p style="margin-left: 15px;"><label>累计充值金额：<b id="currentRechargeMoney">0</b>&nbsp;元</label></p>
    <p style="margin-left: 15px;"><label>添加充值金额：</label><input type="number" placeholder="请输入4的倍数" id="addRechargeMoney" step="4" min="4" class="rechargeMoney" required="required">&nbsp;元</p>
    <p style="margin-left: 15px;"><label>新增可用币数：<span id="addBalance">0</span>&nbsp;金币</label></p>
    <p align="center">
        <a href="javascript:updateBalance();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
        &nbsp;&nbsp;&nbsp;
        <a href="javascript:closeDialog();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
    </p>
    <input type="hidden" id="rowId">
    <input type="hidden" id="rowSellerNick">
</div>
</body>
</html>