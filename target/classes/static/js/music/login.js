layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    // 登录过期的时候，跳出ifram框架
    if (top.location != self.location) top.location = self.location;

    // 粒子线条背景
    // $(document).ready(function(){
    //     $('.layui-container').particleground({
    //         dotColor:'#7ec7fd',
    //         lineColor:'#7ec7fd'
    //     });
    // });

    // 进行登录操作
    form.on('submit(login)', function (data) {
        var filedData = data.field;
        if (data.username == '') {
            layer.msg('用户名不能为空');
            return false;
        }
        if (data.password == '') {
            layer.msg('密码不能为空');
            return false;
        }
        var paramData = JSON.stringify({
            userName:filedData.userName,
            userPwd:filedData.userPwd
        });
        //提交到后台
        $.ajax({
            type:"post",
            url:ctx+"/loginAction",
            data:paramData,
            contentType:"application/json;charset=utf-8",
            dataType:"json",
            success:function (result) {
                if(result.code == '200'){ //登录成功
                    layer.msg("登录成功,即将进入系统",function (){
                        //将用户信息存入cookie中
                        //id取加密后的id
                        $.cookie('userIdStr',result.result.userIdStr);
                        $.cookie('userName',result.result.userName);
                        //用户选择了记住密码功能
                        if($('#rememberMe').prop("checked")){
                            $.cookie('userIdStr',result.result.userIdStr,{expires:7});
                            $.cookie('userName',result.result.userName,{expires:7});
                        }
                        //跳转到菜单主页
                        window.location.href = ctx + "/main";
                    });
                }else{ //登录失败
                    layer.msg(result.msg);
                }
            }
        })

        return false;
    });

});