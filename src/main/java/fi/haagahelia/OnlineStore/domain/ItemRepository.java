package fi.haagahelia.OnlineStore.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Allows CRUD functionality for items stored in MongoDB.
 * 
 * @author aburkova
 */
@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
	void deleteById(String id);
}
