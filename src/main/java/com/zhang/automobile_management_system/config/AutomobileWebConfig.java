package com.zhang.automobile_management_system.config;

import com.zhang.automobile_management_system.interceptor.AutomobileInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author ZQJ
 * @version 1.0
 * @description:
 * @throws
 * @date 2023/4/13 19:25
 */

@Configuration
public class AutomobileWebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AutomobileInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/checklogin","/login","/css/**","/fonts/**","/images/**","/js/**","/favicon.ico");
    }
}
