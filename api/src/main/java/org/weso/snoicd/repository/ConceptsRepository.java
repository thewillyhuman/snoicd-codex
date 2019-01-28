package org.weso.snoicd.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.weso.snoicd.types.Concept;

public interface ConceptsRepository  extends MongoRepository<Concept, ObjectId> {
	
	public List<Concept> findByCode(String code);
	
	@Query("{'descriptions': {$regex: ?0, $options: 'i' }})")
	public List<Concept> findByDescription(String description);

}
