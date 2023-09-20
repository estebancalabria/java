package org.allianz.hellospringboot.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.stereotype.Component;

@Component
public class RequestHeaderFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("ANTES DEL DO FILTER");

		//HttpServletResponseWrapper newResponse = new HttpServletResponseWrapper((HttpServletResponse)response);
		//newResponse.addHeader("CopyRight", "Alianz");

		HttpServletRequest httpRequest = ((HttpServletRequest) request);
		if (httpRequest.getRequestURI().startsWith("/api/cliente")) {
			String token = httpRequest.getHeader("token");
			
			if ((token==null) || (!token.equals("TOKEN"))) {	
				//HttpServletResponseWrapper unauthorizedResponse = new HttpServletResponseWrapper((HttpServletResponse)response);
				((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
				//chain.doFilter(httpRequest, unauthorizedResponse);
			}
		}
		
		chain.doFilter(request, response);
		System.out.println("DESPUES DEL DO FILTER");
		
	}
}
