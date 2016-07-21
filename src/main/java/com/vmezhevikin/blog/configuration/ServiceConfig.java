package com.vmezhevikin.blog.configuration;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan({ "com.vmezhevikin.blog.service.impl", "com.vmezhevikin.blog.controller", 
	"com.vmezhevikin.blog.filter", "com.vmezhevikin.blog.listener" })
public class ServiceConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() throws IOException {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setLocations(getResources());
		return configurer;
	}

	private static Resource[] getResources() {
		return new Resource[] { new ClassPathResource("application.properties") };
	}
}
