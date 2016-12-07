<%--
  Created by IntelliJ IDEA.
  User: GuangRi
  Date: 2016/11/24
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index/agentManage.js"></script>
    <title>官网推广代理管理</title>
</head>
<body>
<div id="agentManageDataGrid"></div>

<div id="dlgcreateAgentUser" class="easyui-dialog" closed="true" title="新建代理商"
     style="width:400px;height:330px;">
    <p><label>手机号：</label><input class="easyui-textbox" id="userName" style="width: 150px;" data-options="prompt:'请输入代理商手机号'"></p>
    <p><label>&nbsp;&nbsp;&nbsp;密码：</label><input class="easyui-textbox" id="password" style="width: 150px;" data-options="prompt:'请输入代理商登录密码'"></p>
    <p><label>&nbsp;QQ号：</label><input class="easyui-textbox" id="qq" style="width: 150px;" data-options="prompt:'请输入代理商QQ号'"></p>
    <p><label>分成比例：</label><input class="easyui-numberspinner" value="5" id="ratio" data-options="increment:1,min:1,max:9" style="width:50px;">成</p>
    <p><label>支付宝账号：</label><input class="easyui-textbox" id="alipayAccount" style="width: 180px;" data-options="prompt:'请输入代理商支付宝账号'"></p>
    <p><label>&nbsp;&nbsp;&nbsp;备注：</label><input class="easyui-textbox" id="note" style="width: 80%;" data-options="prompt:'请输入备注信息'"></p>
    <p align="center">
        <a href="javascript:createAgentUser();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
        &nbsp;&nbsp;&nbsp;
        <a href="javascript:closeDialog('dlgcreateAgentUser');" class="easyui-linkbutton">取消</a>
    </p>
</div>

<div id="dlgModifyAgentUser" class="easyui-dialog" closed="true" title="修改代理商信息"
     style="width:400px;height:330px;">
    <p>
        <label>&nbsp;&nbsp;&nbsp;状态：</label>
        <select class="easyui-combobox" id="status" style="width: 100px;">
            <option value="0">注销</option>
            <option value="1">正常</option>
        </select>
    </p>
    <p><label>&nbsp;&nbsp;&nbsp;密码：</label><input class="easyui-textbox" id="newPassword" style="width: 150px;" data-options="prompt:'为空表示不修改登录密码'"></p>
    <p><label>&nbsp;QQ号：</label><input class="easyui-textbox" id="newQQ" style="width: 150px;" data-options="prompt:'请输入代理商QQ号'"></p>
    <p><label>分成比例：</label><input class="easyui-numberspinner" value="5" id="newRatio" data-options="increment:1,min:1,max:9" style="width:50px;">成</p>
    <p><label>支付宝账号：</label><input class="easyui-textbox" id="newAlipayAccount" style="width: 180px;" data-options="prompt:'请输入代理商支付宝账号'"></p>
    <p><label>&nbsp;&nbsp;&nbsp;备注：</label><input class="easyui-textbox" id="newNote" style="width: 80%;" data-options="prompt:'请输入备注信息'"></p>
    <p align="center">
        <a href="javascript:modifyAgentUser();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">修改</a>
        &nbsp;&nbsp;&nbsp;
        <a href="javascript:closeDialog('dlgModifyAgentUser');" class="easyui-linkbutton">取消</a>
    </p>
    <input type="hidden" id="modifyId">
</div>
</body>
</html>
