/**
 * 
 */
$(function(){
    initTaobaoAgentDataGrid();

    $('#addRechargeMoney').on('valuechange', function () {
        var addRechargeMoney = parseInt($(this).val(), 10);
        var addBalance = 0;
        if (addRechargeMoney <= 0) {
            $.messager.alert('提示', '请输入正确的金额', 'error');
        } else {
            addBalance = addRechargeMoney * 2.5;
        }
        $('#addBalance').html(addBalance);
    });
});

function initTaobaoAgentDataGrid() {
    $('#taobaoAgentDataGrid').datagrid({
        title: '淘宝代理列表',
        width: 1550,
        height: 362,
        sortOrder: 'asc',
        singleSelect: true,
        checkOnSelect: true,
        pagination: true,
        rownumbers: true,
        showFooter: true,
        method: 'post',
        url: PageCommon.contextPath + "/taobaoAgent/agentList.xhtml",
        columns: [[
            {field: 'id', title: 'id', width: 0, hidden: true},
            {field: 'sellerNick', title: '淘宝昵称', width: 150, align: 'center'},
            {field: 'domain', title: '短地址', width: 100, align: 'center'},
            {field: 'realName', title: '真实姓名', width: 100, align: 'center'},
            {field: 'wangWang', title: '旺旺号', width: 150, align: 'center'},
            {field: 'balance', title: '账户币数余额', width: 150, align: 'center'},
            {field: 'rechargeMoney', title: '累计充值金额', width: 150, align: 'center'},
            {
                field: 'state', title: '供货状态', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    if (value == 0) {
                        return '已断货';
                    } else if (value == 1) {
                        return '供货充足';
                    }
                }
            },
            {
                field: 'updateTime', title: '授权时间', width: 150, align: 'center',
                formatter: function (value, row, index) {
                    if (value) {
                        return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                    }
                }
            },
            {
                field: 'duration', title: '授权截止时间', width: 150, align: 'center',
                formatter: function (value, row, index) {
                    if (value) {
                        var updateTime = new Date();
                        updateTime.setTime(row.updateTime);
                        return new Date(updateTime.getTime() + value * 1000).format("yyyy-MM-dd hh:mm:ss");
                    }
                }
            },
            {
                field: 'deleteStatus', title: '账户状态', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    if (value == 0) {
                        return '已注销';
                    } else if (value == 1) {
                        return '正常';
                    }
                }
            },
            {field: 'note', title: '备注', width: 100, align: 'center'},
            {
                field: 'other', title: '操作', width: 118, align: 'center',
                formatter: function (value, row, index) {
                    if (!row.id || row.id == '') {
                        return '';
                    }
                    if (row.deleteStatus == 0) {
                        return "<a href=\"javascript:enableAgent(" + row.id + ",'" + row.sellerNick + "');\">激活</a>";
                    } else {
                        return "<a href=\"javascript:disableAgent(" + row.id + ",'" + row.sellerNick + "');\">注销</a>&nbsp;&nbsp;<a href=\"javascript:openUpdateBalanceDialog(" + row.id + ",'" + row.sellerNick + "'," + row.balance + "," + row.rechargeMoney + ");\">更新账户余额</a>";
                    }
                }
            }
        ]]
    });
}

function searchTaobaoAgent() {
    var startTime = $('#startTime').datebox('getValue');
    var endTime = $('#endTime').datebox('getValue');
    if (startTime > endTime) {
        $.messager.alert('提示', '开始时间不能大于结束时间', 'warning');
        return;
    }
    var taobaoAgentDataGrid = $('#taobaoAgentDataGrid');
    var queryParams = taobaoAgentDataGrid.datagrid('options').queryParams;
    queryParams.sellerNick = $('#sellerNick').textbox('getValue');
    queryParams.startTimeStr = startTime;
    queryParams.endTimeStr = endTime;
    var page = taobaoAgentDataGrid.datagrid('getPager');
    queryParams.page = page.data('pagination').options.pageNumber;
    queryParams.rows = page.data('pagination').options.pageSize;
    $.ajax({
        url: PageCommon.contextPath + '/taobaoAgent/agentList.xhtml',
        data: queryParams,
        type : 'post',
        dataType: 'json',
        success: function (data) {
            if (data.state == 'success') {
                taobaoAgentDataGrid.datagrid('loadData', data);
            } else {
                $.messager.alert('提示', data.desc, 'warning');
            }
        },
        fail: function (data) {
            $.messager.alert('提示', '查询失败，请重试！', 'error');
        }
    });
}

