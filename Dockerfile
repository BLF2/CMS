FROM maven:3.3.9-jdk-8

MAINTAINER BLF2 blf20822@126.com

RUN mkdir /javatest
WORKDIR /javatest
COPY . /javatest
RUN wget http://apache.opencas.org/tomcat/tomcat-8/v8.0.33/bin/apache-tomcat-8.0.33.tar.gz
RUN tar -zxvf apache-tomcat-8.0.33.tar.gz
RUN mvn package
RUN rm -r ./apache-tomcat-8.0.33/webapps/*
RUN mv ./target/CMS.war ./apache-tomcat-8.0.33/webapps/ROOT.war

CMD ['/bin/bash', '/javatest/apache-tomcat-8.0.33/bin/startup.sh']
EXPOSE 8080

