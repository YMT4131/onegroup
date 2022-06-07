layui.use(['form', 'table'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table;


    //页面表单渲染
    var tableIns = table.render({
        elem: '#roleList',
        url: ctx+'/role/list',
        toolbar: '#toolbarDemo',
        defaultToolbar: ['filter', 'exports', 'print'],
        id: 'roleList',
        cols: [[
            {type: "checkbox", width: 50},
            {field: 'roleId', width: 80, title: '序号', sort: true},
            {field: 'roleName',  title: '角色名称'},
            {field: 'roleRemark', title: '角色备注'},
            {field: 'createDate',  title: '创建日期', sort: true},
            {field: 'updateDate',  title: '修改日期'},

            {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
        ]],
        limits: [10, 15, 20, 25],
        limit: 10,
        page: true,
        skin: 'line'
    });

    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        var result = data.field

        //执行搜索重载
        table.reload('roleList', {
            page: {
                curr: 1
            }
            , where: {
                roleName:result.roleName,
                roleId:result.roleId,

            }
        }, 'data');

        return false;
    });

    /**
     * toolbar头部工具栏监听事件
     */
    table.on('toolbar(roles)', function (data) {

        if (data.event === 'add') {  // 监听添加操作
            toAddOrUpdate();
         } else if (data.event === 'grant') {  //
            var checkStatus = table.checkStatus("roleList");

            openGrantDialog(checkStatus.data);
         }
         else if (data.event === 'delete') {  // 监听删除操作
             //获取被选中的数据信息
            var checkStatus =table.checkStatus("roleList");
            console.log(checkStatus)
            deleteRole(checkStatus.data);
         }
    });

    //
    /**
     * 行工具栏监听
     */
    table.on('tool(roles)', function (data) {
        // var data = obj.data; //获取当前行的数据
        if (data.event === "edit") {
            toAddOrUpdate(data.data.roleId);
         } else if(data.event == "delete"){
            // 询问是否确认删除
            layer.confirm("确定要删除这条记录吗？", {icon: 3, title:"角色管理"}, function (index) {
                // 关闭窗口
                layer.close(index);
                // 发送ajax请求，删除记录
                $.ajax({
                    type:"post",
                    url: ctx + "/role/deleteRole",
                    data:{
                        ids:data.data.roleId
                    },
                    dataType:"json",
                    success:function (result) {
                        if (result.code == 200) {
                            layer.msg("删除成功",{icon:6})
                            // 加载表格
                            tableIns.reload();
                        } else {
                            layer.msg(result.msg, {icon: 5});
                        }
                    }
                });
            });
        }
     });
    //打开新增或修改页面
    function toAddOrUpdate(roleId){
        var url = ctx+'/role/toAddOrUpdate';
        var tit = "添加角色";
        if(roleId !=null && roleId !=''){
            url = url +"?roleId="+roleId;
            tit = "修改角色";
        }
        var index = layer.open({
            title: tit,
            type: 2,
            shade: 0.2,
            maxmin:true,
            shadeClose: true,
            area: ["500px","300px"],
            content:url
        });
    }




    function openGrantDialog(data){

        if (data.length == 0){
            layer.msg("请选择授权角色");
            return
        }
        if (data.length >1){
            layer.msg("不支持多角色授权");
            return;
        }
        var url = ctx+"/module/toGrantPage?roleId="+data[0].roleId;
        var tit ="角色授权";
        layui.layer.open({
            title: tit,
            type: 2,
            shade: 0.2,
            maxmin:true,
            shadeClose: true,
            area: ["600px","300px"],
            content:url
        })


    }


    //多条删除操作
    function deleteRole(roleData){
        if(roleData.length == 0){
                 layer.msg("请选择需要删除的数据",{icon:5});
                 return;
             }
//询问用户是否确认删除
        layer.confirm("您确定要删除选中的记录吗？", {icon:3, title:'角色管理'}, function (index){
            //关闭确认框
            layer.close(index);
            //传递的参数是数组 ids=1&ids=2&ids=3
            var ids = "ids=";
            //循环选中行记录的数据
            for (var i = 0; i < roleData.length; i++){
                if (i < roleData.length-1){
                    ids = ids + roleData[i].roleId + "&ids="
                }else{
                    ids = ids + roleData[i].roleId;
                }

            }

            //发送ajax请求，执行删除角色
            $.ajax({
                type:"post",
                url:ctx + "/role/deleteRole",
                data:ids,   //传递的参数是数组
                success:function (result) {
                    //判断删除结果
                    if (result.code == 200) {
                        layer.msg("删除成功！", {icon: 6});
                        //刷新表格
                        tableIns.reload();
                    } else {
                        //提示失败
                        layer.msg(result.msg, {icon: 5});
                    }
                }
            })
        });


    }

});