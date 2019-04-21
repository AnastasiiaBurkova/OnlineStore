package fi.haagahelia.OnlineStore.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import fi.haagahelia.OnlineStore.domain.User;

public interface UserRepository extends MongoRepository<User, String>{
	User findByUsername(String username);
}
