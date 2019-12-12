package com.SpringCloud.demo.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 路由之前的过滤器
 * @author citicsf
 *
 */
@Component
public class MyFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(MyFilter.class);
	
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		//线程安全request获取
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));//日志
		Object accessToken = request.getParameter("token");
		if(accessToken == null) {//访问的token 没有获取到
			log.warn("token is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			try {
				ctx.getResponse().getWriter().write(" token is empty! ");
			}catch (Exception e) {
			}
			return null;
		}
		log.info("OK");
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
