/**
 * Created by GuangRi on 2016/11/24.
 */
$(function () {
    $('#withdrawRecordDataGrid').datagrid({
        title: '官网推广代理提现申请列表',
        width: 1200,
        height: 362,
        sortOrder: 'asc',
        singleSelect: true,
        checkOnSelect: true,
        pagination: true,
        rownumbers: true,
        method: 'post',
        url: PageCommon.contextPath + "/agentmanage/withdrawRecordList.xhtml",
        columns: [[
            {field: 'id', title: 'id', width: 0, hidden: true},
            {field: 'agentId', title: '代理商ID', width: 100, align: 'center'},
            {field: 'userName', title: '代理商手机号', width: 100, align: 'center'},
            {field: 'withdrawAmounts', title: '提现金额', width: 100, align: 'center'},
            {
                field: 'createTime', title: '申请时间', width: 150, align: 'center',
                formatter: function (value, row, index) {
                    if (value) {
                        return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                    }
                }
            },
            {
                field: 'status', title: '申请状态', width: 150, align: 'center',
                formatter: function (value, row, index) {
                    if (value == 0) {
                        return '未处理';
                    } else if (value == 1) {
                        return '已处理，提现成功';
                    } else {
                        return '已处理，拒绝提现';
                    }
                }
            },
            {field: 'approveResult', title: '处理结果', width: 300, align: 'center'},
            {
                field: 'updateTime', title: '处理时间', width: 150, align: 'center',
                formatter: function (value, row, index) {
                    if (value) {
                        return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                    }
                }
            },
            {
                field: 'other', title: '操作', width: 118, align: 'center',
                formatter: function (value, row, index) {
                    if (row.status == 0) {
                        return "<a href=\"javascript:approveWithdraw(" + row.id + ");\">同意提现</a>&nbsp;&nbsp;&nbsp;<a href=\"javascript:rejectWithdraw(" + row.id + ");\">拒绝提现</a>";
                    }
                }
            }
        ]]
    });
});

function approveWithdraw(id) {
    $.messager.confirm('批准提现', '确定批准该笔提现？', function (choice) {
        if (choice) {
            submitHandleResult(id, 1, 'ok');
        }
    });
}

function rejectWithdraw(id) {
    $.messager.prompt('拒绝提现', '请输入拒绝提现理由', function(reason){
        if (reason){
            submitHandleResult(id, 2, reason);
        }
    });
}

function submitHandleResult(id, status, approveResult) {
    $.ajax({
        url: PageCommon.contextPath + "/agentmanage/handleWithdraw.xhtml",
        data: {
            'id': id,
            'status': status,
            'approveResult': approveResult
        },
        type: 'post',
        dataType: "json",
        success: function (data) {
            if (data.state == 'success') {
                $.messager.alert('提示', data.desc, 'info');
                $('#withdrawRecordDataGrid').datagrid('load');
            } else {
                $.messager.alert('提示', data.desc, 'error');
            }
        },
        fail: function (data) {
            $.messager.alert('提示', "操作失败，请重试。", 'error');
        }
    });
}