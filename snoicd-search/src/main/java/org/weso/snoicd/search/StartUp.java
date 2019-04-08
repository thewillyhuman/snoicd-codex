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
package org.weso.snoicd.search;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.weso.snoicd.search.persistence.WarmUpMemory;

//import io.micronaut.runtime.Micronaut;

/**
 * Start up entry point for the snoicd API. By executing this class you will
 * start the API on with the parameters given at applications.propperties file.
 * 
 * @author Guillermo Facundo Colunga.
 * @version since 1.0
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = RepositoryRestMvcAutoConfiguration.class)
public class StartUp {

	/**
	 * Main entry point for the snoicd API.
	 * 
	 * @param args to run the application.
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		
		try {
			WarmUpMemory.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SpringApplication.run(StartUp.class, args);
	}
}
