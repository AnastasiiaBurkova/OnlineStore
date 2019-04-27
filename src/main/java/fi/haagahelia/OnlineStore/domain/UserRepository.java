package fi.haagahelia.OnlineStore.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import fi.haagahelia.OnlineStore.domain.User;

/**
 * Allows CRUD functionality for users' credentials stored in MongoDB.
 * 
 * @author aburkova
 */
public interface UserRepository extends MongoRepository<User, String> {
	User findByUsername(String username);
}
