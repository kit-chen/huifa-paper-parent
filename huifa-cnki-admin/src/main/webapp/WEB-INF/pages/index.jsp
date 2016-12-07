<%--
  Created by IntelliJ IDEA.
  User: kchen
  Date: 2016/10/8
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PaperTime后台管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/demo.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index/index.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height:80px;background:#B3DFDA;padding:10px;text-align: center;">
    CNKI 后台管理
    欢迎：<span style="color: rgb(255,0,0)">${adminUser.name}</span>
    <a href="javascript:void(0);" onclick="$('#dlg').dialog('open');"></a>
    <a href="${pageContext.request.contextPath}/admin/loginOut.xhtml"></a>
    <c:if test="${fn:length(baseMenu) > 0}">
        <a id="baseBtn" class="easyui-linkbutton" onclick="baseMenu()">基础菜单</a>
    </c:if>
    <c:if test="${fn:length(sysMenu) > 0}">
        <a id="systemBtn" class="easyui-linkbutton" style="color: #ff0000" onclick="systemMenu()">系统菜单</a>
    </c:if>
</div>
<div data-options="region:'west',split:true,title:'导航菜单'" style="width:300px;padding:10px;">
    <c:if test="${fn:length(baseMenu) > 0}">
        <div id="baseMenu" class="easyui-accordion"
             style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">
            <div title="基础管理" iconcls="icon-sum" style="overflow: auto; padding: 10px;border-bottom: none;" selected="true">
                <ul class="easyui-tree">
                    <li>
                        <span>基础管理</span>
                        <ul>
                            <c:forEach items="${baseMenu}" var="base">
                                <li>
                                    <span>
                                        <a onclick="addTab('${base.moduleName}','${pageContext.request.contextPath}${base.moduleUrl}')">${base.moduleName}</a>
                                    </span>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </c:if>
    <c:if test="${fn:length(sysMenu) > 0}">
        <div id="systemMenu" class="easyui-accordion"
             style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">
            <div title="系统管理" iconcls="icon-sum" style="overflow: auto; padding: 10px;" selected="false">
                <ul class="easyui-tree">
                    <li>
                        <span>系统管理</span>
                        <ul>
                            <c:forEach items="${sysMenu}" var="base">
                                <li>
                                    <span>
                                        <a onclick="addTab('${base.moduleName}','${pageContext.request.contextPath}${base.moduleUrl}')">${base.moduleName}</a>
                                    </span>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </c:if>
</div>
<%--<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region--%>
<%--</div>--%>
<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;text-align: center">
    Copyright © CNKI
</div>

<div id="mainPanle" data-options="region:'center',title:''" style="overflow: hidden;">
    <div id="tabs" class="easyui-tabs" fit=true border="false"></div>
    <div id="mm" class="easyui-menu" style="width: 150px;">
        <div id="mm-tabclose">关闭</div>
        <div id="mm-tabcloseall">全部关闭</div>
        <div id="mm-tabcloseother">除此之外全部关闭</div>
        <div class="menu-sep"></div>
        <div id="mm-tabcloseright">当前页右侧全部关闭</div>
        <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
    </div>
</div>
<div style="display: none;">
    <input type="hidden" name="userType" value="${adminUser.userType}">
</div>
</body>
</html>