function disableAgent(id, sellerNick) {
    $.messager.confirm('注销', '确定注销淘宝代理：' + sellerNick + '？', function (choice) {
        if (choice) {
            $.ajax({
                url: PageCommon.contextPath + '/taobaoAgent/disableAgent.xhtml',
                data: {
                    'id': id
                },
                type : 'post',
                dataType: 'json',
                success: function (data) {
                    if (data.state == 'success') {
                        $.messager.alert('提示', '注销代理成功！', 'info');
                        $('#taobaoAgentDataGrid').datagrid('reload');
                    } else {
                        $.messager.alert('提示', data.desc, 'warning');
                    }
                },
                fail: function (data) {
                    $.messager.alert('提示', '注销代理失败，请重试！', 'error');
                }
            });
        }
    });
}

function enableAgent(id, sellerNick) {
    $.messager.confirm('激活', '确定激活淘宝代理：' + sellerNick + '？', function (choice) {
        if (choice) {
            $.ajax({
                url: PageCommon.contextPath + '/taobaoAgent/enableAgent.xhtml',
                data: {
                    'id': id
                },
                type : 'post',
                dataType: 'json',
                success: function (data) {
                    if (data.state == 'success') {
                        $.messager.alert('提示', '激活代理成功！', 'info');
                        $('#dlgUpdateBalance').dialog('close');
                        $('#taobaoAgentDataGrid').datagrid('reload');
                    } else {
                        $.messager.alert('提示', data.desc, 'warning');
                    }
                },
                fail: function (data) {
                    $.messager.alert('提示', '激活代理失败，请重试！', 'error');
                }
            });
        }
    });
}

function openUpdateBalanceDialog(id, sellerNick, balance, rechargeMoney) {
    $('#rowId').val(id);
    $('#rowSellerNick').val(sellerNick);
    $('#currentBalance').html(balance);
    $('#currentRechargeMoney').html(rechargeMoney);
    $('#dlgUpdateBalance').dialog('open');
}

function closeDialog() {
    $('#dlgUpdateBalance').dialog('close');
}

function updateBalance() {
    var addRechargeMoney = parseInt($('#addRechargeMoney').val(), 10);
    if (addRechargeMoney <= 0) {
        $.messager.alert('提示', '请输入正确的金额', 'error');
    } else if (addRechargeMoney % 4 != 0) {
        $.messager.alert('提示', '金额必是4的倍数', 'error');
    } else {
        var sellerNick = $('#rowSellerNick').val();
        $.messager.confirm('更新账户余额', '确定为淘宝代理：' + sellerNick + ' 添加' + addRechargeMoney + '元？', function (choice) {
            if (choice) {
                $.ajax({
                    url: PageCommon.contextPath + '/taobaoAgent/updateBalance.xhtml',
                    data: {
                        'id': $('#rowId').val(),
                        'rechargeMoney': addRechargeMoney
                    },
                    type : 'post',
                    dataType: 'json',
                    success: function (data) {
                        if (data.state == 'success') {
                            $.messager.alert('提示', '更新代理余额成功！', 'info');
                            $('#taobaoAgentDataGrid').datagrid('reload');
                        } else {
                            $.messager.alert('提示', data.desc, 'warning');
                        }
                    },
                    fail: function (data) {
                        $.messager.alert('提示', '更新代理余额失败，请重试！', 'error');
                    }
                });
            }
        });
    }
}