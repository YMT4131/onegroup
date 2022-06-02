layui.use(['form', 'table'], function () {
    var $ = layui.jquery,
    form = layui.form,
    table = layui.table;

    /**
     * 歌单列表展示
     */
    var tableIns= table.render({
    elem: '#currentTableId',
    url: ctx+'/list/query',
    toolbar: '#toolbarDemo',
    defaultToolbar: ['filter', 'exports', 'print', {
        title: '提示',
        layEvent: 'LAYTABLE_TIPS',
        icon: 'layui-icon-tips'
    }],
    cols: [[
        {type: "checkbox", width: 50},
        {field: 'listId', title: '歌单序号',sort: true},
        {field: 'listName', title: '歌单名称', sort: true},
        {field: 'listInfo', title: '歌单介绍'},
        {field: 'typeName', title: '音乐类型', sort: true},
        {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
    ]],
    limits: [10, 15, 20, 25],
    limit: 15,
    page: true,
    skin: 'line'
});

    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        var result = data.field
        console.log(result.listName);

        //执行搜索重载
        table.reload('currentTableId', {
            page: {
                curr: 1
            }
            , where: {
                listId:result.listId,
                listName:result.listName,
                typeName:result.typeName,
            }
        }, 'data');

        return false;
    });


    /**
     * toolbar头部工具栏监听事件
     */
    table.on('toolbar(currentTableFilter)', function (obj) {
        if (obj.event === 'add') {  // 监听添加操作
            toAddOrUpdate();
            $(window).on("resize", function () {
                layer.full(index);
            });
        } else if (obj.event === 'delete') {  // 监听删除操作
            var checkStatus = table.checkStatus('currentTableId');
            deleteList(checkStatus.data);
        }
    });

    //打开新增或修改页面
    function toAddOrUpdate(listId){
        var url = ctx+'/list/toAddOrUpdate';
        var title = '添加歌单';
        if(listId){
            url = url +"?listId="+listId;
            title = '更新歌单';
        }
        var index = layer.open({
            title: title,
            type: 2,
            shade: 0.2,
            maxmin:true,
            shadeClose: true,
            area: ['100%', '100%'],
            content:url
        });
    }

    //监听表格复选框选择
    table.on('checkbox(currentTableFilter)', function (obj) {
    console.log(obj)
});

    //监听行工具栏
    table.on('tool(currentTableFilter)', function (obj) {
        if (obj.event === 'edit') {  // 监听添加操作
            toAddOrUpdate(obj.data.listId);
            $(window).on("resize", function () {
                layer.full(index);
            });
        } else if (obj.event === 'delete') {  // 监听删除操作
            layer.confirm('确定删除当前歌单？', {icon: 3, title: "歌单管理"}, function
                (index) {
                $.post(ctx + "/list/delete",{listIds:obj.data.listId},function (data) {
                    if(data.code==200){
                        layer.msg("操作成功");
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg, {icon: 5});
                    }
                });
            });
        }
    });

    function deleteList(datas){
        if(datas.length == 0){
            layer.msg("请选择要删除的歌单", {icon: 5});
            return;
        }
        layer.confirm('确定删除选中的歌单?', {
            btn: ['确定','取消'] //按钮
        }, function(index){
            layer.close(index);
            var listIds= "listIds=";
            for(var i=0;i<datas.length;i++){
                if(i<datas.length-1){
                    listIds=listIds+datas[i].listId+"&listIds=";
                }else {
                    listIds=listIds+datas[i].listId;
                }
            }
            $.ajax({
                type:"post",
                url:ctx + "/list/delete",
                data:listIds,
                dataType:"json",
                success:function (data) {
                    if(data.code==200){
                        layer.msg("删除成功");
                        tableIns.reload();
                    }else{
                        layer.msg(data.msg, {icon: 5});
                    }
                }
            })
        });
    }

});