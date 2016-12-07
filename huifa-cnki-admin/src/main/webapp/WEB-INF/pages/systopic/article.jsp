<%--
  Created by IntelliJ IDEA.
  User: kchen
  Date: 2016/10/10
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/css/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/css/icon.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/css/demo.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/jquery/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/jquery/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
    <script charset="UTF-8" src="${pageContext.request.contextPath}/resources/js/kindedit/kindeditor.js"></script>
    <script charset="UTF-8" src="${pageContext.request.contextPath}/resources/js/kindedit/lang/zh_CN.js"></script>
    <script>
        // 关闭过滤模式，保留所有标签
        var editor;
        KindEditor.options.filterMode = false;
        KindEditor.ready(function (K) {
            editor = K.create('#content', {
                <%--uploadJson: '${pageContext.request.contextPath}/kindEditor/fileUpload.xhtml',--%>
                <%--fileManagerJson: '${pageContext.request.contextPath}/kindEditor/fileManager.xhtml',--%>
                allowFileManager: true,
                allowImageUpload: true,
                afterCreate: function () {
                    this.loadPlugin('autoheight');
                },
                afterBlur: function () {
                    this.sync();
                }  //Kindeditor下获取文本框信息
            });
        });
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/systopic/article.info.js"></script>
    <title>文章管理</title>
</head>
<body>
<table id="articleInfoDataGrid"></table>
<div id="updateArticle" class="easyui-window" data-options="closed:true,modal:true,title:'新增/修改文章',
	        minimizable:false,maximizable:true,resizable:true,onResize:function(){$(this).dialog('center');}"
     style="width:100%;height:500px;">
    <div style="height:8%;width:auto"></div>
    <div id="info">
        <form method="post" id="articleForm" enctype="multipart/form-data">
            <table>
                <tr>
                    <input type="hidden" name="id" id="id" value="0"/>
                    <td>文章标题：</td>
                    <td>
                        <input class="easyui-validatebox" name="title" type="text"
                               style="line-height:30px;height:30px;width: 450px;"
                               data-options="required:true,validType:'length[1,30]'"/>
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<td>文章主图：</td>--%>
                    <%--<td>--%>
                        <%--<input class="easyui-filebox" name="itemImageFile" data-options="prompt:'选择文件'"--%>
                               <%--style="line-height:30px;height:30px;width: 450px;">--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <td>文章分类：</td>
                    <td>
                        <input name="topicType" id="type" class="easyui-combobox" style="width: 200px;"
                               data-options="required:'true',valueField:'id',textField:'typeName',url:'${pageContext.request.contextPath}/sysTopicType/getAllType.xhtml'">
                    </td>
                </tr>
                <tr>
                    <td>文章内容：</td>
                    <td>
                        <textarea id="content" style="width:700px;height:300px;visibility:hidden;"></textarea>
                        <input class="easyui-textbox" name="content" id="hideContent" type="hidden">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="clear:both;height:3%;width:auto">
        <input type="hidden" id="articleFormAction" value="add">
    </div>
    <div data-options="region:'south',border:false"
         style="text-align:center;padding:5px 0;margin-right:45px;;margin-bottom:30px">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)"
           onclick="articleFormSubmit($('#articleFormAction').val())">提交</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)"
           onclick="closeUserWindow()">关闭</a>
    </div>
</div>
</div>
</body>
</html>