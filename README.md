# People Finder backend 
[![Build Status](https://travis-ci.org/atwjsw/peoplefinder.svg?branch=master)](https://travis-ci.org/atwjsw/peoplefinder) [![codecov](https://codecov.io/gh/atwjsw/peoplefinder/branch/master/graph/badge.svg)](https://codecov.io/gh/atwjsw/peoplefinder)

### start application:
 mvn spring-boot:run

### run test:
 mvn clean test
 
### test API: use postman
* list all users: http://localhost:8080/persons
* search by first name: http://localhost:8080/persons?firstName=John
* search by first name: http://localhost:8080/persons?lastName=Doe

### build docker image
mvn install dockerfile:build

### start docker instance
docker run -p 8080:8080 -t atwjsw/peoplefinder



