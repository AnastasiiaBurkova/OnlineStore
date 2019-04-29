package fi.haagahelia.OnlineStore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.OnlineStore.domain.Item;
import fi.haagahelia.OnlineStore.domain.File;
import fi.haagahelia.OnlineStore.domain.FileRepository;
import fi.haagahelia.OnlineStore.domain.ItemRepository;
import fi.haagahelia.OnlineStore.domain.User;
import fi.haagahelia.OnlineStore.domain.UserRepository;

/**
 * Functionality testing.
 * 
 * @author aburkova
 */
@DataMongoTest
@RunWith(SpringRunner.class)
public class OnlineStoreApplicationMongoRepoTest {

	@Autowired
	private ItemRepository itemrepo;
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private FileRepository filerepo;

	/**
	 * Add new item testing.
	 */
	@Test
	public void createNewItem() {
		Item item = new Item("Table", 15, "wide");
		itemrepo.save(item);
		assertThat(item.getId()).isNotNull();
	}

	/**
	 * Add new user testing.
	 */
	@Test
	public void createNewUser() {
		User user = new User("Anastasiia", "$2a$06$iWJkiUp6tTCfxItYgjfp3e5z2SgYd4GKky0JENTlJq97gCVkAkV0C", "USER");
		userrepo.save(user);
		assertThat(user.getId()).isNotNull();
	}

	/**
	 * Add new file testing.
	 */
	@Test
	public void getNewFile() {
		File file = new File();
		filerepo.save(file);
		assertThat(file.getId()).isNotNull();
	}

	/**
	 * Adding and deleting the user.
	 */
	@Test
	public void deleteUser() {
		User user = new User("Anastasiia", "$2a$06$iWJkiUp6tTCfxItYgjfp3e5z2SgYd4GKky0JENTlJq97gCVkAkV0C", "USER");
		userrepo.save(user);
		assertThat(user).isNotNull();
		userrepo.deleteById(user.getId());
		Optional<User> deletedUser = userrepo.findById(user.getId());
		assertThat(deletedUser).isNotPresent();
	}

	/**
	 * Adding and deleting the item.
	 */
	@Test
	public void deleteItem() {
		Item item = new Item("Table", 15, "wide");
		itemrepo.save(item);
		assertThat(item.getId()).isNotNull();
		itemrepo.deleteById(item.getId());
		Optional<Item> deletedItem = itemrepo.findById(item.getId());
		assertThat(deletedItem).isNotPresent();
	}

	/**
	 * Adding and deleting the file.
	 */
	@Test
	public void deleteFile() {
		File file = new File();
		filerepo.save(file);
		assertThat(file.getId()).isNotNull();
		filerepo.deleteById(file.getId());
		Optional<File> deletedFile = filerepo.findById(file.getId());
		assertThat(deletedFile).isNotPresent();
	}

	/**
	 * Testing that username anastasiiaBurkova has ADMIN role.
	 */
	@Test
	public void findByUserName() {
		User users = userrepo.findByUsername("anastasiiaBurkova");
		assertThat(users.getRole().equals("ADMIN"));
	}

}
