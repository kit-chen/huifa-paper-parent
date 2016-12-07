/**
 * Created by Administrator on 2016/9/29.
 */

var ztreeObj;
var ztreeObj2;
var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    },
    check: {
        enable: true,
        chkStyle: "checkbox"
    }
};
var setting2 = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    },
    callback:{
        onClick:function(event,nodeId,node){
            var parent = node.getParentNode();
            $("#mparent").val(parent.name);
            $("#mparentId").val(parent.id);
            $("#mid").val(node.id);
            $("#mname").val(node.name);
            $("#murl").val(node.menuUrl);
            $("#mtype").val(node.type);

        }
    }

};
$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: "/sysmodule/list.xhtml",
        dataType: "json",
        success: function(menus){
            ztreeObj = $.fn.zTree.init($("#menus"), setting, menus);
            ztreeObj.expandAll(true);
            ztreeObj2 = $.fn.zTree.init($("#modules"), setting2, menus);
            ztreeObj2.expandAll(true);
        }
    });
});


function actionFmt(val,row){
    return "<a href=# onclick='openUpdatePanel(\""+row.id+"\")'>修改</a> <a href=# onclick='doDelete(\""+row.id+"\")'>删除</a>";
}

function doSearch(){
    var role = {};
    if($("#name").val()){
        role.name = $("#name").val();
    }
    role.status = $("#status").val();
    $('#roleTable').datagrid('load',role);
}

function openModulePanel(){
    $('#moduleWin').window('open');
}

function openUpdatePanel(id){
    resetPanel();
    $.ajax({
        type: "POST",
        data:{id:id},
        url: "/sysrole/get.xhtml",
        dataType: "json",
        success: function(data){
            $("#nid").val(data.sysRole.id);
            $("#nname").val(data.sysRole.name);
            $("#nstatus").val(data.sysRole.status);
            if(data.modules){
                $.each(data.modules,function(index,module){
                   var node =  ztreeObj.getNodeByParam("id", module.moduleId, null);
                    if(!node.isParent){
                        ztreeObj.checkNode(node, true, true);
                    }

                });
            }
            $('#win').window('open');
        }
    });
}

function resetPanel(){
    $("#nid").val("");
    $("#nname").val("");
    $("#nstatus").val(1);
    ztreeObj.checkAllNodes(false);
}

function openAddPanel(){
    resetPanel();
    $('#win').window('open');
}


function doCreateOrUpdate(){
    var params;
    var name = $("#nname").val();
    var status = $("#nstatus").val();
    var id = $("#nid").val();
    if(!name){
        $.messager.alert('数据验证','请填写角色名称','error');
    }
    var modules = "";
    var selNodes = ztreeObj.getCheckedNodes();
    $.each(selNodes,function(index,node){
        modules+=node.id+',';
    });
    params = {name:name,status:status,modules:modules};
    if(id){
        params.id=id;
    }
    if(modules){
        $.ajax({
            type: "POST",
            data:params,
            url: "/sysrole/createOrUpdate.xhtml",
            dataType: "json",
            success: function(data){
                if(data.result=="success"){
                    $.messager.alert('操作提示','数据保存成功！');
                    $('#roleTable').datagrid('load');
                    $('#win').window('close');
                }else{
                    $.messager.alert('操作提示','数据保存失败！','error');
                }
            }
        });
    }else{
        $.messager.alert('数据验证','请选择角色权限','error');
    }
}
function doDelete(id){
    $.messager.confirm('操作提示',"确认要删除此项数据吗？",function(){
        $.ajax({
            type: "POST",
            data:{id:id},
            url: "/sysrole/delete.xhtml",
            dataType: "json",
            success: function(data){
                if(data.result=="success"){
                    $.messager.alert('操作提示','删除成功！');
                    $('#roleTable').datagrid('load');
                }else{
                    $.messager.alert('操作提示',data.errorMsg,'error');
                }
            }
        });
    })
}
function resetModulePanel(){
    $("#mparentId").val("");
    $("#mparent").val("");
    $("#mid").val("");
    $("#mname").val("");
    $("#murl").val("");
    $("#mtype").val(1);
}
function toAddModule(){
    resetModulePanel();
   var nodes= ztreeObj2.getSelectedNodes();
    if(nodes.length>0){
        $.each(nodes,function(index,node){
            $("#mparentId").val(node.id);
            $("#mparent").val(node.name);
        });
    }else{
        $.messager.alert('操作提示','请选择菜单后点击新增按钮！','error');
    }
}

function saveModule(){
    var params = {};
    if(!$("#mparentId").val()){
        $.messager.alert('操作提示','未选择上级菜单！','error');
        return false;
    }
    if(!$("#mname").val()){
        $.messager.alert('操作提示','菜单名称不能为空！','error');
        return false;
    }
    if(!$("#murl").val()){
        $.messager.alert('操作提示','菜单链接不能为空！','error');
        return false;
    }
    params.parentId =$("#mparentId").val();
    if($("#mid").val()){
        params.id = $("#mid").val();
    }
    params.id = $("#mid").val();
    params.name = $("#mname").val("");
    params.menuUrl =  $("#murl").val("");
    params.type = $("#mtype").val(1);
    $.ajax({
        type: "POST",
        data:params,
        url: "/sysmodule/saveOrUpdate.xhtml",
        dataType: "json",
        success: function(data){
            if(data.result=="success"){
                $.messager.alert('操作提示','菜单保存成功！');
                window.location.reload();
            }else{
                $.messager.alert('操作提示',data.errorMsg,'error');
            }
        }
    });


}