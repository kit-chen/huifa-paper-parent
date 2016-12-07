$(function () {
    $('#adminInfoDataGrid').datagrid({
        title: '后台用户管理',
        width: 1200,
        height: 450,
        sortOrder: 'asc',
        singleSelect: true,
        method: 'post',
        url: PageCommon.contextPath + "/sysuser/getList.xhtml",
        columns: [[
            {title: 'id', field: 'id', width: 0, hidden: true},
            {title: 'roleId', field: 'roleId', width: 0, hidden: true},
            {field: 'loginName', title: '用户账户', width: 120, align: 'center'},
            {field: 'name', title: '用户姓名', width: 120, align: 'center'},
            {field: 'phone', title: '用户手机', width: 120, align: 'center'},
            {field: 'roleName', title: '角色', width: 150, align: 'center'},
            {
                field: 'other', title: '操作', width: 60, align: 'center',
                formatter: function (value, row, index) {
                    if(row.roleId != '0'){
                        return "<a href='javascript:void(0);' onclick='deleteAdmin(" + row.id + ")'>删除</a>";
                    }
                }
            }
        ]],
        pagination: true,
        rownumbers: true,
        onClickRow: function (index, row) {
            if(row.roleId != 0){
                $("#btnUpdate").linkbutton("enable");
                $("#id").val(row.id);
            }
        },
        toolbar: [{
            id: 'btnadd',
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                $('#updateAdmin').window('open');
                $('#adminFormAction').val("add");
            }
        }, {
            id: 'btnUpdate',
            text: '修改',
            disabled: true,
            iconCls: 'icon-undo',
            handler: function () {
                var node = $('#adminInfoDataGrid').datagrid('getSelected');
                if (node) {
                    loadOneAdmin(node.id);
                    $('#adminFormAction').val("update");
                    $('#updateAdmin').window('open');
                } else {
                    jQuery.messager.alert('警告', '请选择一行');
                }
            }
        }]
    });
});

/**
 * 加载一条用户信息
 * @param id
 */
function loadOneAdmin(id){
    $.ajax({
        url: PageCommon.contextPath + "/admin/getAdminById.xhtml",
        data: {"id": id},
        type: 'get',
        dataType: "json",
        success: function (data) {
            $("input[name='id']", "#adminForm").val(data.id);
            $("input[name='loginName']", "#adminForm").val(data.adminCode);
            $("input[name='name']", "#adminForm").val(data.adminName);
            $("input[name='phone']", "#adminForm").val(data.phone);
            $("input[name='roleId']", "#adminForm").combobox('setValue', data.roleId);
        },
        fail: function (data) {
            $.messager.alert("请求失败", "添加数据时，请求失败，服务器出错，请重试。");
        }
    });
}

function deleteAdmin(id){
    $.messager.confirm('温馨提示', '是否确定删除此用户?', function (result) {
        if (result) {
            $.ajax({
                url : PageCommon.contextPath + "/sysuser/delete.xhtml",
                data : {"id":id},
                dataType : "json",
                type : 'get',
                success : function(data){
                    if(data.result == 'success'){
                        $.messager.alert("删除提示", "删除成功");
                        $('#adminInfoDataGrid').datagrid("reload");
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
function adminFormSubmit(action) {
    if (action == 'add') {
        $('#adminForm').form('submit', {
            url: PageCommon.contextPath + '/sysuser/createOrUpdate.xhtml',
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.result == 'success') {
                    closeUserWindow();
                    $('#adminInfoDataGrid').datagrid("reload");
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
        $('#adminForm').form('submit', {
            url: PageCommon.contextPath + '/sysuser/createOrUpdate.xhtml',
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.result == 'success') {
                    closeUserWindow();
                    $('#adminInfoDataGrid').datagrid("reload");
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
    $('#updateAdmin').window('close');
}