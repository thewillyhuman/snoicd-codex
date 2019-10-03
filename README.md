<img src="https://github.com/thewilly/snoicd-codex/blob/master/docs/snoicd-codex-logo.png" alt="Snomed logo" height="50">

# SNOMED-CT & ICD Codex 

|| **Architecture** | **Status** | **Tests Coverage**
|:------:|:-:|:----------:|:---:|
|**Ubuntu Trusty 14.04**|x86_64|[![Build Status](https://travis-ci.org/thewilly/snoicd-codex.svg?branch=master)](https://travis-ci.org/thewilly/snoicd-codex)|[![codecov](https://codecov.io/gh/thewilly/snoicd-codex/branch/master/graph/badge.svg)](https://codecov.io/gh/thewilly/snoicd-codex)|

## Welcome to snoicd-codex
Snoicd-codex is a set of tools that allow to crawl, index and perform queries over the ICD-9, ICD-10 and SNOMED CT terminologies. Thanks to the previous crawl and indexation it is able to achieve very low latency queries and it occupies a no more than a Gb.

It's architecture its based on the well known Google search engine where they have a crawler that collects data about websites, and indexer to reduce all the information to a physical location in their databases/indexes and a search algorithm that given a query finds the best matching results in their indexes.

In the following illustration the descrived architecture can be perfectly seen.

<img src="https://github.com/metacare/snoicd-codex/blob/master/docs/snoicd-codex-schema.png" alt="Snomed logo" height="250">

### Crawler
The crawler is in charge of finding the data among the different terminologies, add it to a common file-system for the indexer to look for and add the relations between the crawled data. At the beggining of the crawl process we have a set of unstructure and unlinked files meanwhile at the end of the process we have a unique JSON file containing all the crawled data. This file will be the one readed by the indexer.

### Indexer
The indexer module starts by reading the file served by the crawler and it creates two index files, one where the concepts are indexed by its terminology unique code and another where concepts are text-indexed by its descriptions.

### Search
The searcher is an api-structured module that by means of some algorithms it allows, trough REST requests to execute queries over the indexed data. At the time it allows queries by terminology codes and free text queries. Thanks to the previous indexation of the crawled data it performs free text queries over all the set of descriptions at less than 10ms. That is for unique words and n words with intersection search.

## Getting started
These instructions give the most direct path to work with this module.

### System Requirements
As the project is developed in java macOS, Windows and Linux distributions are natively supported. Of course you will need the latest **JDK** available and haveing **Docker** installed on your computer. Also, depending on where are you going to run the database, you will need internet connection or MongoDB installed and running on your machine.

#### Java Development Kit (JDK) :warning: No support for java 10 nor 11 :warning:
A Java Development Kit (JDK) is a program development environment for writing Java applets and applications. It consists of a runtime environment that "sits on top" of the operating system layer as well as the tools and programming that developers need to compile, debug, and run applets and applications written in the Java programming language.

If you do not has the latest stable version download you can download it [here](http://www.oracle.com/technetwork/java/javase/downloads).

#### Docker
Docker provides container software that is ideal for developers and teams looking to get started and experimenting with container-based applications. It can be downloaded [here](https://www.docker.com/) for your favourite OS.

### Deploying snoicd-codex
These instructions give the most direct path to a working snoice-codex. First thing to do is to clone the repository on to your local computer, for that:
  
``` shell
git clone https://github.com/thewilly/snoicd-codex
```
  
Then you will need to change your working directory to the snoicd-codex, build the api sources and finally deploy the docker container, to do so:
  
```
cd snoicd-codex;
cd api;
mvn package -DskipTests;
cd ..;
docker-compose up;
```
  
Notice that once the docker-compose services start it will take between 1 and 5 minutes for services to start. Once the services started you will be able to connect at http://localhost:8082. Find more information about the API [here](/api).
This API has been documented with **Swagger 2** so at http://localhost:8082/swagger-ui.html you can try / test the behaviour of the endpoint.

## Did you find an issue?
If you find any issue or have any doubt with the system just ask by submitting an issue.


## Versions included

| **Terminology Version** | **Internal code** |
|:------:|:-----:|
|ICD-9|`icd9`|
|ICD-10|`icd10`|
|SNOMED CT|`snomed`|
