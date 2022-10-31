# ws-training
[![Java CI with Maven][github-image]][github-url-main] [![Sonar quality gate][sonar-quality-gate]][sonar-url] [![Sonar coverage][sonar-coverage]][sonar-url] [![Sonar bugs][sonar-bugs]][sonar-url] [![Sonar vulnerabilities][sonar-vulnerabilities]][sonar-url] [![MIT licensed][mit-badge]](./LICENSE.txt)

The collection of implementation examples for SOAP webservice usage.

## Pre-requisities
* JDK 17
* Maven 3.8.x
* Lombok (installed into IDE)
* WildFly (tested on version 25)
* Tomcat (tested on version 9 - JavaEE)

## Features
1. **JAX-B** - class generation from WSDL 
1. **JAX-WS** - JavaEE standard
    1. The WS implementation for WildFly & Tomcat
    1. Tests representing different client approaches for consuming WS 
1. **Framework CXF** - implementation of WS for WildFly
1. **Spring Boot** - implementation of WS via SpringBoot for WildFly. 

## Used Technologies

| Tool             | Project                                            | Description                    |
| ---------------- | -------------------------------------------------- | ------------------------------ |
| JAX-B            | all                                                | Class binding                  |
| SpringBoot       | all                                                | Dependency Management          |
| Lombok           | all                                                | Simplification of Java classes |
| JUnit            | ws-training-client-jaxws                           | Unit testing with JUnit5       |
| CXF              | ws-training-cxf                                    | WA framework                   |

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

[github-url-main]: https://github.com/arnosthavelka/ws-training/actions/workflows/maven.yml
[github-image]: https://github.com/arnosthavelka/ws-training/actions/workflows/maven.yml/badge.svg

[sonar-url]: https://sonarcloud.io/dashboard?id=arnosthavelka_ws-training
[sonar-quality-gate]: https://sonarcloud.io/api/project_badges/measure?project=arnosthavelka_ws-training&metric=alert_status
[sonar-coverage]: https://sonarcloud.io/api/project_badges/measure?project=arnosthavelka_ws-training&metric=coverage
[sonar-bugs]: https://sonarcloud.io/api/project_badges/measure?project=arnosthavelka_ws-training&metric=bugs
[sonar-vulnerabilities]: https://sonarcloud.io/api/project_badges/measure?project=arnosthavelka_ws-training&metric=vulnerabilities
[mit-badge]: https://img.shields.io/badge/license-MIT-maroon.svg
