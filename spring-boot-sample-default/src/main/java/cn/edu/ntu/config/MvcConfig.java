package cn.edu.ntu.config;

import java.util.List;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig {

	//error page outside of mvc
	@Bean
	public ErrorPageRegistrar configErrorPageRegistrar(){
		return new ErrorPageRegistrar() {
			
			@Override
			public void registerErrorPages(ErrorPageRegistry registry) {

				registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
				
			}
		};
	}
	
	@Bean
	public WebMvcConfigurer configWebMvcConfigurer(){
		
		return new WebMvcConfigurerAdapter() {

			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				super.addViewControllers(registry);
				registry.addViewController("/404").setViewName("404");
			}
			
		};
	}
}
