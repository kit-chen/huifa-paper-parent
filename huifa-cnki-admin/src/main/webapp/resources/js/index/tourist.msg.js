
$(function () {
    $('#touristDataGrid').datagrid({
        title: '官网游客留言',
        width: 1200,
        height: 500,
        sortOrder: 'asc',
        singleSelect: true,
        method: 'post',
        url: PageCommon.contextPath + "/touristMessage/list.xhtml",
        columns: [[
            {title: 'id', field: 'id', width: 0, hidden: true},
            {field: 'touristName', title: '留言姓名', width: 80, align: 'center'},
            {field: 'touristPhone', title: '留言手机', width: 400, align: 'center'},
            {field: 'content', title: '留言内容', width: 400, align: 'center',
                formatter: function (value, row, index) {
                    if (value)
                        return html_encode(value);
                }
            },
            {
                field: 'createTime', title: '留言时间', width: 150, align: 'center',
                formatter: function (value, row, index) {
                    if (value)
                        return new Date(value).format("yyyy-MM-dd hh:mm:ss")
                }
            },
            {
                field: 'other', title: '操作', width: 60, align: 'center',
                formatter: function (value, row, index) {
                    return "<a href='javascript:void(0);' onclick='deleteTouristMsg(" + row.id + ")'>删除</a>";
                }
            }
        ]],
        pagination: true,
        rownumbers: true
    });
});

function deleteTouristMsg(id){
    $.messager.confirm('温馨提示', '是否确定删除此留言?', function (result) {
        if (result) {
            $.ajax({
                url : PageCommon.contextPath + "/touristMessage/deleteById.xhtml",
                data : {"id":id},
                dataType : "json",
                type : 'post',
                success : function(data){
                    if(data.state == 'success'){
                        $.messager.alert("删除提示", "删除成功");
                        $('#touristDataGrid').datagrid("reload");
                    }else{
                        $.messager.alert("删除提示", "删除失败");
                    }
                }
            });
        }
    });
}

function html_encode(str)
{
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

//解码
function html_decode(str)
{
    var s = "";
    if (str.length == 0) return "";
    s = str.replace(/>/g, "&");
    s = s.replace(/</g, "<");
    s = s.replace(/>/g, ">");
    s = s.replace(/ /g, " ");
    s = s.replace(/'/g, "\'");
    s = s.replace(/"/g, "\"");
    s = s.replace(/<br>/g, "\n");
    return s;
}