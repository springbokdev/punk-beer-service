# PUNK Beer Service

This service can be started in your IDE without a specific profile, or from the command line:

``mvn spring-boot:run``

The service uses an in-memory H2 database. The beer data is loaded from the JSON files in the resource folder.

- Spring Boot 3.2.4
- JDK 17
- Maven 3.9.0

## OpenAPI definition
http://localhost:8080/swagger-ui/index.html

## H2 Console
http://localhost:8080/h2-console/

password: sa

## TODO
- Validation of the request parameters has been applied to the BeerFilter (annotated with @ModelAttribute). Should be applied to all the request parameters.
- There is an integration test for the BeerController, but a unit test should also be added for this controller and a unit test for BeerServiceImpl.
- The BeerSpecification for filtering beers is not fully implemented. Missing criteria are:
  - brewed_after
  - brewed_before
  - yeast
  - ids
- Search beers is based on one of the simple properties. An improvement of search can be based on one or more propeties.

## FIXME
- The import of the hops items data from JSON results in duplicates per ingredients in the target database, but for the malt items this is not the case. The source of this problem is in the BootStrapData loader.
- The native query for retrieving beers based on ingredients (hops and malt) in the BeerRepository interface uses a UNION. This query should be reviewed and refactored.
- the names of the hops item are not displayed in the HTTP responses, but the names of the malts do!! This is weird. Needs to be fixed.

