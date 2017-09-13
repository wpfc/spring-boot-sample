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
//@ComponentScan(basePackages="cn.edu.ntu") �ȼ��� <beans><context:component-scan base-package="cn.edu.ntu"/></beans>
public class MvcConfig extends WebMvcConfigurerAdapter  {

	private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);
	
	/**
	 * �����������
	 */
	@Override
    public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**");
    }
	
	@Bean
	public InternalResourceViewResolver configureInternalResourceViewResolver(){
		logger.info("�Զ���mvc����ͼ����");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	/**
	 * �ȼ���
	 * spring.mvc.static-path-pattern=/static/**
     * spring.resources.static-locations=/static/
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
	
	/**
	 * 404ҳ�洦��  
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
	            // ����
	            String result = json + "�����ˣ�";
	            // ���
	            outputMessage.getBody ().write (result.getBytes ());
			}
		};
	}*/
	
	/**
	 * ���һ��filter����������MDC�����м����ӡ����
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
	 * RestTemplate ���з������
	 *  
	 */
	@Bean
	public RestTemplate restTemplate(){
		
		// �����ӱ���30��
        PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
        // ��������
        pollingConnectionManager.setMaxTotal(1000);
        // ͬ·�ɵĲ�����
        pollingConnectionManager.setDefaultMaxPerRoute(1000);
 
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(pollingConnectionManager);
        // ���Դ�����Ĭ����3�Σ�û�п���
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(2, true));
        // ���ֳ��������ã���Ҫ��ͷ���Keep-Alive
        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
 
        HttpClient httpClient = httpClientBuilder.build();
 
        // httpClient�������ã��ײ�������RequestConfig
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        // ���ӳ�ʱ
        clientHttpRequestFactory.setConnectTimeout(5000);
        // ���ݶ�ȡ��ʱʱ�䣬��SocketTimeout
        clientHttpRequestFactory.setReadTimeout(5000);
        // ���Ӳ����õĵȴ�ʱ�䣬���˹������������ã��������Ӳ�����ʱ��ʱ��������������Ե�
        clientHttpRequestFactory.setConnectionRequestTimeout(200);
        // �����������ݣ�Ĭ��ֵ��true��ͨ��POST����PUT������������ʱ�����齫�����Ը���Ϊfalse������ľ��ڴ档
        // clientHttpRequestFactory.setBufferRequestBody(false);
 
 
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(clientHttpRequestFactory);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
		
		return restTemplate;
	}
}
