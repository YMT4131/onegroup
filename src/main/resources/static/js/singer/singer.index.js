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
        if (obj.event === 'add') {  // 监听添加操作
            toAddOrUpdate();
        } else if (obj.event === 'delete') {  // 监听删除操作
            deleteData(checkStatus.data,1);
        }
    });


    /**
     * 行工具栏监听
     */
    table.on('tool(currentTableFilter)', function (obj) {
        console.log(obj);
        var data = obj.data; //获取当前行的数据
        if (obj.event === 'edit') {
            toAddOrUpdate(data.singerId);
        } else if (obj.event === 'delete') { //删除操作
            deleteData(data,2);
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
    function deleteData(data,flag){
        if(data.length == 0){
            layer.msg("请选择需要删除的数据");
            return;
        }
        layer.confirm("确定要删除这些数据吗？",{btn:['确定','取消']},function (index){
            //关闭确认框
            layer.close(index);
            var ids = new Array(); //创建存放id的数组
            if(flag == 1){ //头部工具栏
                for(var i=0;i<data.length;i++){
                    ids.push(data[i].singerId);
                }
            }else{ //行工具栏
                ids.push(data.singerId);
            }

            //请求后台
            $.ajax({
                type:"post",
                url:ctx+"/singer/deleteSinger",
                data:JSON.stringify(ids),
                contentType:"application/json;charset=utf-8",
                dataType:"json",
                success:function (result){
                    if(result.code == '200'){
                        layer.msg("删除成功");
                        //重载列表
                        table.reload('currentTableId', {
                            where: { //设定异步数据接口的额外参数，任意设
                                albumName:result.albumName,
                                singer:result.singer,
                                lowPrice:result.lowPrice,
                                higPrice:result.higPrice
                            }
                            ,page: {
                                curr: 1 // 重新从第 1 页开始
                            }
                        });
                    }else{
                        layer.msg(result.msg);
                    }
                }
            })
        })
    }

});