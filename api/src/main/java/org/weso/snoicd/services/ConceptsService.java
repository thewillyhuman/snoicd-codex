package org.weso.snoicd.services;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.weso.snoicd.repository.ConceptsRepository;
import org.weso.snoicd.types.Concept;

@Service
public class ConceptsService {
	
	
	@Autowired
	ConceptsRepository repository;

	public List<Concept> getConceptByCode(String code) {
		return this.repository.findByCode(code);
	}

	public List<Concept> getConceptsByDescription(@NotNull String q) {
		return this.repository.findByDescription(q);
	}
	
}
