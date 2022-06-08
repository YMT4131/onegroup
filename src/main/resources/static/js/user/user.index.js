layui.use(['form', 'table'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table;



    //页面表单渲染
    var tableIns = table.render({
        id:'userTable',
        elem: '#currentTableId',    //容器元素的ID属性值
        url: ctx + '/user/list',
        toolbar: '#toolbarDemo',
        defaultToolbar: ['filter', 'exports', 'print'],
        cols: [[
            {type: "checkbox", width: 50},
            {field: 'userId', width: 80, title: '序号', sort: true},
            {field: 'isVip', width: 120, title: '是否会员'},
            {field: 'userName', width: 100, title: '昵称', sort: true},
            {field: 'userGender', width: 80, title: '性别'},
            {field: 'userBirthday', title: '生日', width: 200},
            {field: 'userJoinDate', width: 200, title: '注册时间', sort: true},
            {field: 'roleName', width: 200, title: '角色', sort: true},
            {title: '操作', minWidth: 80, toolbar: '#currentTableBar', align: "center"}
        ]],
        limits: [10, 15, 20, 25],
        limit: 10,
        page: true,
        skin: 'line'
    });

    /*--------------------------------------------------------------------------*/

    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        var result = data.field

        //执行搜索重载
        table.reload('userTable', {
            page: {
                curr: 1 //重新从第一页开始
            }
            , where: {
                userId: result.userId,     //序号
                isVip:  result.isVip,     //是否会员
                userName: result.userName    //昵称
            }
        }, 'data');

        return false;
    });

    /*-----------------------------------------------------------------------*/




    /**
     * toolbar监听事件
     *  table.on('toolbar(数据表格的lay-filter属性值)', function (obj) {
     *  }
     */
    table.on('toolbar(currentTableFilter)', function (obj) {
        if (obj.event === 'add') {  // 监听添加操作
            //添加操作
            openUserDialog();
        } else if (obj.event === 'delete') {  // 监听删除操作
            //删除操作
            deleteUser(obj);
        }
    });

    /**
     * 打开添加/修改用户数据的窗口
     *  如果用户ID为空，则为添加操作
     *  如果用户ID不为空，则为修改操作
     *
     * @param userId
     */
    function openUserDialog(userId){
        var url = ctx + "/user/toUserPage";
        //判断营销机会ID是否为空
        if (userId != null && userId != ''){
            title = "编辑用户";
            url += '?userId=' + userId;

        }
        layui.layer.open({
            title: '添加用户',
            type: 2,
            shade: 0.2,
            maxmin:true,
            shadeClose: true,
            area: ['100%', '100%'],
            content: url,
        });
    }

    /**
     * 删除用户(多条记录)
     * @param data
     */
    function deleteUser(data){
        //获取数据表格选中的行数据
        var checkStatus = table.checkStatus("userTable");

        //获取所有被选中的记录对应的数据
        var userData = checkStatus.data;

        //判断用户是否选择的记录(选中行的数量大于0)
        if(userData.length < 1){
            layer.msg("请选择要删除的记录！", {icon:5});
            return;
        }
        //询问用户是否确认删除
        layer.confirm("您确定要删除选中的记录吗？", {icon:3, title:'用户管理'}, function (index){
            //关闭确认框
            layer.close(index);
            //传递的参数是数组 ids=1&ids=2&ids=3
            var ids = "ids=";
            //循环选中行记录的数据
            for (var i = 0; i < userData.length; i++){
                if (i < userData.length-1){
                    ids = ids + userData[i].userId + "&ids="
                }else{
                    ids = ids + userData[i].userId;
                }

            }
            //console.log(ids);
            //发送ajax请求，执行删除用户
            $.ajax({
                type:"post",
                url:ctx + "/user/delete",
                data:ids,   //传递的参数是数组
                success:function (result){
                    //判断删除结果
                    if(result.code == 200){
                        layer.msg("删除成功！", {icon:6});
                        //刷新表格
                        tableIns.reload();
                    }else {
                        //提示失败
                        layer.msg(result.msg, {icon:5});
                    }
                }
            })
        });
    }





    /*-----------------------------------------------------------------------*/


    //监听表格复选框选择
    table.on('checkbox(currentTableFilter)', function (data) {
        console.log(data)
    });

    table.on('tool(currentTableFilter)', function (data) {

        if (data.event === 'edit') {   //编辑操作
            //得到用户ID
            var userId = data.data.userId;
            //打开用户数据的窗口
            openUserDialog(userId);
        }else if(data.event == "delete"){
            //弹出确认框，询问用户是否确认删除
            layer.confirm('确定要删除该记录吗？', {icon:3, title:"用户管理"}, function (index){
                //关闭确认框
                layer.close(index);

                //发送ajax请求，删除记录
                $.ajax({
                    type: "post",
                    url: ctx + "/user/delete",
                    data:{
                        ids:data.data.userId
                    },
                    success:function (result){
                        //判断删除结果
                        if(result.code == 200){
                            layer.msg("删除成功！", {icon:6});
                            //刷新表格
                            tableIns.reload();
                        }else {
                            //提示失败
                            layer.msg(result.msg, {icon:5});
                        }
                    }
                });
            });
        }
    });





});
