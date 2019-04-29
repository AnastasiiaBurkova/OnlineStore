package fi.haagahelia.OnlineStore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.OnlineStore.web.ItemController;
import fi.haagahelia.OnlineStore.web.UserController;
/**
 * Controllers testing.
 * 
 * @author aburkova
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineStoreApplicationTests {

	@Autowired
	private ItemController controller;
	
	@Autowired
	private UserController ucontroller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
		assertThat(ucontroller).isNotNull();
	}
}
