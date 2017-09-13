package boot;

import java.util.HashSet;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import cn.edu.ntu.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class DemoTest {

	private static final Logger logger = LoggerFactory.getLogger(DemoTest.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	
	@Test
	public void testFirst(){
		ResponseEntity<String> result = restTemplate.getForEntity("https://www.baidu.com", String.class);
		logger.info(result.getBody());
	}
	
	@Test
	public void test(){
		HashSet<String> set = new HashSet<String>();
		set.add("one");
		set.add("two");
		set.add("three");
		redisTemplate.opsForValue().set("1", set);
		
		HashSet<String> result = (HashSet<String>) redisTemplate.opsForValue().get("1");
		Iterator<String> iter = result.iterator();
		while(iter.hasNext()){
			String item = iter.next();
			logger.info(item);
		}
	}
	
}
