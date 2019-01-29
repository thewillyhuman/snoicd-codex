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
/*
 * This source file is part of the snoicd open source project.
 *
 * Copyright (c) 2018 willy and the snoicd project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 *
 */
package org.weso.snoicd.types;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A term node is an object that joins the snomed code, icd9 code and the icd10
 * code. It stores also the different descriptions for a given code and the
 * ChildNodes for the term.
 *
 * @author Guillermo Facundo Colunga
 * @version 20190125
 */
@Data
@Document(collection = "internalKB")
public class TermNode {

	@Id
	@JsonIgnore
	private ObjectId _id;

	// SNOMED CT Code.
	@Indexed
	private String snomedCode;

	// ICD 9 Code.
	@Indexed
	private String icd9Code;

	// ICD 10 Code.
	@Indexed
	private String icd10Code;

	// The description of the term
	private Description[] descriptions;

	// List of the children.
	// @DBRef for keeping just the reference. But then we will have to resolve the
	// terms.
	private ChildNode[] children;

	@JsonIgnore
	private String[] parents;
}
