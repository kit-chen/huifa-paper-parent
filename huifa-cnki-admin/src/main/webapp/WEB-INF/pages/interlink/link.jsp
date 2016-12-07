<%--
  Created by IntelliJ IDEA.
  User: kchen
  Date: 2016/10/13
  Time: 16:05
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/interlink/findship.link.js"></script>
    <title>友情链接管理</title>
</head>
<body>
<table id="friendshipLinkDataGrid"></table>

<div id="updateFriendshipLink" class="easyui-window" data-options="closed:true,modal:true,title:'新增/修改友情链接',
	        minimizable:false,maximizable:false,resizable:false" style="width:700px;height:450px;">
    <div style="height:8%;width:auto"></div>
    <div id="info">
        <form method="post" id="friendshipLinkForm">
            <table>
                <tr>
                    <input type="hidden" name="id" id="id" value="0"/>
                    <td style="text-indent:5em;"><span class="title">链接名称：</span></td>
                    <td>
                        <input class="easyui-validatebox" name="linkName" type="text"
                               style="width: 300px;line-height: 30px; height: 30px;"
                               data-options="required:true,validType:'length[1,10]'"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-indent:5em;"><span class="title">地址：</span></td>
                    <td>
                        <input class="easyui-validatebox" name="link" type="url"
                               style="width: 300px;line-height: 30px; height: 30px;"
                               placeholder="请输入以http开头的链接地址" data-options="required:true"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="clear:both;height:3%;width:auto">
        <input type="hidden" id="friendshipLinkFormAction" value="add">
    </div>
    <div data-options="region:'south',border:false"
         style="text-align:center;padding:5px 0;margin-right:45px;;margin-bottom:30px">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)"
           onclick="linkFormSubmit($('#friendshipLinkFormAction').val())">提交</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)"
           onclick="closeUserWindow()">关闭</a>
    </div>
</div>
</div>
</body>
</html>