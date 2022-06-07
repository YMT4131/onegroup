//定义树形结构对象
var zTreeObj;

$(function () {
    //加载树形结构
    loadModuleInfo();
});
function loadModuleInfo() {

    $.ajax({
        type:"post",
        url:ctx+"/module/queryAllModules",
        data:{
            roleId:$('#roleId').val()
        },
        dataType:"json",
        success:function (data) {
            //data查询到的资源列表

        // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
            var setting = {
                data: {simpleData: {enable: true } } ,//支持简单数据格式
                check: {
                    enable: true  //复选框
                },
                callback: {
                    onCheck: zTreeOnCheck //用于捕获 checkbox / radio 被勾选 或 取消勾选的事件回调函数
                }
            };
            var zNodes =data;
            zTreeObj=$.fn.zTree.init($("#test1"), setting, zNodes);
        }
    })

}

function zTreeOnCheck(event,treeId,treeNode){
    var nodes = zTreeObj.getCheckedNodes(true); //获取所有被选中的节点数据
    var ids = new Array();//获取所有资源的id
    for(var i=0;i<nodes.length;i++){
        ids.push(nodes[i].id);
    }
    //获取需要授权角色的id
    var roleId = $("[name='roleId']").val();
    //请求后台
    $.ajax({
        type:"post",
        url:ctx+"/role/addRoleGrant?roleId="+roleId,
        data:JSON.stringify(ids),
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        success:function (result){
            if(result.code == '200'){

            }else{
                layer.msg(result.msg);
            }
        }
    });

}
