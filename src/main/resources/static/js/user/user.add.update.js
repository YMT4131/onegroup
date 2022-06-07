layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    //var formSelects = layui.formSelects;

    /**
     * 监听表单submit事件
     */
    form.on('submit(saveBtn)', function (data){
        //提交数据时的加载层
        var index = layer.msg("数据提交中，请稍后...",{
            icon:16,        //图标
            time:false,     //不关闭
            shade:0.8       //设置遮罩的透明度
        });

        //发送ajax请求
        var url = ctx + "/user/add";    //添加操作

        //通过营销机会ID来判断当前需要执行添加操作还是修改操作
        //如果营销机会ID为空，则表示执行添加操作;如果ID不为空，则表示执行更新操作
        //通过获取隐藏域中的ID
        var userId = $("[name = 'userId']").val();
        //判断ID是否为空
        if (userId != null && userId != ''){
            //更新操作
            url = ctx + "/user/update";
        }


        $.ajax({
            type:"post",
            url:url,
            data:JSON.stringify(data.field),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success:function (result) {
                //判断操作是否执行成功 200=成功
                if (result.code == 200) {
                    //成功
                    //提示成功
                    layer.msg("操作成功！", {icon: 6});
                    //关闭加载层
                    layer.close(index);
                    //关闭弹出层
                    layer.closeAll("iframe");
                    //刷新父窗口
                    parent.location.reload();
                } else {
                    //失败
                    layer.msg(result.msg, {icon: 5});
                }
            }
        });
        //阻止表单提交
        return false;
    });


    /**
     * 加载下拉框
     */
    /*formSelects.config("selectId", {
        type: "post",    //请求方式
        searchUrl:ctx + "/user/queryAllRoles",  //请求地址
        keyName:"roleName",     //下拉框中的文本内容，要与返回的数据中对应key一致
        keyVal:"roleId"
    }, true);*/

    $.ajax({
        type:"post",
        url:ctx + "/user/queryAllRoles",
        data:{},
        success:function (data){
            //判断返回的数据是否为空
            if (data != null){
                //获取隐藏域设置的指派人ID
                var roleId = $("#rolenameId").val();
                //遍历返回的数据
                for (var i = 0; i < data.length; i++){
                    var opt = "";
                    //如果循环得到的ID与隐藏域的ID相等，则表示被选中
                    if (roleId == data[i].roleId){
                        //设置下拉选项选中
                        opt = "<option value='"+data[i].roleId+"' selected>"+data[i].roleName+"</option>";
                    }else{
                        //设置下拉选项
                        opt = "<option value='"+data[i].roleId+"'>"+data[i].roleName+"</option>";
                    }

                    //将下拉项设置到下拉框中
                    $("#roleId").append(opt);
                }
            }
            //重新渲染下拉框的内容
            layui.form.render("select");
        }
    });




});