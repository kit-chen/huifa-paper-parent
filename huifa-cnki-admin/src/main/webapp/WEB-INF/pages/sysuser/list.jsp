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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/plugins/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/plugins/jquery-easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common.css">
</head>
<body class="easyui-layout">
    <div data-options="region:'north'" style="height:100px">
        <div class="searchPanel">
            <div class="condition"><span>用户名：</span><input id="name" type="text"></div>
            <div class="condition"><span>登录名：</span><input id="loginName" type="text"></div>
            <div class="condition"><span>电话：</span><input id="phone" type="text"></div>
            <div class="condition">
                <span>角色：</span>
                <select  id="roleId">
                </select>
            </div>
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
        </div>
    </div>
    <div data-options="region:'center'">
        <table id="userTable" class="easyui-datagrid"  style="width:100%;height:100%"
               data-options="singleSelect:true,collapsible:true,url:'/sysuser/getList.xhtml',method:'post',pagination:true,
				pageSize:50">
            <thead>
            <tr>
                <th data-options="field:'id',width:80,align:'center'">id</th>
                <th data-options="field:'name',width:150,align:'center'">用户名</th>
                <th data-options="field:'loginName',width:150,align:'center'">登录名</th>
                <th data-options="field:'phone',width:150,align:'center'">电话号码</th>
                <th data-options="field:'roleName',width:120,align:'center'">角色</th>
                <th data-options="field:'status',width:60,align:'center'">状态</th>
                <th data-options="field:'action',width:120,align:'center',formatter:actionFmt">操作</th>
            </tr>
            </thead>
        </table>
    </div>
    <div id="win" class="easyui-window" closed="true" title="用户信息" style="width:350px;height:350px;display:none;">
        <form style="padding:10px 20px 10px 40px;">
            <table class="addTable" >
                <tr>
                    <td class="title">用户名:</td>
                    <td><input type="hidden" id="nid"> <input id="nname"  type="text"></td>
                </tr>
                <tr>
                    <td class="title">登录名:</td>
                    <td><input  id="nloginName" type="text"></td>
                </tr>
                <tr class="pwd">
                    <td class="title">密  码: </td>
                    <td><input id="npassword" type="password"></td>
                </tr>
                <tr class="pwd">
                    <td class="title">确认密码: </td>
                    <td><input id="rppassword" type="password"></td>
                </tr>
                <tr>
                    <td class="title">电  话:</td>
                    <td><input id="nphone" type="text"></td>
                </tr>
                <tr>
                    <td class="title">角  色:</td>
                    <td>
                        <select id="nroleId">
                            <option value="1">启用</option>
                            <option value="0">停用</option>
                        </select>
                    </td>
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
                <tr >
                    <td colspan="2" class="buttons">
                        <a href="#" class="easyui-linkbutton" onclick="doCreateOrUpdate()" icon="icon-ok">保存</a>
                        <a href="#" class="easyui-linkbutton" onclick="closeAddPanel()" icon="icon-cancel">取消</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/plugins/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/plugins/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sysuser/list.js"></script>
</body>
</html>
