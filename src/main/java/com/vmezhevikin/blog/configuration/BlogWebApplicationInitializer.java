package com.vmezhevikin.blog.configuration;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.vmezhevikin.blog.filter.ApplicationFilter;
import com.vmezhevikin.blog.listener.ApplicationListener;

public class BlogWebApplicationInitializer implements WebApplicationInitializer{
	
	@Override
	public void onStartup(ServletContext container) throws ServletException {
		WebApplicationContext context = createWebApplicationContext(container);

		container.addListener(new ContextLoaderListener(context));
		container.addListener(context.getBean(ApplicationListener.class));

		registerFilters(container, context);
		registerSpribngMVCDispathcerServlet(container, context);
	}

	private WebApplicationContext createWebApplicationContext(ServletContext container) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.scan("com.vmezhevikin.blog.configuration");
		context.setServletContext(container);
		context.refresh();
		return context;
	}

	private void registerFilters(ServletContext container, WebApplicationContext context) {
		registerFilter(container, context.getBean(ApplicationFilter.class));
		registerFilter(container, new CharacterEncodingFilter("UTF-8", true));
		registerFilter(container, new OpenEntityManagerInViewFilter());
		registerFilter(container, new RequestContextFilter());
		registerFilter(container, buildConfigurableSiteMeshFilter(), "sitemesh");
	}

	private void registerFilter(ServletContext container, Filter filter, String... filterNames) {
		String filterName = filterNames.length > 0 ? filterNames[0] : filter.getClass().getSimpleName();
		container.addFilter(filterName, filter).addMappingForUrlPatterns(null, true, "/*");
	}

	private void registerSpribngMVCDispathcerServlet(ServletContext container, WebApplicationContext context) {
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(context));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}

	private ConfigurableSiteMeshFilter buildConfigurableSiteMeshFilter() {
		return new ConfigurableSiteMeshFilter() {
			@Override
			protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
				builder.addDecoratorPath("/*", "/WEB-INF/template/page-template.jsp");
			}
		};
	}
}
