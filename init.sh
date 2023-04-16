#!/bin/bash
spring init --build maven --dependencies=web,webflux,data-jpa,h2 -g "au.chrissimon" -n UniversityApi universityapi.zip --description "Demo of TDD and DDD in Java.  See https://github.com/chrissimon-au/tdd-ddd-demo-java."
unzip -o universityapi.zip -d .
rm universityapi.zip