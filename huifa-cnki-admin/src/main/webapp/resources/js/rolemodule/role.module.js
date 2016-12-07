$(function () {
    $('#roleGrid').datagrid({
        title: '权限信息',
        nowrap: false,
        width: 500,
        height: 450,
        resizable: true,
        autoRowHeight: false,
        striped: true,
        collapsible: true,
        sortOrder: 'asc',
        remoteSort: false,
        singleSelect: true,
        url: projectName + '/sysrole/getList.xhtml',
        ollapsible: false,
        columns: [[
            {field: 'id', title: 'id', width: 50, hidden: 'true'},
            {field: 'name', title: '角色名称', width: 220, align: 'center'}
        ]],
        pagination: true,
        rownumbers: true,
        onClickRow: function (index, row) {
            initRoleModuleInfo(row.id);
            $("#module").html("");
            initRoleModuleTree(row.id);
        }
    });
});

/**
 * 初始化角色权限信息
 * @param roleId
 */
function initRoleModuleInfo(roleId) {
    $.ajax({
        url: PageCommon.contextPath + "/sysRoleModule/initRoleModule.xhtml",
        data: {"roleId": roleId},
        type: 'post',
        dataType: 'json'
    });
}

/**
 * 加载用户权限信息
 * @param id
 */
function initRoleModuleTree(id) {
    $("#module").tree({
        url: PageCommon.contextPath + '/sysRoleModule/initMenuTree.xhtml?roleId=' + id,
        method: 'get',
        animate: true,
        checkbox: true,
        onlyLeafCheck: true,
        onCheck: function (node, checked) {
            $.ajax({
                url: PageCommon.contextPath + '/sysRoleModule/updateRoleModuleById.xhtml',
                type: "post",
                data: {"roleId": id, "moduleId": node.id, "checked": checked},
                dataType: "json",
                success: function (data) {
                    if (data.result == '0') {
                        $.messager.alert('警告', data.msg);
                    }
                },
                fail: function (data) {
                    $.messager.alert("请求失败", "添加数据时，请求失败，服务器出错，请重试。");
                }
            });
        }
    });
}