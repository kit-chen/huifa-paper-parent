/**
 * Created by GuangRi on 2016/11/25.
 */
$(function () {
    $('#taobaoOrderDataGrid').datagrid({
        title: '淘宝代理订单信息',
        width: 1062,
        height: 362,
        sortOrder: 'asc',
        singleSelect: false,
        checkOnSelect: true,
        pagination: true,
        rownumbers: true,
        showFooter: true,
        method: 'post',
        url: PageCommon.contextPath + '/taobaoAgent/orderList.xhtml',
        columns: [[
            {field: 'id', title: 'id', width: 0, hidden: true},
            {field: 'tid', title: '订单编号', width: 150, align: 'center'},
            {field: 'buyerNick', title: '买家昵称', width: 150, align: 'center'},
            {field: 'payment', title: '订单金额', width: 100, align: 'center'},
            {field: 'num', title: '商品数量', width: 80, align: 'center'},
            {field: 'status', title: '订单状态', width: 250, align: 'center'},
            {field: 'sellerNick', title: '卖家昵称', width: 150, align: 'center'},
            {
                field: 'createTime', title: '创建时间', width: 150, align: 'center',
                formatter: function (value, row, index) {
                    if (value) {
                        return new Date(value).format("yyyy-MM-dd hh:mm:ss");
                    }
                }
            }
        ]]
    });
});

function searchTaobaoOrder() {
    var tid = $('#tid').textbox('getValue');
    if (tid  && tid != '') {
        if (!isTaobaoOrder(tid)) {
            $.messager.alert('提示', '请输入正确的淘宝订单号', 'warning');
            return;
        }
    }
    var startTime = $('#startTime').datebox('getValue');
    var endTime = $('#endTime').datebox('getValue');
    if (startTime > endTime) {
        $.messager.alert('提示', '开始时间不能大于结束时间', 'warning');
        return;
    }
    var taobaoOrderDataGrid = $('#taobaoOrderDataGrid');
    var queryParams = taobaoOrderDataGrid.datagrid('options').queryParams;
    queryParams.tid = tid;
    queryParams.buyerNick = $('#buyerNick').textbox('getValue');
    queryParams.sellerNick = $('#sellerNick').textbox('getValue');
    queryParams.startTimeStr = startTime;
    queryParams.endTimeStr = endTime;
    var page = taobaoOrderDataGrid.datagrid('getPager');
    queryParams.page = page.data('pagination').options.pageNumber;
    queryParams.rows = page.data('pagination').options.pageSize;
    $.ajax({
        url: PageCommon.contextPath + '/taobaoAgent/orderList.xhtml',
        data: queryParams,
        type : 'post',
        dataType: 'json',
        success: function (data) {
            taobaoOrderDataGrid.datagrid('loadData', data);
        },
        fail: function (data) {
            $.messager.alert('提示', '查询失败，请重试！', 'error');
        }
    });
}