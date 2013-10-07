#!/bin/sh
mvn clean install -P release
cp build/jersey-common-2.3.1.jar target/base-rest/WEB-INF/lib/
