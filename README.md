#News Search Microservice
This repository contains Spring boot microservice with CI/CD pipeline for deployement.

## Overview
The news search microservice aggregates the search results from two news agencies apis.
These news agencies are The Guardian & The New York Times.
The aggregated results are pagingated.
The apis consumer should provide the search query to apis & optional page number.
The default page number is 1 & it can be increased to number of pages available.
The microservice is containerized using docker & has jenkins pipeline to push image to docker hub
with the tag as passed as paratmeter to build.  
The Microservice apis are available using swaggger ui, postman & any web browser.   
The microservice has sonarqube scan included in maven build stage of jenkins pipeline.

### Tools you will need
 * Maven 3.8+ is your build tool
 * Your favorite IDE but we will recommend IntelliJ. We use IntelliJ.
 * JDK 11+
 


#### Following are the design pattern used in developing this microservice.
 * Factory pattern
 * Builder Pattern
 * Template Pattern



### To run the microservice locally follow the following steps:-
1. run "mvn clean install sonar:sonar -Dsonar.projectKey=news-search-service -Dsonar.host.url=http://localhost:9000 -Dsonar.login=${sonarqube_token} -DGUARDIAN_API_KEY=${guardian_api_key} -DNYTIMES_API_KEY=${nytimes_api_key}"
   ##### where
* sonarqube_token -> access token to run sonarqube
* guardian_api_key -> api key to access The Guardian content api
* nytimes_api_key -> api key to access the New York Times article api
2. run "java -jar news-search-service-0.0.1-SNAPSHOT.jar"



### To run the microservice on docker, follow the following steps:- 
1. run "mvn clean install sonar:sonar -Dsonar.projectKey=news-search-service -Dsonar.host.url=http://localhost:9000 -Dsonar.login=${sonarqube_token} -DGUARDIAN_API_KEY=${guardian_api_key} -DNYTIMES_API_KEY=${nytimes_api_key}"
   ##### where 
* sonarqube_token -> access token to run sonarqube  
* guardian_api_key -> api key to access The Guardian content api 
* nytimes_api_key -> api key to access the New York Times article api 
2. run "docker build -t mukeshmande/news-search-service:dev ."
3. run ".\run.bat dev" for windows  or "./run.sh dev" for linux

## Architecture
### Sequence Diagram
![Alt text](src/main/resources/NewsSearchController_getNewsSearch.svg?raw=true "Sequence Diagram")


## Next
There are certain areas where improvement is required to make it production ready.
 * Requires deployement environment like k8s to run this service.
 * ControllerAdvice can be added to more effectively handle exceptions & error codes.
 * Require UI to show the search results & to have offline mode.
 * City is not provided by any of the external apis(Guardian and New York Times), so city will be null in resonse.
 * I wanted to add Actuators, but it was not compatible with the version of swagger.
