$(function () {
    $('#articleInfoDataGrid').datagrid({
        title: '文章管理',
        width: 1100,
        height: 450,
        sortOrder: 'asc',
        singleSelect: true,
        method: 'post',
        url: PageCommon.contextPath + "/article/getAllArticle.xhtml",
        columns: [[
            {title: 'id', field: 'id', width: 0, hidden: true},
            {field: 'title', title: '标题', width: 200, align: 'center'},
            {field: 'topicTypeName', title: '文章类别', width: 80, align: 'center'},
            {field: 'contentText', title: '内容', width: 600, align: 'center',nowrap : true},
            {field: 'itemImageUrl', title: '主图', width: 400, align: 'center',nowrap : false},
            {
                field: 'createTime', title: '提交时间', width: 120, align: 'center',
                formatter: function (value, row, index) {
                    if (value)
                        return new Date(value).format("yyyy-MM-dd hh:mm:ss")
                }
            },
            {
                field: 'other', title: '操作', width: 60, align: 'center',
                formatter: function (value, row, index) {
                    return "<a href='javascript:void(0);' onclick='deleteArticle(" + row.id + ")'>删除</a>";
                }
            }
        ]],
        pagination: true,
        rownumbers: true,
        onClickRow: function (index, row) {
            $("#btnUpdate").linkbutton("enable");
            $("#id").val(row.id);
        },
        toolbar: [{
            id: 'btnadd',
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                $('#updateArticle').window('open');
                $('#articleFormAction').val("add");
            }
        }, {
            id: 'btnUpdate',
            text: '修改',
            disabled: true,
            iconCls: 'icon-undo',
            handler: function () {
                var node = $('#articleInfoDataGrid').datagrid('getSelected');
                if (node) {
                    loadOneArticle(node.id);
                    $('#articleFormAction').val("update");
                    $('#updateArticle').window('open');
                } else {
                    jQuery.messager.alert('警告', '请选择一行');
                }
            }
        }]
    });
});

function closeUserWindow() {
    $('#updateArticle').window('close');
}

/**
 * 加载一条活动信息
 * @param id
 */
function loadOneArticle(id) {
    $.ajax({
        url: PageCommon.contextPath + "/article/getArticleById.xhtml",
        data: {"id": id},
        type: 'post',
        dataType: "json",
        success: function (data) {
            $("input[name='id']", "#articleForm").val(data.id);
            $("input[name='title']", "#articleForm").val(data.title);
            $("#type").combobox('setValue', data.type).combobox('setText', data.topicTypeName);
            editor.sync();
            editor.html(data.content);
        },
        fail: function (data) {
            $.messager.alert("请求失败", "添加数据时，请求失败，服务器出错，请重试。");
        }
    });
}

/**
 * 活动信息表单提交 新增与修改
 * @param action
 */
function articleFormSubmit(action) {
    var url = PageCommon.contextPath + "/article/";
    if (action == 'add') {
        url += "addArticle.xhtml";
    } else if (action == 'update') {
        url += "updateArticle.xhtml"
    }
    editor.sync();
    $("#hideContent").textbox('setValue', editor.html());
    if (action == 'add') {
        $('#articleForm').form('submit', {
            url: url,
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.state == 'success') {
                    closeUserWindow();
                    $('#articleInfoDataGrid').datagrid("reload");
                    $.messager.alert('新增提示', "添加成功");
                } else if (obj.state == 'exist') {
                    $.messager.alert('新增提示', "医生已经存在，请检查！");
                } else {
                    $.messager.alert('新增提示', "添加失败");
                }
            },
            fail: function (data) {
                $.messager.alert("请求失败", "添加数据时，请求失败，服务器出错，请重试。");
            }
        });
    } else if (action == 'update') {
        $('#articleForm').form('submit', {
            url: url,
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.state == 'success') {
                    closeUserWindow();
                    $('#articleInfoDataGrid').datagrid("reload");
                    $.messager.alert('修改提示', "修改成功");
                } else {
                    $.messager.alert('修改提示', "修改失败");
                }
            },
            fail: function (data) {
                $.messager.alert("请求失败", "修改数据时，请求失败，服务器出错，请重试。");
            }
        });
    }
}

function deleteArticle(id) {
    $.ajax({
        url: PageCommon.contextPath + "/article/deleteArticle.xhtml",
        data: {"id": id},
        dataType: 'json',
        type: 'post',
        success: function (data) {
            if (data.state == 'success') {
                $.messager.alert("删除提示", "删除成功");
                $('#articleInfoDataGrid').datagrid("reload");
            } else {
                $.messager.alert("删除提示", "删除失败");
            }
        }
    });
}