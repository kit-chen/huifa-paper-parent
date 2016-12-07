<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/28
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ztree/ztree.css"/>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:100px">
    <div class="searchPanel">
        <div class="condition"><span>角色名：</span><input id="name" type="text"></div>
        <div class="condition">
            <span>状态：</span>
            <select id="status">
                <option value="1">启用</option>
                <option value="0">停用</option>
            </select>
        </div>
    </div>
    <div class="btnPanel">
        <a href="#" class="easyui-linkbutton" onclick="doSearch()" icon="icon-search">查询</a>
        <a href="#" class="easyui-linkbutton" onclick="doclear()" icon="icon-clear">清空</a>
        <a href="#" class="easyui-linkbutton" onclick="openAddPanel()" icon="icon-add">新增</a>
        <a href="#" class="easyui-linkbutton" onclick="openModulePanel()" icon="icon-add">菜单管理</a>
    </div>
</div>
<div data-options="region:'center'">
    <table id="roleTable" class="easyui-datagrid"  style="width:100%;height:100%"
           data-options="singleSelect:true,collapsible:true,url:'/sysrole/getList.xhtml',method:'post',pagination:true,
				pageSize:50">
        <thead>
        <tr>
            <th data-options="field:'id',width:80,align:'center'">id</th>
            <th data-options="field:'name',width:150,align:'center'">角色名</th>
            <th data-options="field:'roleCount',align:'center'">权限</th>
            <th data-options="field:'status',width:60,align:'center'">状态</th>
            <th data-options="field:'action',width:120,align:'center',formatter:actionFmt">操作</th>
        </tr>
        </thead>
    </table>
</div>
<div id="win" class="easyui-window" closed="true" title="角色信息" style="width:350px;height:400px;display:none;">
    <form style="padding:10px 20px 10px 40px;">
        <table class="addTable" >
            <tr>
                <td class="title">角色名:</td>
                <td><input type="hidden" id="nid"> <input id="nname"  type="text"></td>
            </tr>
            <tr>
                <td class="title">状  态: </td>
                <td>
                    <select id="nstatus">
                        <option value="1">启用</option>
                        <option value="0">停用</option>
                    </select>
                </td>
            </tr>
            <tr style="height:200px;vertical-align: top;">
                <td colspan="2" >
                    <ul id="menus" class="ztree"></ul>
                </td>
            </tr>
            <tr >
                <td colspan="2" class="buttons">
                    <a href="#" class="easyui-linkbutton" onclick="doCreateOrUpdate()" icon="icon-ok">保存</a>
                    <a href="#" class="easyui-linkbutton" onclick="closeAddPanel()" icon="icon-cancel">取消</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="moduleWin" class="easyui-window" closed="true" title="功能信息" style="width:550px;height:400px;display:none;">
    <form style="padding:10px 20px 10px 40px;">
        <table class="addTable" >
            <tr  style="vertical-align: top;">
                <td rowspan="6" colspan="3" style="width:200px;height:300px;border-right:1px solid;" >
                    <ul id="modules" class="ztree"></ul>
                </td>
            </tr>
            <tr>
                <td class="title">上级菜单:</td>
                <td><input type="hidden" id="mparentId"><input id="mparent" type="text" readonly="readonly"></td>
            </tr>
            <tr>
                <td class="title">菜单名:</td>
                <td><input type="hidden" id="mid"> <input id="mname"  type="text"></td>
            </tr>
            <tr>
                <td class="title">URL:</td>
                <td><input id="mUrl"  type="text"></td>
            </tr>
            <tr>
                <td class="title">菜单类型:</td>
                <td>
                    <select id="mtype">
                        <option value="1">系统链接</option>
                        <option value="2">外部链接</option>
                    </select>

                </td>
            </tr>
            <tr >
                <td colspan="2" class="buttons">
                    <a href="#" class="easyui-linkbutton" onclick="toAddModule()" icon="icon-ok">新增</a>
                    <a href="#" class="easyui-linkbutton" onclick="saveModule()" icon="icon-ok">保存</a>
                    <a href="#" class="easyui-linkbutton" onclick="closeModulePanel()" icon="icon-cancel">取消</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sysrole/list.js"></script>
</body>
</html>
