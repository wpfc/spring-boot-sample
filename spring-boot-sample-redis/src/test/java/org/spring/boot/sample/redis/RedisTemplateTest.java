package org.spring.boot.sample.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTemplateTest {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private static final String REDIS_CONTENT = "REDIS_CONTENT";
	
	@Test
	public void test(){
		
		if(!redisTemplate.hasKey(REDIS_CONTENT)){
			redisTemplate.opsForValue().set(REDIS_CONTENT, "Hello World");
		}
		Assert.assertEquals("Hello World", redisTemplate.opsForValue().get(REDIS_CONTENT));
		
	}
	
}
