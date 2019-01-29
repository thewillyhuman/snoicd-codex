/*******************************************************************************
 * MIT License
 * 
 * Copyright (c) 2019 CODE OWNERS (See CODE_OWNERS.TXT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package org.weso.snoicd.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.weso.snoicd.types.Concept;

/**
 * Repository for the concepts, implemented as an extension of the
 * MongoRepository so it connects with MongoDB with the properties given at the
 * file application.propperties.
 * 
 * @author Guillermo Facundo Colunga
 * @version since 1.0
 */
public interface ConceptsRepository extends MongoRepository<Concept, ObjectId> {

	/**
	 * Finds and returns a {@link List} of concepts that has as the code field the
	 * code given as parameter. In theory the code should be unique in the database
	 * but to add flexibility we return a {@link List} and keep as unique index the
	 * _id field of the {@link Concept}.
	 * 
	 * @param code of the concept to find.
	 * @return a {@link List} containing all the concepts that match the given code.
	 */
	public List<Concept> findByCode(String code);

	/**
	 * Finds and returns a {@link List} of concepts that contains on its
	 * descriptions the given text. As each {@link Concept} can have multiple
	 * descriptions the query check that the given description is found completely
	 * or partially at any description of the code. It does not differentiate
	 * between upper and lower cases.
	 * 
	 * @param description of the concepts to find. It does not differentiate between
	 *                    upper and lower cases.
	 * @return a {@link List} containing all the concepts that match partially or
	 *         completely the given description.
	 */
	@Query("{'descriptions': {$regex: ?0, $options: 'i' }})")
	public List<Concept> findByDescription(String description);

}
