# ws-training
[![Travis Build Status][travis-image]][travis-url-main]

The collection of implementation examples for SOAP webservice usage.

## Pre-requisities
* JDK 10
* Maven 3
* Lombok (installed into IDE)
* WildFly 14
* Tomcat 9

## Features
1. **JAX-B** - class generation from WSDL 
1. **JAX-WS** - JavaEE standard
    1. The WS implementation for WildFly & Tomcat
    1. Tests representing different client approaches for consuming WS 
1. **Framework CXF** - implementation of WS for WildFly
1. **Spring Boot** - implementation of WS via SpringBoot for WildFly. 

## Used Technologies

| Tool             | Version      | Project                                            | Description                    |
| ---------------- | ------------ | --------------------------------------             | ------------------------------ |
| Maven            | 3.x          | all                                                | Build                          |
| Java             | 10           | all                                                | Java platform                  |
| JavaEE           | 7.0          | ws-training-cxf, ws-training-jaxws, ws-training-sb | Java Enterprise Edition        |
| JAX-B            | 2.3.1        | all                                                | Class binding                  |
| SpringBoot       | 2.1.1        | all                                                | Dependency Management          |
| Lombok           | N/A          | all                                                | Simplification of Java classes |
| JUnit            | 5.3.2        | ws-training-client-jaxws                           | Unit testing with JUnit5       |
| CXF              | 3.2.7        | ws-training-cxf                                    | WA framework                   |

## Services
| Service          | Technology                                          |
| ---------------- | --------------------------------------------------- |
| CalcService      | JAX-WS (@WebService & Endpoint) for WildFly, CXF    |
| HelloWebService  | JAX-WS for WildFly & Tomcat, SpringBoot for WildFly |
| LoremWebService  | JAX-WS (@WebService) for WildFly, CXF               |

## Tomcat Setup
1. Configure users for deployment - see [How to deploy Maven based war file to Tomcat](https://www.mkyong.com/maven/how-to-deploy-maven-based-war-file-to-tomcat/)
1. Add `<server/>` element to `settings.xml` with:
   1. ID=tomcat-local
   1. Username & Password
1. Change *Server location* in Eclipse to value *Use Tomcat Installation*

[travis-url-main]: https://travis-ci.org/arnosthavelka/ws-training
[travis-image]: https://travis-ci.org/arnosthavelka/ws-training.svg?branch=master
