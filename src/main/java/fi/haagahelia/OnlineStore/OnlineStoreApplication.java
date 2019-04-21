package fi.haagahelia.OnlineStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fi.haagahelia.OnlineStore.domain.User;
import fi.haagahelia.OnlineStore.domain.Item;
import fi.haagahelia.OnlineStore.domain.ItemRepository;
import fi.haagahelia.OnlineStore.domain.UserRepository;

@SpringBootApplication
public class OnlineStoreApplication implements CommandLineRunner {
	
	@Autowired
	private ItemRepository itemrepository;
	
	@Autowired
	private UserRepository userrepository;

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreApplication.class, args);
	
	}

	@Override
	public void run(String... args) throws Exception {
		//itemrepository.save(new Item("Chair", 18.00, "good", "perfect"));
		//User user1 = new User("user", "$2a$06$iWJkiUp6tTCfxItYgjfp3e5z2SgYd4GKky0JENTlJq97gCVkAkV0C", "USER");
		//User user2 = new User("anastasiiaBurkova", "$2a$10$BiJikCNoU4D80x4OEhzL4upHkoKKjAKYmubXo.0s4Ym37pP7Ypmxi", "ADMIN");
		//userrepository.save(user1);
		//userrepository.save(user2);
		
		//for admin
		//username:anastasiiaBurkova
		//password:anastasiia
		
	}
	
	

}
