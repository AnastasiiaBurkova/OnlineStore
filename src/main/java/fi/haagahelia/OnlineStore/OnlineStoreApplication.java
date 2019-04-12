package fi.haagahelia.OnlineStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fi.haagahelia.OnlineStore.domain.Item;
import fi.haagahelia.OnlineStore.domain.ItemRepository;

@SpringBootApplication
public class OnlineStoreApplication implements CommandLineRunner {
	
	@Autowired
	private ItemRepository itemrepository;

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreApplication.class, args);
	
	}

	@Override
	public void run(String... args) throws Exception {
		//itemrepository.save(new Item("Chair", 18.00, "good", "perfect"));
		
	}
	
	

}
