/**
 * Created by GuangRi on 2016/11/24.
 */
$(function () {
    $('#agentManageDataGrid').datagrid({
        title: '官网推广代理列表',
        width: 1580,
        height: 388,
        sortOrder: 'asc',
        singleSelect: true,
        checkOnSelect: true,
        pagination: true,
        rownumbers: true,
        method: 'post',
        url: PageCommon.contextPath + "/agentmanage/agentList.xhtml",
        columns: [[
            {field: 'id', title: 'id', width: 0, hidden: true},
            {field: 'userName', title: '手机号', width: 100, align: 'center'},
            {
                field: 'createTime', title: '创建时间', width: 150, align: 'center',
                formatter: function (value, row, index) {
                    if (value) {
                        return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                    }
                }
            },
            {field: 'qq', title: 'QQ号', width: 100, align: 'center'},
            {field: 'withdrawAmounts', title: '累计提现金额', width: 100, align: 'center'},
            {
                field: 'lastWithdrawTime', title: '上次提现时间', width: 150, align: 'center',
                formatter: function (value, row, index) {
                    if (value) {
                        return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                    }
                }
            },
            {field: 'ratio', title: '分成比例', width: 100, align: 'center'},
            {field: 'alipayAccount', title: '支付宝账号', width: 180, align: 'center'},
            {field: 'url', title: '推广链接', width: 250, align: 'center'},
            {field: 'note', title: '备注', width: 200, align: 'center'},
            {
                field: 'deleteStatus', title: '状态', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    if (value == 0) {
                        return '已注销';
                    } else {
                        return '正常';
                    }
                }
            },
            {
                field: 'other', title: '操作', width: 118, align: 'center',
                formatter: function (value, row, index) {
                    return "<a href=\"javascript:openModifyAgentUserDialog(" + row.id + ",'" + row.qq + "','" + row.note + "'," + row.ratio + ",'" + row.alipayAccount + "','" + row.deleteStatus + "');\">修改代理商信息</a>";
                }
            }
        ]],
        toolbar: [{
            id: 'createAgentUser',
            text: '新增代理商',
            iconCls: 'icon-add',
            handler: function () {
                $('#dlgcreateAgentUser').dialog('open');
            }
        }]
    });
});

function closeDialog(dialogId) {
    $('#' + dialogId).dialog('close');
}

function createAgentUser() {
    var userName = $('#userName').textbox('getValue');
    if (!userName || userName == '' || !mobilenoReg.test(userName)) {
        $.messager.alert('提示', '请输入正确的代理商手机号', 'error');
        return;
    }
    var password = $('#password').textbox('getValue');
    if (!password || password == '') {
        $.messager.alert('提示', '请输入代理商登录密码', 'error');
        return;
    }
    var ratio = $('#ratio').numberspinner('getValue');
    if (ratio) {
        ratio = parseInt(ratio, 10);
        if (!isInteger(ratio)) {
            $.messager.alert('提示', '分成比例只能是1~9的整数', 'error');
            return;
        } else if (ratio >= 10 || ratio <= 0) {
            $.messager.alert('提示', '分成比例只能是1~9的整数', 'error');
            return;
        }
    }
    $.messager.confirm('新建', '确定新建代理商？', function (choice) {
        if (choice) {
            $.ajax({
                url: PageCommon.contextPath + "/agentmanage/createAgentUser.xhtml",
                data: {
                    'userName': userName,
                    'password': password,
                    'qq': $('#qq').textbox('getValue'),
                    'note': $('#note').textbox('getValue'),
                    'ratio': ratio,
                    'alipayAccount': $('#alipayAccount').textbox('getValue')
                },
                type: 'post',
                dataType: "json",
                success: function (data) {
                    if (data.state == 'success') {
                        $('#userName').textbox('setValue', '');
                        $('#password').textbox('setValue', '');
                        $('#qq').textbox('setValue', '');
                        $('#note').textbox('setValue', '');
                        $('#ratio').numberspinner('setValue', '');
                        $('#alipayAccount').textbox('setValue', '');

                        $.messager.alert('提示', data.desc, 'info');
                        $('#dlgcreateAgentUser').dialog('close');
                        $('#agentManageDataGrid').datagrid('load');
                    } else {
                        $.messager.alert('提示', data.desc, 'error');
                    }
                },
                fail: function (data) {
                    $.messager.alert('提示', "操作失败，请重试。", 'error');
                }
            });
        }
    });
}

function openModifyAgentUserDialog(id, qq, note, ratio, alipayAccount, deleteStatus) {
    $('#modifyId').val(id);
    $('#status').combobox('setValue', deleteStatus);
    $('#newQQ').textbox('setValue', qq);
    $('#newNote').textbox('setValue', note);
    $('#newRatio').numberspinner('setValue', ratio);
    $('#newAlipayAccount').textbox('setValue', alipayAccount);
    $('#dlgModifyAgentUser').dialog('open');
}

function modifyAgentUser() {
    var ratio = $('#newRatio').numberspinner('getValue');
    if (ratio) {
        ratio = parseInt(ratio, 10);
        if (!isInteger(ratio)) {
            $.messager.alert('提示', '分成比例只能是1~9的整数', 'error');
            return;
        } else if (ratio >= 10 || ratio <= 0) {
            $.messager.alert('提示', '分成比例只能是1~9的整数', 'error');
            return;
        }
    }
    $.messager.confirm('修改', '确定修改代理商信息？', function (choice) {
        if (choice) {
            $.ajax({
                url: PageCommon.contextPath + "/agentmanage/modifyAgentUser.xhtml",
                data: {
                    'id': $('#modifyId').val(),
                    'deleteStatus': $('#status').combobox('getValue'),
                    'password': $('#newPassword').textbox('getValue'),
                    'qq': $('#newQQ').textbox('getValue'),
                    'note': $('#newNote').textbox('getValue'),
                    'ratio': ratio,
                    'alipayAccount': $('#newAlipayAccount').textbox('getValue')
                },
                type: 'post',
                dataType: "json",
                success: function (data) {
                    if (data.state == 'success') {
                        $('#status').combobox('setValue', '');
                        $('#newPassword').textbox('setValue', '');
                        $('#newQQ').textbox('setValue', '');
                        $('#newNote').textbox('setValue', '');
                        $('#newRatio').numberspinner('setValue', '');
                        $('#newAlipayAccount').textbox('setValue', '');

                        $.messager.alert('提示', data.desc, 'info');
                        $('#dlgModifyAgentUser').dialog('close');
                        $('#agentManageDataGrid').datagrid('load');
                    } else {
                        $.messager.alert('提示', data.desc, 'error');
                    }
                },
                fail: function (data) {
                    $.messager.alert('提示', "操作失败，请重试。", 'error');
                }
            });
        }
    });
}