#!/bin/bash

mvn clean verify
mvn sonar:sonar -Dsonar.login.secretKey=squ_65abde3fa2fbd6cd952c1e151661e9dcdcfe4de8