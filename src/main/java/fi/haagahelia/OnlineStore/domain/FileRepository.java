package fi.haagahelia.OnlineStore.domain;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<File, String> {
	Optional<File> findById(String id);
}
