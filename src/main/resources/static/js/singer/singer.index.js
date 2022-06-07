layui.use(['form', 'table'], function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table;


    //页面表单渲染
    var tableIns = table.render({
        elem: '#currentTableId',
        url: ctx+'/singer/singerlist',
        toolbar: '#toolbarDemo',
        defaultToolbar: ['filter', 'exports', 'print'],
        cols: [[
            {type: "checkbox", width: 50},
            {field: 'singerId', width: 80, title: '序号', sort: true},
            {field: 'singerName',  title: '歌手名称'},
            {field: 'singerGender',  title: '歌手性别', sort: true},
            {field: 'singerNal',  title: '歌手国籍'},
            {field: 'singerInfo', title: '歌手信息'},
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
        table.reload('currentTableId', {
            page: {
                curr: 1
            }
            , where: {
                singerName:result.singerName,
                singerGender:result.singerGender,
                singerNal:result.singerNal
            }
        }, 'data');

        return false;
    });

    /**
     * toolbar头部工具栏监听事件
     */
    table.on('toolbar(currentTableFilter)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        if (obj.event == 'add') {  // 监听添加操作
            toAddOrUpdate();
        } else if (obj.event == 'delete') {  // 监听删除操作
            deleteSingerList(checkStatus.data);
        }
    });
    //批量删除 1.判断是否选中 2.询问用户是否删除 3.遍历选中的数据对象,得到对应的ID,拼接id参数 4.发送ajax请求
    function deleteSingerList(dataList){
        //判断是否选中
        if (dataList.length < 1){
            layer.msg("请选择要删除的记录",{icon:5});
            return;
        }
        //询问是否确定删除
        layer.confirm("确定要删除这些数据吗？",{btn:['确定','取消']},function (index){
            //关闭确认框
            layer.close(index);
            //遍历选中记录,得到用户ID，拼接ID参数
            var ids = "ids=";
            for (var i = 0; i < dataList.length; i++){
                var singer = dataList[i];
                //判断是否最后一个元素
                if(i < dataList.length - 1){
                    ids = ids+ singer.singerId + '&ids=';
                }else {
                    ids = ids + singer.singerId;
                }
            }
            console.log(ids);
            //请求后台
            $.ajax({
                type:"post",
                url:ctx+"/singer/delete",
                data:ids,//参数是数组
                success:function (result){
                    if(result.code == '200'){
                        layer.msg("删除成功",{icon:6});
                        //重载列表
                        tableIns.reload();
                    }else{
                        layer.msg(result.msg,{icon:5});
                    }
                }
            })
        })
    }


    /**
     * 行工具栏监听
     */
    table.on('tool(currentTableFilter)', function (obj) {
        console.log(obj);
        var data = obj.data; //获取当前行的数据
        if (obj.event == 'edit') {
            toAddOrUpdate(data.singerId);
        } else if (obj.event == 'delete') { //删除操作
            deleteData(data.singerId,2);
        }
    });
    //打开新增或修改页面
    function toAddOrUpdate(singerId){
        var url = ctx+'/singer/addOrUpdateSinger';
        var tit = "添加歌手";
        if(singerId){
            url = url +"?singerId="+singerId;
            tit = "修改歌手";
        }
        var index = layer.open({
            title: tit,
            type: 2,
            shade: 0.2,
            maxmin:true,
            shadeClose: true,
            area: ['100%', '100%'],
            content:url
        });
    }
    //删除操作
    function deleteData(singerId,flag){
        if(singerId.length == 0){
            layer.msg("请选择需要删除的数据");
            return;
        }
        layer.confirm("确定要删除这些数据吗？",{btn:['确定','取消']},function (index){
            //关闭确认框
            console.log(singerId);
            layer.close(index);
            //请求后台
            $.ajax({
                type:"post",
                url:ctx+"/singer/delete",
                data: {
                    ids:singerId
                },
                success:function (result){
                    if(result.code == '200'){
                        layer.msg("删除成功",{icon:6});
                        //重载列表
                        tableIns.reload();
                    }else{
                        layer.msg(result.msg,{icon:5});
                    }
                }
            })
        })
    }

});