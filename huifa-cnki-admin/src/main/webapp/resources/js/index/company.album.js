
$(function () {
    $('#albumDataGrid').datagrid({
        title: '常见问题',
        width: 1200,
        height: 500,
        sortOrder: 'asc',
        singleSelect: true,
        method: 'post',
        url: PageCommon.contextPath + "/companyAlbum/list.xhtml",
        columns: [[
            {title: 'id', field: 'id', width: 0, hidden: true},
            {field: 'fileName', title: '文件名', width: 80, align: 'center'},
            {field: 'oriFileName', title: '原文件名', width: 400, align: 'center'},
            {field: 'filePath', title: '上传绝对地址', width: 400, align: 'center'},
            {
                field: 'createTime', title: '新增时间', width: 150, align: 'center',
                formatter: function (value, row, index) {
                    if (value)
                        return new Date(value).format("yyyy-MM-dd hh:mm:ss")
                }
            },
            {
                field: 'other', title: '操作', width: 60, align: 'center',
                formatter: function (value, row, index) {
                    return "<a href='javascript:void(0);' onclick='deleteAlbum(" + row.id + ")'>删除</a>";
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
                $('#updateAlbum').window('open');
                $('#albumFormAction').val("add");
                $("#id").val("0");
            }
        }]
    });
});

function deleteAlbum(id){
    $.messager.confirm('温馨提示', '是否确定删除此照片?', function (result) {
        if (result) {
            $.ajax({
                url : PageCommon.contextPath + "/companyAlbum/deleteById.xhtml",
                data : {"id":id},
                dataType : "json",
                type : 'get',
                success : function(data){
                    if(data.state == 'success'){
                        $.messager.alert("删除提示", "删除成功");
                        $('#albumDataGrid').datagrid("reload");
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
function albumFormSubmit(action) {
    if (action == 'add') {
        $('#albumForm').form('submit', {
            url: PageCommon.contextPath + '/companyAlbum/addAlbum.xhtml',
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.state == 'success') {
                    closeUserWindow();
                    $('#albumDataGrid').datagrid("reload");
                    $.messager.alert('新增提示', "添加成功");
                } else {
                    $.messager.alert('新增提示', "添加失败");
                }
            },
            fail: function (data) {
                $.messager.alert("请求失败", "添加数据时，请求失败，服务器出错，请重试。");
            }
        });
    } else if (action == 'update') {
        $('#albumForm').form('submit', {
            url: PageCommon.contextPath + '/companyAlbum/updateAlbum.xhtml',
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.state == 'success') {
                    closeUserWindow();
                    $('#albumDataGrid').datagrid("reload");
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
    $('#updateAlbum').window('close');
}