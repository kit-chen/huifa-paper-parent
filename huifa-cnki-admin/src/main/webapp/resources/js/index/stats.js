/**
 * Created by GuangRi on 2016/11/23.
 */
function searchStatsData() {
    $.ajax({
        url: PageCommon.contextPath + "/stats/allData.xhtml",
        data: {
            'startTime': $('#startTime').datebox('getValue'),
            'endTime': $('#endTime').datebox('getValue')
        },
        type: 'post',
        dataType: "json",
        success: function (data) {
            if (data.state == 'success') {
                var statsData = data.statsData;
                if (statsData && statsData.length > 0) {
                    var length = statsData.length;
                    var statsHtml = '';
                    for (var i=0;i<length;i++) {
                        statsHtml += '<div style="line-height: 35px;font-size: 16px;">' + statsData[i] + '</div>';
                    }
                    $('#divStatsData').html(statsHtml);
                }
            } else {
                $.messager.alert('提示', data.desc, 'error');
            }
        },
        fail: function (data) {
            $.messager.alert('提示', "操作失败，请重试。", 'error');
        }
    });
}