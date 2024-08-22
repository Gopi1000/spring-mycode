/**
 * 
 */
package com.scube.crm.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.scube.crm.utils.MyjobkartResourceBundle;

/**
 * @author User
 * 
 */
public class MyjobkartRequestHandler extends HandlerInterceptorAdapter {

	@Autowired
	ServletContext servletContext;

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String title = "MyJobKart Job Portal";
		String metaInfo = "MyJobKart, jobb search, job openings";
		String tempURL = request.getRequestURL().toString();
		String requestMapping = null;
		String[] tempArray = tempURL.split("/");
		if (tempArray[tempArray.length - 1].lastIndexOf(".html") >= 0) {
			requestMapping = tempArray[tempArray.length - 1].substring(0,
					tempArray[tempArray.length - 1].lastIndexOf(".html"));
		} else {
			requestMapping = tempArray[tempArray.length - 1]
					.substring(tempArray[tempArray.length - 1]
							.lastIndexOf(".html") + 1);
		}
		if (null != requestMapping && !requestMapping.isEmpty()) {

			String seoKeyValue = MyjobkartResourceBundle
					.getSEOValue(requestMapping);
			if (null != seoKeyValue && !seoKeyValue.isEmpty()) {
				title = MyjobkartResourceBundle.getTitleValue(seoKeyValue);
				metaInfo = MyjobkartResourceBundle.getMetaValue(seoKeyValue);
			}
		}
		request.setAttribute("title", title);
		request.setAttribute("meta", metaInfo);

		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			Exception exceptionIfAny) throws Exception {
	}

}
