package org.allianz.hellospringboot.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class RequestRedirectInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//if (request.getUrl()=="/api/customer/" then...
		response.sendRedirect("/api/cliente");
		return false;
	}

}
