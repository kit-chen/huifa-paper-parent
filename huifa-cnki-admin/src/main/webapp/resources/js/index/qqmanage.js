/**
 * Created by GuangRi on 2016/11/22.
 */
$(function () {
    $('#qqDataGrid').datagrid({
        title: '客服QQ管理',
        width: 1200,
        height: 500,
        sortOrder: 'asc',
        singleSelect: false,
        checkOnSelect: false,
        pagination: false,
        rownumbers: true,
        method: 'post',
        url: PageCommon.contextPath + "/qqcustomerservice/getallqq.xhtml",
        columns: [[
            {field: 'id', title: 'id', width: 0, hidden: true},
            {field: 'qqNumber', title: 'QQ号', width: 110, align: 'center'},
            {field: 'qqName', title: 'QQ名称', width: 150, align: 'center'},
            {
                field: 'qqType', title: '类型', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    if (value == '0') {
                        value = '人工';
                    } else {
                        value = '机器';
                    }
                    return value;
                }
            },
            {field: 'weight', title: '权重', width: 50, align: 'center'},
            {
                field: 'status', title: '状态', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    if (value == '0') {
                        value = '下线';
                    } else {
                        value = '在线';
                    }
                    return value;
                }
            },
            {
                field: 'other', title: '操作', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    if (row.status == '0') {
                        return "<a href='javascript:void(0);' onclick='updateQQStatus(\"" + row.qqNumber + "\", " + row.status + ")'>上线</a>";
                    } else {
                        return "<a href='javascript:void(0);' onclick='updateQQStatus(\"" + row.qqNumber + "\", " + row.status + ")'>下线</a>";
                    }
                }
            }
        ]]
    });
});

function updateQQStatus(qqNumber, status) {
    $.messager.confirm('切换QQ状态', '您确定要更新该QQ状态？', function (choice) {
        if (choice) {
            $.ajax({
                url: PageCommon.contextPath + "/qqcustomerservice/updatestatus.xhtml",
                data: {
                    'qqNumber': qqNumber,
                    'status': status
                },
                type: 'post',
                dataType: "json",
                success: function (data) {
                    if (data.respCode == 200) {
                        $('#qqDataGrid').datagrid('load');
                    } else {
                        $.messager.alert('提示', data.respDesc, 'error');
                    }
                },
                fail: function (data) {
                    $.messager.alert('提示', "操作失败，请重试。", 'error');
                }
            });
        }
    });
}