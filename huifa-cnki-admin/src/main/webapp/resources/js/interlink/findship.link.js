
$(function () {
    $('#friendshipLinkDataGrid').datagrid({
        title: '友情链接',
        width: 700,
        height: 450,
        sortOrder: 'asc',
        singleSelect: true,
        method: 'post',
        url: PageCommon.contextPath + "/interlink/getList.xhtml",
        columns: [[
            {title: 'id', field: 'id', width: 0, hidden: true},
            {field: 'linkName', title: '链接名称', width: 80, align: 'center'},
            {field: 'link', title: '链接地址', width: 400, align: 'center'},
            {
                field: 'other', title: '操作', width: 60, align: 'center',
                formatter: function (value, row, index) {
                    return "<a href='javascript:void(0);' onclick='deleteLink(" + row.id + ")'>删除</a>";
                }
            }
        ]],
        pagination: true,
        rownumbers: true,
        onClickRow: function (index, row) {
            $("#btnUpdate").linkbutton("enable");
            $("#btnDel").linkbutton("enable");
            $("#id").val(row.id);
        },
        toolbar: [{
            id: 'btnadd',
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                $('#updateFriendshipLink').window('open');
                $('#friendshipLinkFormAction').val("add");
            }
        }, {
            id: 'btnUpdate',
            text: '修改',
            disabled: true,
            iconCls: 'icon-undo',
            handler: function () {
                var node = $('#friendshipLinkDataGrid').datagrid('getSelected');
                if (node) {
                    loadOneLink(node.id);
                    $('#friendshipLinkFormAction').val("update");
                    $('#updateFriendshipLink').window('open');
                } else {
                    jQuery.messager.alert('警告', '请选择一行');
                }
            }
        }]
    });
});

/**
 * 加载一条链接
 * @param id
 */
function loadOneLink(id){
    $.ajax({
        url: PageCommon.contextPath + "/interlink/getById.xhtml",
        data: {"id": id},
        type: 'get',
        dataType: "json",
        success: function (data) {
            $("input[name='id']", "#friendshipLinkForm").val(data.id);
            $("input[name='linkName']", "#friendshipLinkForm").val(data.linkName);
            $("input[name='link']", "#friendshipLinkForm").val(data.link);
        },
        fail: function (data) {
            $.messager.alert("请求失败", "添加数据时，请求失败，服务器出错，请重试。");
        }
    });
}

function deleteLink(id){
    $.messager.confirm('温馨提示', '是否确定删除此链接?', function (result) {
        if (result) {
            $.ajax({
                url : PageCommon.contextPath + "/interlink/deleteLinkById.xhtml",
                data : {"id":id},
                dataType : "json",
                type : 'get',
                success : function(data){
                    if(data.state == 'success'){
                        $.messager.alert("删除提示", "删除成功");
                        $('#friendshipLinkDataGrid').datagrid("reload");
                    }else{
                        $.messager.alert("删除提示", "删除失败");
                    }
                }
            });
        }
    });
}

/**
 * 用户信息表单提交 新增与修改
 * @param action
 */
function linkFormSubmit(action) {
    if (action == 'add') {
        $('#friendshipLinkForm').form('submit', {
            url: PageCommon.contextPath + '/interlink/addLink.xhtml',
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.state == 'success') {
                    closeUserWindow();
                    $('#friendshipLinkDataGrid').datagrid("reload");
                    $.messager.alert('新增提示', "添加成功");
                } else if (obj.state == 'exist') {
                    $.messager.alert('新增提示', "友情链接已经存在，请检查！");
                } else {
                    $.messager.alert('新增提示', "添加失败");
                }
            },
            fail: function (data) {
                $.messager.alert("请求失败", "添加数据时，请求失败，服务器出错，请重试。");
            }
        });
    } else if (action == 'update') {
        $('#friendshipLinkForm').form('submit', {
            url: PageCommon.contextPath + '/interlink/updateLink.xhtml',
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.state == 'success') {
                    closeUserWindow();
                    $('#friendshipLinkDataGrid').datagrid("reload");
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

function closeUserWindow() {
    $('#updateFriendshipLink').window('close');
}