layui.use(['form','tableSelect','laydate'], function () {
    var form = layui.form,
        layer = layui.layer,
        tableSelect = layui.tableSelect,
        laydate = layui.laydate,
        $ = layui.$;
    //日期框渲染
    laydate.render({
        elem: '#albumReleaseDate'
    });

    //歌手下拉菜单渲染
    tableSelect.render({
        elem: '#singer',	//定义输入框input对象 必填
        checkedKey: 'id', //表格的唯一建值，非常重要，影响到选中状态 必填
        searchKey: 'keyword',	//搜索输入框的name值 默认keyword
        searchPlaceholder: '关键词搜索',	//搜索输入框的提示文字 默认关键词搜索
        height:'400',  //自定义高度
        width:'900',  //自定义宽度
        table: {	//定义表格参数，与LAYUI的TABLE模块一致，只是无需再定义表格elem
            url:ctx+ '/album/querySingerList',
            cols: [[
                { type: 'radio' },
                { field: 'id', title: 'ID' },
                { field: 'name', title: '歌手' }
            ]]
        },
        done: function (elem, data) {
            //选择完后的回调，包含2个返回值 elem:返回之前input对象；data:表格返回的选中的数据 []
            //拿到data[]后 就按照业务需求做想做的事情啦~比如加个隐藏域放ID...
            var NEWJSON = []
            var bzcodejson=[]
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.name)
                bzcodejson.push(item.id)
            })
            elem.val(NEWJSON.join(","))
            $("#singerId").val(bzcodejson.join(","))
        }
    })

    //监听提交
    form.on('submit(saveBtn)', function (form_data) {
        //添加遮罩
        var index = layer.msg("数据提交中,请稍后...",{
            icon:16, // 图标
            time:false, // 不关闭
            shade:0.8 // 设置遮罩的透明度
        });

        var save_url=ctx+"/album/saveOrUpdateAlbum";
        //数据提交
        $.ajax({
            type:"post",
            url:save_url,
            data:JSON.stringify(form_data.field),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success:function (result){
                if(result.code == "200"){
                    layer.msg(result.msg);
                    //关闭遮罩
                    layer.close(index);
                    //关闭弹出层
                    layer.close("iframe");
                    //刷新父页面的列表
                    parent.location.reload();
                }else{
                    layer.msg(result.msg);
                }
            }

        });

        return false;
    });

});