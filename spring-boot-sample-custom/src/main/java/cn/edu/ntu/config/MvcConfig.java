package cn.edu.ntu.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration        
//@EnableWebMvc
//@ComponentScan(basePackages="cn.edu.ntu") 等价于 <beans><context:component-scan base-package="cn.edu.ntu"/></beans>
public class MvcConfig extends WebMvcConfigurerAdapter  {

	private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);
	
	/**
	 * 处理跨域问题
	 */
	@Override
    public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**");
    }
	
	@Bean
	public InternalResourceViewResolver configureInternalResourceViewResolver(){
		logger.info("自定义mvc的视图管理");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	/**
	 * 等价于
	 * spring.mvc.static-path-pattern=/static/**
     * spring.resources.static-locations=/static/
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
	
	/**
	 * 404页面处理  
	 * Error page location 404 must start with a '/'
	 * error: new ErrorPage(HttpStatus.NOT_FOUND, "404")
	 * right: new ErrorPage(HttpStatus.NOT_FOUND, "/404")
	 */
	@Bean
	public ErrorPageRegistrar errorPageRegistrar(){
	    return new ErrorPageRegistrar(){
			@Override
			public void registerErrorPages(ErrorPageRegistry registry) {
				registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
			}
	    };
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/404").setViewName("404");
	}
}
