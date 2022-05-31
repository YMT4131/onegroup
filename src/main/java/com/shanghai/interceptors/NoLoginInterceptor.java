//package com.shanghai.interceptors;
//
//import com.shanghai.exceptions.NoLoginException;
//import com.shanghai.po.User;
//import com.shanghai.service.UserService;
//import com.shanghai.utils.LoginUserUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 登录拦截器
// */
//public class NoLoginInterceptor extends HandlerInterceptorAdapter {
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request
//            , HttpServletResponse response, Object handler) throws Exception {
//        //获取cookie中的数据，
//        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
//        //通过cookie中的数据查询数据库，判断是否有对应的信息
//        User user = userService.selectByPrimaryKey(userId);
//        if(user == null ){
//            throw new NoLoginException();
//        }
//        return true;
//    }
//}
