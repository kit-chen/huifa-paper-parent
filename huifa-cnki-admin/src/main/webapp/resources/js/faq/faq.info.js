
$(function () {
    $('#faqDataGrid').datagrid({
        title: '常见问题',
        width: 1200,
        height: 500,
        sortOrder: 'asc',
        singleSelect: true,
        method: 'post',
        url: PageCommon.contextPath + "/faq/list.xhtml",
        columns: [[
            {title: 'id', field: 'id', width: 0, hidden: true},
            {field: 'title', title: '标题', width: 80, align: 'center'},
            {field: 'content', title: '内容', width: 400, align: 'center'},
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
                    return "<a href='javascript:void(0);' onclick='deleteFaq(" + row.id + ")'>删除</a>";
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
                $('#updateFaq').window('open');
                $('#faqFormAction').val("add");
                $("input[name='title']", "#faqForm").val("");
                $("#content", "#faqForm").val("");
            }
        }, {
            id: 'btnUpdate',
            text: '修改',
            disabled: true,
            iconCls: 'icon-undo',
            handler: function () {
                var node = $('#faqDataGrid').datagrid('getSelected');
                if (node) {
                    loadOneFaq(node.id);
                    $('#faqFormAction').val("update");
                    $('#updateFaq').window('open');
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
function loadOneFaq(id){
    $.ajax({
        url: PageCommon.contextPath + "/faq/getById.xhtml",
        data: {"id": id},
        type: 'get',
        dataType: "json",
        success: function (data) {
            $("input[name='id']", "#faqForm").val(data.id);
            $("input[name='title']", "#faqForm").val(data.title);
            $("#content").textbox("setText",data.content);
        },
        fail: function (data) {
            $.messager.alert("请求失败", "添加数据时，请求失败，服务器出错，请重试。");
        }
    });
}

function deleteFaq(id){
    $.messager.confirm('温馨提示', '是否确定删除此问题?', function (result) {
        if (result) {
            $.ajax({
                url : PageCommon.contextPath + "/faq/deleteFaqById.xhtml",
                data : {"id":id},
                dataType : "json",
                type : 'get',
                success : function(data){
                    if(data.state == 'success'){
                        $.messager.alert("删除提示", "删除成功");
                        $('#faqDataGrid').datagrid("reload");
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
function faqFormSubmit(action) {
    if (action == 'add') {
        $('#faqForm').form('submit', {
            url: PageCommon.contextPath + '/faq/addFaq.xhtml',
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.state == 'success') {
                    closeUserWindow();
                    $('#faqDataGrid').datagrid("reload");
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
        $('#faqForm').form('submit', {
            url: PageCommon.contextPath + '/faq/updateFaq.xhtml',
            onSubmit: function () {
                return $(this).form('enableValidation').form('validate');
            },
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.state == 'success') {
                    closeUserWindow();
                    $('#faqDataGrid').datagrid("reload");
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
    $('#updateFaq').window('close');
}