layui.use(['form','tableSelect'], function () {
    var form = layui.form,
        layer = layui.layer,
        tableSelect = layui.tableSelect,
        $ = layui.$;


    //歌手下拉菜单渲染
    tableSelect.render({
        elem: '#singerId',	//定义输入框input对象 必填
        checkedKey: 'id', //表格的唯一建值，非常重要，影响到选中状态 必填
        searchKey: 'keyword',	//搜索输入框的name值 默认keyword
        searchPlaceholder: '姓名搜索',	//搜索输入框的提示文字 默认关键词搜索
        height:'400',  //自定义高度
        width:'900',  //自定义宽度
        table: {	//定义表格参数，与LAYUI的TABLE模块一致，只是无需再定义表格elem
            url: '../api/tableSelect.json',
            cols: [[
                { type: 'radio' },
                { field: 'id', title: 'ID' },
                { field: 'username', title: '姓名' }
            ]]
        },
        done: function (elem, data) {
            //选择完后的回调，包含2个返回值 elem:返回之前input对象；data:表格返回的选中的数据 []
            //拿到data[]后 就按照业务需求做想做的事情啦~比如加个隐藏域放ID...
        }
    })





    //监听提交
    form.on('submit(saveBtn)', function (data) {
        var index = layer.alert(JSON.stringify(data.field), {
            title: '最终的提交信息'
        }, function () {

            // 关闭弹出层
            layer.close(index);

            var iframeIndex = parent.layer.getFrameIndex(window.name);
            parent.layer.close(iframeIndex);

        });

        return false;
    });

});