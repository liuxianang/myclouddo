package com.bootdo.clouddoapp.config;

import com.bootdo.clouddocommon.intercepter.AuthIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthConfig implements WebMvcConfigurer {
    @Bean
    public AuthIntercepter authIntercepter() {
        return new AuthIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(authIntercepter());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");

        addInterceptor.excludePathPatterns("/app/**");
        addInterceptor.excludePathPatterns("/files/**");
        addInterceptor.excludePathPatterns("/appimage/**");
        addInterceptor.excludePathPatterns("/appuser/**");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }
}
