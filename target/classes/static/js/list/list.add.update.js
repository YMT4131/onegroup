layui.use(['form','tableSelect'], function () {
    var form = layui.form,
        layer = layui.layer,
        tableSelect = layui.tableSelect,
        $ = layui.$;


    //歌曲列表下拉菜单渲染
    tableSelect.render({
        elem: '#music',	//定义输入框input对象 必填
        checkedKey: 'musicId', //表格的唯一建值，非常重要，影响到选中状态 必填
        searchKey: 'keyword',	//搜索输入框的name值 默认keyword
        searchPlaceholder: '歌曲列表搜索',	//搜索输入框的提示文字 默认关键词搜索
        height:'400',  //自定义高度
        width:'900',  //自定义宽度
        table: {	//定义表格参数，与LAYUI的TABLE模块一致，只是无需再定义表格elem
            //没有返回带selected标记的数据
            url: ctx+'/list/queryAllMusicByListId?listId'+$("input[name='listId']").val(),
            cols: [[
                { type: 'checkbox' },
                { field: 'musicId', title: '歌曲序号' },
                { field: 'musicName', title: '歌曲名称' }
               // ,{ field: 'selected', title: 'selected'}
            ]]
        },
        done: function (elem, data) {
            var NEWJSON = []
            var bzcodejson=[]
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.musicName)
                bzcodejson.push(item.musicId)
            })
            elem.val(NEWJSON.join(","))
            $("#musicId").val(bzcodejson.join(","))
            //需要再添加数据库更新list_has_music
        }
    });



    //歌单类型下拉菜单渲染
    tableSelect.render({
        elem: '#type',	//定义输入框input对象 必填
        checkedKey: 'typeId', //表格的唯一建值，非常重要，影响到选中状态 必填
        searchKey: 'keyword',	//搜索输入框的name值 默认keyword
        searchPlaceholder: '类型搜索',	//搜索输入框的提示文字 默认关键词搜索
        height:'400',  //自定义高度
        width:'900',  //自定义宽度
        table: {	//定义表格参数，与LAYUI的TABLE模块一致，只是无需再定义表格elem
            url: ctx+'/list/queryAllTypes',
            cols: [[
                { type: 'radio' },
                { field: 'typeId', title: '类型序号' },
                { field: 'typeName', title: '类型名称' }
            ]]
        },
        done: function (elem, data) {
            var NEWJSON = []
            var bzcodejson=[]
            layui.each(data.data, function (index, item) {
                NEWJSON.push(item.typeName)
                bzcodejson.push(item.typeId)
            })
            elem.val(NEWJSON.join(","))
            $("#typeId").val(bzcodejson.join(","))

        }
    });


    //监听提交
    form.on('submit(saveBtn)', function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false,
            shade: 0.8});
        var url = ctx+"/list/save";
        if($("input[name='listId']").val()){
            url = ctx+"/list/update";
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