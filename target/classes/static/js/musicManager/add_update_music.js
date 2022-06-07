layui.use(['form','tableSelect'], function () {
    var form = layui.form,
        layer = layui.layer,
        tableSelect = layui.tableSelect,
        $ = layui.$;

    //歌手下拉菜单渲染
    tableSelect.render({
        elem: '#singer',	//定义输入框input对象 必填
        checkedKey: 'id', //表格的唯一建值，非常重要，影响到选中状态 必填
        searchKey: 'keyword',	//搜索输入框的name值 默认keyword
        searchPlaceholder: '关键词搜索',	//搜索输入框的提示文字 默认关键词搜索
        height:'400',  //自定义高度
        width:'400',  //自定义宽度
        table: {	//定义表格参数，与LAYUI的TABLE模块一致，只是无需再定义表格elem
            url:ctx+ '/musicManage/singer',
            cols: [[
                { type: 'radio' },
                { field: 'singerId', title: 'ID' },
                { field: 'singerName', title: '歌手名' }
            ]]
        },
        done: function (elem, data) {
            //选择完后的回调，包含2个返回值 elem:返回之前input对象；data:表格返回的选中的数据 []
            //拿到data[]后 就按照业务需求做想做的事情啦~比如加个隐藏域放ID...
            var NEWJSON = []
            var bzcodejson=[]
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.singerName)
                bzcodejson.push(item.singerId)
            })
            elem.val(NEWJSON.join(","))
            $("#singerId").val(bzcodejson.join(","))
        }
    })

    //专辑下拉菜单渲染
    tableSelect.render({
        elem: '#album',	//定义输入框input对象 必填
        checkedKey: 'id', //表格的唯一建值，非常重要，影响到选中状态 必填
        searchKey: 'keyword',	//搜索输入框的name值 默认keyword
        searchPlaceholder: '关键词搜索',	//搜索输入框的提示文字 默认关键词搜索
        height:'400',  //自定义高度
        width:'400',  //自定义宽度
        table: {	//定义表格参数，与LAYUI的TABLE模块一致，只是无需再定义表格elem
            url:ctx+ '/musicManage/album',
            cols: [[
                { type: 'radio' },
                { field: 'albumId', title: '专辑ID' },
                { field: 'albumName', title: '专辑名' }
            ]]
        },
        done: function (elem, data) {
            //选择完后的回调，包含2个返回值 elem:返回之前input对象；data:表格返回的选中的数据 []
            //拿到data[]后 就按照业务需求做想做的事情啦~比如加个隐藏域放ID...
            var NEWJSON = []
            var bzcodejson=[]
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.albumName)
                bzcodejson.push(item.albumId)
            })
            elem.val(NEWJSON.join(","))
            //隐藏域中传递给后端
            $("#albumId").val(bzcodejson.join(","))
        }
    })



    //监听提交
    form.on('submit(saveBtn)', function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false,
            shade: 0.8});
        var url = ctx+"/musicManage/add";
        if($("input[name='musicId']").val()){
            url = ctx+"/musicManage/edit";
        }
        $.post(url,data.field,function(res) {
            if (res.code == 200) {
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg("操作成功");
                    layer.closeAll("iframe");
                    parent.location.reload();
                }, 500);
            } else {
                layer.msg(
                    res.msg, {
                        icon: 5
                    }
                );
            }
        });

        return false;
    });
});