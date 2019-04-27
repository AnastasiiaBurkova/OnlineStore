package fi.haagahelia.OnlineStore.domain;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Allows CRUD functionality for files stored in MongoDB.
 * 
 * @author aburkova
 */
public interface FileRepository extends MongoRepository<File, String> {
	Optional<File> findById(String id);
}
