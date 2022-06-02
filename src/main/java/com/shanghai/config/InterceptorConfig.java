package com.shanghai.config;

import com.shanghai.interceptors.NoLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //创建拦截器对象
    @Bean
    public NoLoginInterceptor noLoginInterceptor(){
        return new NoLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(noLoginInterceptor())
                .addPathPatterns("/**")  //设置拦截地址
                .excludePathPatterns("/index","/login","/loginAction","/indexModel"
                        ,"/api/**","/page/**","/css/**","/images/**","/js/**","/lib/**"); //设置放行地址
    }
}
