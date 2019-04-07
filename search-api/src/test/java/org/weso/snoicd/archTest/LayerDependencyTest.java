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

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * Instance of InterfaceRulesTest.java
 * 
 * @author 
 * @version 
 */
@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "org.weso.snoicd")
public class LayerDependencyTest {

	@ArchTest
    public static final ArchRule services_should_not_access_controllers =
            noClasses().that().resideInAPackage("..services..")
                    .should().accessClassesThat().resideInAPackage("..controllers..");

    @ArchTest
    public static final ArchRule persistence_should_not_access_services =
            noClasses().that().resideInAPackage("..persistence..")
                    .should().accessClassesThat().resideInAPackage("..services..");

    @ArchTest
    public static final ArchRule services_should_only_be_accessed_by_controllers_or_other_services =
            classes().that().resideInAPackage("..services..")
                    .should().onlyBeAccessed().byAnyPackage("..controllers..", "..services..", "..search..");

    @ArchTest
    public static final ArchRule services_should_only_access_persistence_or_other_services =
            classes().that().resideInAPackage("..services..")
                    .should().onlyAccessClassesThat().resideInAnyPackage("..services..", "..persistence..", "java..");

    @ArchTest
    public static final ArchRule services_should_not_depend_on_controllers =
            noClasses().that().resideInAPackage("..services..")
                    .should().dependOnClassesThat().resideInAPackage("..controllers..");

    @ArchTest
    public static final ArchRule persistence_should_not_depend_on_services =
            noClasses().that().resideInAPackage("..persistence..")
                    .should().dependOnClassesThat().resideInAPackage("..services..");

    @ArchTest
    public static final ArchRule services_should_only_be_depended_on_by_controllers_or_other_services =
            classes().that().resideInAPackage("..services..")
                    .should().onlyHaveDependentClassesThat().resideInAnyPackage("..controllers..", "..services..", "..search..");

    @ArchTest
    public static final ArchRule services_should_only_depend_on_persistence_or_other_services =
            classes().that().resideInAPackage("..services..")
                    .should().onlyDependOnClassesThat().resideInAnyPackage("..services..", "..persistence..", "java..");
}
