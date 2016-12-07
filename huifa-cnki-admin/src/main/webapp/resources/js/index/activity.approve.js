/**
 * Created by GuangRi on 2016/11/10.
 */
$(function () {
    $('#activityDataGrid').datagrid({
        title: '待审核活动参与记录',
        width: 1200,
        height: 388,
        sortOrder: 'asc',
        singleSelect: false,
        checkOnSelect: true,
        pagination: true,
        rownumbers: true,
        method: 'post',
        url: PageCommon.contextPath + "/activityManage/approveList.xhtml",
        columns: [[
            {field: 'id', title: 'id', width: 0, hidden: true},
            {field: 'ck', checkbox: true},
            {field: 'userId', title: '用户ID', width: 80, align: 'center'},
            {field: 'title', title: '活动标题', width: 250, align: 'center'},
            {
                field: 'submitTime', title: '提交时间', width: 150, align: 'center',
                formatter: function (value, row, index) {
                    if (value) {
                        return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                    }
                }
            },
            {
                field: 'actResult', title: '上传结果', width: 560, align: 'center',
                formatter: function (value, row, index) {
                    if (value) {
                        value = html_encode(value);
                        if (value.indexOf('//www') >= 0) {
                            value = '<a href="' + value + '" target="_blank">' + value +'</a>';
                        }
                        return value;
                    }
                }
            },
            {
                field: 'other', title: '操作', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    return "<a href='javascript:void(0);' onclick='approve(" + row.id + ")'>通过</a>&nbsp;&nbsp;&nbsp;<a href='javascript:void(0);' onclick='reject(" + row.id + ")'>拒绝</a>";
                }
            }
        ]],
        toolbar: [{
            id: 'batchApprove',
            text: '通过选中记录',
            iconCls: 'icon-ok',
            handler: batchApprove
        }]
    });
});

function html_encode(str) {
    var s = "";
    if (str.length == 0) return "";
    s = str.replace(/&/g, ">");
    s = s.replace(/</g, "<");
    s = s.replace(/>/g, ">");
    s = s.replace(/ /g, " ");
    s = s.replace(/\'/g, "'");
    s = s.replace(/\"/g, "\"");
    s = s.replace(/\n/g, "<br>");
    return s;
}

function batchApprove() {
    var selectedRows = $('#activityDataGrid').datagrid('getSelections');
    var rowSize = selectedRows.length;
    if (rowSize <= 0) {
        $.messager.alert('提示', "请选中要审核的记录", 'warning');
        return;
    }
    $.messager.confirm('审核', '全部通过选中的参与记录？', function (choice) {
        if (choice) {
            var recordIds = '';
            for (var i = 0;i < rowSize;i++) {
                recordIds += selectedRows[i].id;
                recordIds += ',';
            }
            $.ajax({
                url: PageCommon.contextPath + "/activityManage/approveInBatch.xhtml",
                data: {
                    'recordIds': recordIds
                },
                type: 'post',
                dataType: "json",
                success: function (data) {
                    if (data.respCode == 200) {
                        $('#activityDataGrid').datagrid('load');
                    } else if (data.respCode == 501) {
                        $.messager.alert('提示', data.respDesc, 'warning');
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

function approve(id) {
    $.messager.confirm('审核', '通过该参与记录？', function (choice) {
        if (choice) {
            $.ajax({
                url: PageCommon.contextPath + "/activityManage/approve.xhtml",
                data: {
                    'id': id
                },
                type: 'post',
                dataType: "json",
                success: function (data) {
                    if (data.respCode == 200) {
                        $('#activityDataGrid').datagrid('load');
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

function reject(id) {
    $.messager.prompt('审核', '请输入拒绝理由：', function(approveResult){
        if (approveResult){
            $.ajax({
                url: PageCommon.contextPath + "/activityManage/reject.xhtml",
                data: {
                    'id': id,
                    'approveResult': approveResult
                },
                type: 'post',
                dataType: "json",
                success: function (data) {
                    if (data.respCode == 200) {
                        $('#activityDataGrid').datagrid('load');
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