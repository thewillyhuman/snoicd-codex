/*
 * This source file is part of the snoicd-crawler open source project.
 *
 * Copyright (c) 2019 willy and the snoicd-crawler project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.crawler.engines.impl;

import org.weso.snoicd.crawler.engines.Crawler;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

/**
 * Instance of AbstractCrawler.java
 * 
 * @author
 * @version
 */
public abstract class AbstractCrawler extends Thread implements Crawler {

	private String uri;
	private int port;
	private String dbName;
	protected MongoDatabase hDB;

	public AbstractCrawler( String uri, int port, String dbName ) {
		this.uri = uri;
		this.port = port;
		this.dbName = dbName;
	}

	/*
	 * (non-Javadoc)
	 * @see org.weso.snoicd.crawler.engines.Crawler#crawl()
	 */
	@Override
	public void crawl() {
		// Connecting to the mongo server.
		MongoClient client = new MongoClient( new ServerAddress( this.uri, this.port ) );

		// Getting the right database.
		hDB = client.getDatabase( this.dbName );
		
		specificCrawl();
		
		client.close();
	}

	abstract void specificCrawl();
	
	@Override
	public void run() {
		this.crawl();
	}

}
