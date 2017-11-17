package org.spring.boot.sample.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {

	@Autowired
	private Environment environment;
	
	/**
	 * 默认连接  localhost:6379
	 * 
	 * 需要自定义host的连接的方法
	 * 1.通过全局环境对象 environment 
	 * 
	 * 如果需要从特定的文件中读取属性：
	 *       在配置类中采用@Component的方式注册为组件，
	 *       然后使用@PropertySource来指定自定义的资源目录
	 */
	@Bean
//	@ConfigurationProperties(prefix="spring.redis")
	public RedisConnectionFactory redisConnectionFactory(){
		JedisConnectionFactory jcf = new JedisConnectionFactory();
//		jcf.setHostName(environment.getProperty("spring.redis.host"));
		return jcf;
	}
	
	@Bean
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RedisTemplate<String, Object> redisTemplate(){
		RedisTemplate redisTemplate = new RedisTemplate();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		return redisTemplate;
	}
	
}
