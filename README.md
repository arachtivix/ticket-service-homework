# Ticket Service

This project was created using a starter project from https://start.spring.io/ and imported to Eclipse.  It is a SpringBoot application designed to be used at the console.

The com.walmart.ticketservice.TicketService interface provides the core API, which is driven by the command interactive command line and TicketServiceApplication classes.

This project requires JDK 8.

## Build

To build in Windows, use:
```
gradlew clean build
```
To build in a Linux or OS X machine:
```
./gradlew clean build
```
The build automatically runs all the unit tests.

## Test

To test:
```
gradlew clean test
```
or
```
./gradlew clean test
```

## Usage

Once you have built the project, change directories into build/libs.  You will find a file with the .jar extension, named "ticket-service-1.0.0-RELEASE.jar".  Run this file using the following command:
```
java -jar ticket-service-1.0.0-RELEASE.jar
```
You should see the Spring Boot masthead followed by a few lines of Spring Boot chatter, then followed by this application's usage instructions:
```
Get the number of seats with: 	numseats
Hold seats with: 				hold <num seats> <email address>
Reserve seats with: 			reserve <seat hold id> <email address>
Exit the program with: 			exit <seat hold id> <email address>
```
The application builds from a configuration assuming a 1000-seat venue.  This can be reconfigured by editing src/main/resources/application.sources where the line originally says:
```
ticket-service.seating-capacity=1000
```
