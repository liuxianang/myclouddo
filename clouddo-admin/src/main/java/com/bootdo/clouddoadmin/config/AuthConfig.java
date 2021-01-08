package com.bootdo.clouddoadmin.config;

import com.bootdo.clouddocommon.intercepter.AuthIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AuthConfig extends WebMvcConfigurerAdapter {
    @Bean
    public AuthIntercepter authIntercepter() {
        return new AuthIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(authIntercepter());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/common/generator/**");
        addInterceptor.excludePathPatterns("/test**");
        addInterceptor.excludePathPatterns("/chat/push/**");
        addInterceptor.excludePathPatterns("/job/**");
        addInterceptor.excludePathPatterns("/chat/**");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }
}
