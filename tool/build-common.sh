#!/bin/sh

cd ./server  

./gradlew :pro-common:common-spring-boot-api:clean 

./gradlew :pro-common:common-spring-boot-api:jar  

./gradlew :pro-common:common-spring-boot-api:publishToMavenLocal


./gradlew :pro-common:common-spring-boot-starter:clean

./gradlew :pro-common:common-spring-boot-starter:jar

./gradlew :pro-common:common-spring-boot-starter:publishToMavenLocal
