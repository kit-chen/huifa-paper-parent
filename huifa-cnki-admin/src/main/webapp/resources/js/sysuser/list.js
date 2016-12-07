function doSearch(){
    var params = {};
    if($('#loginName').val()){
        params.loginName = $('#loginName').val();
    }
    if( $('#name').val()){
        params.name = $('#name').val();
    }
    if( $('#phone').val()){
        params.phone = $('#phone').val();
    }
    if( $('#roleId').val()!=null){
        params.roleId = $('#roleId').val();
    }
    if( $('#status').val()!=null){
        params.status = $('#status').val();
    }
    $('#userTable').datagrid('load',params);
}
function resetPanel(){
    $('#nid').val("");
    $('#nname').val("");
    $('#nloginName').val("");
    $('#npassword').val("");
    $('#rppassword').val("");
    $('#nphone').val("");
    $('#nroleId').val("");
    $('#nstatus').val(1);
}
function doValidate(){
    if(!$('#nname').val()){
        $.messager.alert('数据验证','用户名不能为空','error');
        return true;
    }
    if(!$('#nloginName').val()){
        $.messager.alert('数据验证','登录名不能为空','error');
        return true;
    }
    if(!$('#npassword').is(":hidden")){
        if(!$('#npassword').val()){
            $.messager.alert('数据验证','密码不能为空','error');
            return true;
        }
        if(!$('#rppassword').val()){
            $.messager.alert('数据验证','请再次确认密码','error');
            return true;
        }
        if($('#npassword').val() != $('#rppassword').val()){
            $.messager.alert('数据验证','再次密码输入不一致，请重新输入','error');
            return true;
        }
    }

}

function openAddPanel(){
    resetPanel();
    $('.pwd').show();
    $('#npassword').attr("disabled",false);
    $('.pwd').show();
    $('#rppassword').attr("disabled",false);
    $('#win').window('open');
}

function openUpdatePanel(id){
    resetPanel();
    $('.pwd').hide();
    $('#npassword').attr("disabled",true);
    $('.pwd').hide();
    $('#rppassword').attr("disabled",true);
    $.ajax({
        type: "post",
        data:{id:id},
        url: "/sysuser/get.xhtml",
        dataType: "json",
        success: function(data){
            if(data){
                $('#nid').val(data.id);
                $('#nname').val(data.name);
                $('#nloginName').val(data.loginName);
                $('#nphone').val(data.phone);
                $('#nroleId').val(data.roleId);
                $('#nstatus').val(data.status);
                $('#win').window('open');
            }else{
                $.messager.alert('操作失败',data.errorMsg,'error');
            }
        }
    });
    $('#win').window('open');
}

function closeAddPanel(){
    $('#win').window('close');
}

function doCreateOrUpdate(){
    if(doValidate()){
        return false;
    }
    var user = {
        id: $('#nid').val(),
        name: $('#nname').val(),
        loginName: $('#nloginName').val(),
        password: $('#npassword').val(),
        phone: $('#nphone').val(),
        roleId: $('#nroleId').val(),
        status: $('#nstatus').val()
    };
    $.ajax({
        type: "post",
        data:user,
        url: "/sysuser/createOrUpdate.xhtml",
        dataType: "json",
        success: function(data){
            if(data.result=="success"){
                $.messager.alert('操作成功','保存用户成功!');
                $('#win').window('close');
                doSearch();
            }else{
                $.messager.alert('操作失败',data.errorMsg,'error');
            }

        }
    });
}

function actionFmt(val,row){
    return "<a href=# onclick='openUpdatePanel(\""+row.id+"\")'>修改</a> <a href=# onclick='doDelete(\""+row.id+"\")'>删除</a>";
}

function doDelete(id){
    $.ajax({
        type: "post",
        data:{id:id},
        url: "/sysuser/delete.xhtml",
        dataType: "json",
        success: function(data){
            if(data.result=="success"){
                $.messager.alert('操作成功','删除用户成功!');
                $('#win').window('close');
                doSearch();
            }else{
                $.messager.alert('操作失败',data.errorMsg,'error');
            }

        }
    });
}