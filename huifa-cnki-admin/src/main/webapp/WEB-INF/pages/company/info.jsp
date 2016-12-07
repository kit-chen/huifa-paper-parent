<%--
  Created by IntelliJ IDEA.
  User: kchen
  Date: 2016/10/24
  Time: 10:34
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/index/company.album.js"></script>
    <title>公司相册管理</title>
</head>
<body>
<table id="albumDataGrid"></table>

<div id="updateAlbum" class="easyui-window" data-options="closed:true,modal:true,title:'新增公司照片',
	        minimizable:false,maximizable:false,resizable:false" style="width:700px;height:450px;">
    <div style="height:8%;width:auto"></div>
    <div id="info">
        <form method="post" id="albumForm" enctype="multipart/form-data">
            <table>
                <tr>
                    <input type="hidden" name="id" id="id" value="0"/>
                </tr>
                <tr>
                    <td>单张图片：</td>
                    <td>
                        <input class="easyui-filebox" name="albumFile" data-options="prompt:'选择文件'"
                               style="line-height:30px;height:30px;width: 450px;">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        请上传宽：853px    高：506px大小的图片，否则页面展示不好看
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="clear:both;height:3%;width:auto">
        <input type="hidden" id="albumFormAction" value="add">
    </div>
    <div data-options="region:'south',border:false"
         style="text-align:center;padding:5px 0;margin-right:45px;;margin-bottom:30px">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)"
           onclick="albumFormSubmit($('#albumFormAction').val())">提交</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)"
           onclick="closeUserWindow()">关闭</a>
    </div>
</div>
</div>
</body>
</html>