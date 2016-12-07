<%--
  Created by IntelliJ IDEA.
  User: kchen
  Date: 2016/10/9
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/css/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/css/icon.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/css/demo.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sysuser/admin.info.js"></script>
    <title>后台用户管理</title>
</head>
<body>
<table id="adminInfoDataGrid"></table>
<div id="updateAdmin" class="easyui-window" data-options="closed:true,modal:true,title:'新增/修改用户',
	        minimizable:false,maximizable:false,resizable:false" style="width:540px;height:300px;">
    <div style="height:8%;width:auto"></div>
    <div id="info">
        <form method="post" id="adminForm">
            <table>
                <tr>
                    <input type="hidden" name="id" id="id" value="0"/>
                    <td style="text-indent:5em;"><span class="title">登录名：</span></td>
                    <td>
                        <input class="easyui-validatebox" name="loginName" type="text"
                               data-options="required:true,validType:'length[1,10]'"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-indent:5em;"><span class="title">登录密码：</span></td>
                    <td>
                        <input class="easyui-validatebox" name="password" type="text" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-indent:5em;"><span class="title">用户名称：</span></td>
                    <td>
                        <input class="easyui-validatebox" name="name" type="text" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-indent:5em;"><span class="title">用户手机：</span></td>
                    <td>
                        <input class="easyui-validatebox" name="phone" type="tel" data-options="required:true,validType:'length[1,11]'"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-indent:5em;"><span class="title">用户角色：</span></td>
                    <td>
                        <input class="easyui-combobox" value="请选择" style="width:150px;" name="roleId"
                               data-options="url:'${pageContext.request.contextPath}/sysrole/getRoleText.xhtml',method:'get', valueField:'id', textField:'text', panelHeight:'auto',editable:false,required:true">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="clear:both;height:3%;width:auto">
        <input type="hidden" id="adminFormAction" value="add">
    </div>
    <div data-options="region:'south',border:false"
         style="text-align:center;padding:5px 0;margin-right:45px;;margin-bottom:30px">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)"
           onclick="adminFormSubmit($('#adminFormAction').val())">提交</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)"
           onclick="closeUserWindow()">关闭</a>
    </div>
</div>
</div>
</body>
</html>