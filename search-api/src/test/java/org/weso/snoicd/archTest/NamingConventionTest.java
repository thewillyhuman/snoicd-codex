/*
 * This source file is part of the snoicd open source project.
 *
 * Copyright (c) 2019 willy and the snoicd project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package org.weso.snoicd.archTest;

import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * Instance of NamingConventionTest.java
 * 
 * @author
 * @version
 */
@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "org.weso.snoicd")
public class NamingConventionTest {

	@ArchTest
	public static ArchRule services_should_be_prefixed = classes()
			.that().resideInAPackage( "..service.." )
			.and().areAnnotatedWith( Service.class )
			.should().haveSimpleNameStartingWith( "Service" );

	@ArchTest
	public static ArchRule controllers_should_not_have_Gui_in_name = classes()
			.that().resideInAPackage( "..controller.." )
			.should().haveSimpleNameNotContaining( "Gui" );

	@ArchTest
	public static ArchRule controllers_should_be_suffixed = classes()
			.that().resideInAPackage( "..controller.." )
			.or().areAnnotatedWith( RestController.class )
			.should().haveSimpleNameEndingWith( "Controller" );

	@ArchTest
	public static ArchRule classes_named_controller_should_be_in_a_controller_package = classes()
			.that().haveSimpleNameContaining( "Controller" )
			.should().resideInAPackage( "..controllers.." );
}
