<%--
  Created by IntelliJ IDEA.
  User: GuangRi
  Date: 2016/11/23
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/css/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/css/icon.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/css/demo.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/jquery/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index/stats.js"></script>
    <title>网站数据统计</title>
</head>
<body>
<table>
    <tr>
        <td>选择日期：</td>
        <td>
            <input id="startTime" class="easyui-datebox" data-options="sharedCalendar:'#cc'">
        </td>
        <td>~</td>
        <td>
            <input id="endTime" class="easyui-datebox" data-options="sharedCalendar:'#cc'">
        </td>
        <td><a href="javascript:searchStatsData();" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a></td>
    </tr>
</table>
<div id="cc" class="easyui-calendar"></div>
<div style="font-size: 18px;font-family: 'Microsoft YaHei';color: red;">
    注意：此页面比较特殊，需要选择好日期查询才会显示结果
</div>
<div id="divStatsData" style="margin: 20px;font-family: 'Microsoft YaHei';">
    <%--<div style="line-height: 35px;font-size: 16px;">
        查询时间区间为：2016-11-22 00:00:00~~2016-11-23 23:59:59
    </div>--%>
</div>
</body>
</html>

