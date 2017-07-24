package boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.ntu.Application;
import cn.edu.ntu.utils.BeanUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class DemoTest {

	@Test
	public void testFirst(){
		Object obj = BeanUtils.getBean("dataSource");
		System.out.println(obj.getClass().getName());
	}
	
}
