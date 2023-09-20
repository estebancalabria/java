package org.allianz.hellospringboot.configurations;

import org.allianz.hellospringboot.interceptors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@Configuration
public class AppConfig implements WebMvcConfigurer {

	@Autowired
    RequestLoggerInterceptor loggerInterceptor;	
	@Autowired
	RequestRedirectInterceptor redirectInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(this.loggerInterceptor);
	    registry.addInterceptor(this.redirectInterceptor).addPathPatterns("/api/customer");
	   
	}
}
