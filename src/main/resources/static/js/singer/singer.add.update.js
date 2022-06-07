layui.use(['form','tableSelect'], function () {
    var form = layui.form,
        layer = layui.layer,
        tableSelect = layui.tableSelect,
        $ = layui.$;




    //监听提交
    form.on('submit(saveBtn)', function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false,
            shade: 0.8});
        var url = ctx + "/singer/addsinger";
        if($("input[name='singerId']").val()){
            url = ctx+"/singer/updatesinger";
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