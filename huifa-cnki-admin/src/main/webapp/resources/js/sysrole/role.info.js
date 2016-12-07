$(function () {
    $('#roleGrid').datagrid({
        title: '角色信息',
        nowrap: false,
        width: 700,
        height: 450,
        resizable: true,
        autoRowHeight: false,
        striped: true,
        collapsible: true,
        sortOrder: 'asc',
        remoteSort: false,
        singleSelect: true,
        method: 'post',
        url: projectName + '/sysrole/getList.xhtml',
        ollapsible: false,
        columns: [[
            {field: 'id', title: 'id', width: 50, hidden: 'true'},
            {field: 'name', title: '角色名称', width: 220, align: 'center'}
            // {field: 'status', title: '角色状态', width: 100, align: 'center',
            //     formatter : function (value) {
            //         if(value == '0') return "停用";
            //         if(value == '1') return "启用";
            //     }
            // }
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
                $('#updateRole').window('open');
                $('#roleForm').form('clear');
                $('#roleFormAction').val("add");
                $("input[name='roleCode']", "#roleForm").removeAttr("readonly");
            }
        }, {
            id: 'btnUpdate',
            text: '修改',
            disabled: true,
            iconCls: 'icon-undo',
            handler: function () {
                var node = $('#roleGrid').datagrid('getSelected');
                if (node) {
                    loadOneRole(node.id);
                    $("input[name='roleCode']", "#roleForm").attr("readonly", "readonly");
                    $('#roleFormAction').val("update");
                    $('#updateRole').window('open');
                } else {
                    jQuery.messager.alert('警告', '请选择一行');
                }
            }
        }, {
            id: 'btnDel',
            text: '删除',
            disabled: true,
            iconCls: 'icon-cut',
            handler: function () {
                var node = $('#roleGrid').datagrid('getSelected');
                if (node) {
                    $.messager.confirm('温馨提示', '是否确定删除此用户?', function (result) {
                        if (result) {
                            deleteOneRole(node.id);
                        }
                    });
                } else {
                    jQuery.messager.alert('警告', '请选择一行');
                }
            }
        }]
    });
});

function closeUserWindow() {
    $('#updateRole').window('close');
}

/**
 * 导入用户信息进行修改
 * @param operatorId
 */
function loadOneRole(id) {
    $.ajax({
        url: PageCommon.contextPath + "/sysrole/get.xhtml",
        data: {"roleId": id},
        type: 'POST',
        dataType: "json",
        success: function (data) {
            $("input[name='id']", "#roleForm").val(data.id);
            // $("input[name='roleCode']", "#roleForm").val(data.roleCode);
            $("input[name='name']", "#roleForm").val(data.name);
        },
        fail: function (data) {
            $.messager.alert("请求失败", "添加数据时，请求失败，服务器出错，请重试。");
        }
    });
}

function deleteOneRole(id) {
    $.ajax({
        url: PageCommon.contextPath + "/sysrole/delete.xhtml",
        data: {"roleId": id},
        dataType: "json",
        success: function (data) {
            closeUserWindow();
            $('#roleGrid').datagrid("reload");
            $.messager.alert('删除提示', data.msg);
        },
        fail: function (data) {
            $.messager.alert("请求失败", "删除数据时，请求失败，服务器出错，请重试。");
        }
    });
}

/**
 * 用户信息表单提交 新增与修改
 * @param action
 */
function roleFormSubmit(action) {
    if (action == 'add') {
        $('#roleForm').form('submit', {
            url: PageCommon.contextPath + '/sysrole/createOrUpdate.xhtml',
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.result == 'success') {
                    closeUserWindow();
                    $('#roleGrid').datagrid("reload");
                    $.messager.alert('新增提示', "添加成功");
                } else if (obj.result == 'exist') {
                    $.messager.alert('新增提示', "角色编码已经存在，请检查！");
                } else {
                    $.messager.alert('新增提示', "添加失败");
                }
            },
            fail: function (data) {
                $.messager.alert("请求失败", "添加数据时，请求失败，服务器出错，请重试。");
            }
        });
    } else if (action == 'update') {
        $('#roleForm').form('submit', {
            url: PageCommon.contextPath + '/sysrole/createOrUpdate.xhtml',
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.result == 'success') {
                    closeUserWindow();
                    $('#roleGrid').datagrid("reload");
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