package br.com.meta.avaliacao.gestaocontato;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
		String x = java.util.Base64.getUrlEncoder().encodeToString("admin:123".getBytes());
		System.out.println(x);
	}

}
