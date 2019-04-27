package fi.haagahelia.OnlineStore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starts the application. Admin credentials: username: anastasiiaBurkova,
 * password: anastasiia
 * 
 * @author aburkova
 *
 */
@SpringBootApplication
public class OnlineStoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

	}
}
