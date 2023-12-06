webservice application - GENERATED WITH SPRING INITIALIZR https://start.spring.io

(C) 2023-10-25 - 2023-10-26 BY GEADAU VALENTIN

--------------------------------------------------------

REQUIREMENTS:

[GIVEN 1] - CSV FILE HAS THIS STRUCTURE [currency, value]

[GIVEN 2] - CSV FILE IS ON A PATH OUTSIDE THE PROJECT ON A PHYSICAL DRIVE

[REQ 1] - WRITE AN REST ENDPOINT THAT PARSES A CSV FILE AND RETURNS AVERAGE VALUES FOR A CURRENCY.

[REQ 2] - ONLY : GBP, USD, EUR, CHF CURRENCIES ARE SUPPORTED, OTHER REQUESTED CURRENCIES SHOULD RESULT IN ERROR
- NOTE that only the requested currencies are validated, not the ones present in the CSV

[REQ 3] - WRITE A 2ND REST ENDPOINT THAT PARSES A CSV FILE AND RETURNS A SORTED LIST OF DISTINCT CURRENCIES
 PRESENT IN THE CSV

[REQ 4] - PROVIDING AN SUPPORTED CURRENCY BUT NOT PRESENT IN CSV SHOULD RETURN AN 0 AVERAGE RESULT

[REQ 5] - 100% CODE COVERAGE (UNIT TESTING)
- JACOCO was integrated in order to check that we have full coverage 

[REQ 6] - OPENAPI INTEGRATION AND DOCUMENTATION OF API(S) (SWAGGER 3.0) 

[REQ 7] - ERROR HANDLING IF REQUESTED AVERAGE IS FOR UNSUPPORTED CURRENCY

[REQ 8] - ERROR HANDLING IF FULL PATH NOT PROPERLY CONFIGURED
- FOR both REQ 7, 8 a controller advice class was used CustomExceptionHandler

[REQ 9] - NO UNSOLVED CODE WARNINGS, NO SUPPRESSED WARNINGS, JAVADOC, CLEAN CODE (NO MAGIC NUMBERS, STRINGS, \
DUPLICATIONS ETC.)  

[REQ 10] - PROPER CALCULATIONS FOR REAL VALUES (PROPER DATA TYPES)
- BigDecimal was used as it is recommended for finance calculus

[REQ 11] - USE LATEST VERSIONS IF POSSIBLE
- Java 17 Amazon Corretto still has some open problems with MAVEN so I decided to use this version
- Maven 3.8.8 was also used and it works (FROM INTELLIJ ONLY BUT NOT FROM COMMAND LINE)
- Jacoco 0.8.10 was used
- JUNIT 5 (JUPITER) was used
- Swagger 3.0 was used
- Mockito 1.9.5
--------------------------------------------------------

MAIN GUIDE USED - https://spring.io/guides/gs/spring-boot/

PROJECT ROOT FOLDER - C:/Users/Valentin/IdeaProjects/webservice

IDE USED - IntelliJ IDEA 2023.2.3 (Community Edition) 

--------------------------------------------------------

TO COMPILE - MVN CLEAN INSTALL (FROM INTELLIJ)

TO EXECUTE - java -jar webservice-1.0.0.jar com.vali.webservice.WebserviceApplication

TO CONFIGURE - currencies.full.path from application.properties holds the DEFAULT FULL PATH of the CSV FILE

--------------------------------------------------------

API DOCUMENTATION - SWAGGER 3.0 (OPENAPI) http://localhost:8080/swagger-ui/index.html 

http://localhost:8080/getAverage/USD

http://localhost:8080/getAverage/EUR

http://localhost:8080/getAvailableCurrencies

FOR AVERAGE CALCULATIONS, java BigDecimal was used (recommended for finance calculations)

--------------------------------------------------------

APACHE CSV - WAS USED TO PARSE CSV FILE CONFIGURED IN APPLICATION.PROPERTIES 

DEFAULT FULL PATH IS: c:/Users/Valentin/IdeaProjects/currencies.csv 

EXCEPTIONS ARE HANDLED - USING CONTROLLER ADVICE 

CODE: 100% COVERAGE - JACOCO INTEGRATION (JUNIT 5 & MOCKITO)

TO GENERATE REPORT RUN -> mvn clean jacoco:prepare-agent install jacoco:report (FROM INTELLIJ)

TO VIEW REPORT OPEN IN BROWSER -> file:///C:/Users/Valentin/IdeaProjects/webservice/target/site/jacoco/index.html

---------------------------------------------------------

PROBLEMS ENCOUNTERED:

[KNOWN - JAVA 17 / MAVEN SPECIFIC] - COMMAND LINE MVN CLEAN INSTALL not working properly with JAVA 17 projects
however, the INTELLIJ somehow properly executes the maven goals

[FIXED] - INTEGRATE JUNIT5 AND MOCKITO

[FIXED] - INTEGRATE JACOCO

[FIXED] - INTEGRATE OPENAPI 3.0 (SWAGGER)

[FIXED] - FIND AND USE A GOOD CSV PARSER

[FIXED] - UNIT TESTS FOR MAIN CLASS

[FIXED] - IMPLEMENT PROPER ERROR HANDLING (INTERCEPTOR) TO AVOID HAVING BIG REST ENDPOINTS

[FIXED] - deprecated method used to construct CSVRecord data for unit testing purposes