package com.scube.techboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.scube.techboot.utils.TechbootResourceBundle;

public class TechbootRequestHandler extends HandlerInterceptorAdapter{

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String title = "TechBoot|Admin|Company|Customers|Services";
		String metaInfo = "we are Students,Employees participating Courses and Events in online product services.";
		String tempURL = request.getRequestURL().toString();
		String requestMapping = null;
		String[] tempArray = tempURL.split("/");		
		/*System.out.println("Array 1st Length "+tempArray.length);
		System.out.println("Array 2nd length "+tempArray[tempArray.length - 1]);
		System.out.println(" Array 3rd length "+tempArray[tempArray.length - 1].lastIndexOf(".html"));*/


		if (tempArray[tempArray.length - 1].lastIndexOf(".html") >= 0) {
			requestMapping = tempArray[tempArray.length - 1].substring(0,tempArray[tempArray.length - 1].lastIndexOf(".html"));
		} else {
			/*System.out.println("last element \t"+tempArray[tempArray.length - 1]);//Calcium%20Dificiency
			System.out.println("with sub string +1 \t"+tempArray[tempArray.length - 1].substring(tempArray[tempArray.length - 1].lastIndexOf(".html")+1));*/
			requestMapping = tempArray[tempArray.length - 1].substring(tempArray[tempArray.length - 1].lastIndexOf(".html") + 1);
		}

		if(null==requestMapping){
			requestMapping=tempArray[1];
		}


		if (null != requestMapping && !requestMapping.isEmpty()) {

			String seoKeyValue = TechbootResourceBundle.getSEOValue(requestMapping);

			if (null != seoKeyValue && !seoKeyValue.isEmpty()) {
				title = TechbootResourceBundle.getTitleValue(seoKeyValue);
				metaInfo = TechbootResourceBundle.getMetaValue(seoKeyValue);
			}


			else{
				if(requestMapping.contains("%20")){
					String replace=requestMapping.replace("%20"," ");
					title=replace;
				}
				else{
					title=requestMapping;
				}
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
