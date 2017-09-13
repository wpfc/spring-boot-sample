package cn.edu.ntu.config;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import javax.servlet.DispatcherType;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cn.edu.ntu.filter.UserFilter;

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
	
	/*@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
		super.configureMessageConverters(converters);
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
		return new MappingJackson2HttpMessageConverter(){
			@Override
			protected void writeInternal(Object object, HttpOutputMessage outputMessage)
					throws IOException, HttpMessageNotWritableException {
				ObjectMapper mapper = new ObjectMapper(); 
				String json = mapper.writeValueAsString (object);
	            // 加密
	            String result = json + "加密了！";
	            // 输出
	            outputMessage.getBody ().write (result.getBytes ());
			}
		};
	}*/
	
	/**
	 * 添加一个filter拦截器，向MDC对象中加入打印内容
	 * @return
	 */
	@Bean
	public FilterRegistrationBean myFilter() {
	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(new UserFilter());
	    registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
	    return registration;
	}
	
	/**
	 * RestTemplate 进行服务访问
	 *  
	 */
	@Bean
	public RestTemplate restTemplate(){
		
		// 长连接保持30秒
        PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
        // 总连接数
        pollingConnectionManager.setMaxTotal(1000);
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(1000);
 
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(pollingConnectionManager);
        // 重试次数，默认是3次，没有开启
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(2, true));
        // 保持长连接配置，需要在头添加Keep-Alive
        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
 
        HttpClient httpClient = httpClientBuilder.build();
 
        // httpClient连接配置，底层是配置RequestConfig
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        // 连接超时
        clientHttpRequestFactory.setConnectTimeout(5000);
        // 数据读取超时时间，即SocketTimeout
        clientHttpRequestFactory.setReadTimeout(5000);
        // 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
        clientHttpRequestFactory.setConnectionRequestTimeout(200);
        // 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
        // clientHttpRequestFactory.setBufferRequestBody(false);
 
 
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(clientHttpRequestFactory);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
		
		return restTemplate;
	}
}
