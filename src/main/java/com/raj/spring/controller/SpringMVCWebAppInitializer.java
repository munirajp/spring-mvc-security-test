package com.raj.spring.controller;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMVCWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}
	
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{ LoginApplicationConfig.class };
	}
	
	protected String[] getServletMappings() {
			return new String[] {"/"};
	}
}