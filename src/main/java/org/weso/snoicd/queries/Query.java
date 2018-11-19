/*
 * This source file is part of the snoicd open source project.
 *
 * Copyright (c) 2018 willy and the snoicd project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.queries;

import lombok.Data;

/**
 * Instance of Query.java
 * 
 * @author 
 * @version 
 */
@Data
public class Query {
	private String securityToken;
	private String code;
}
