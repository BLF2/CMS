FROM maven:3.3.9-jdk-8

MAINTAINER BLF2 blf20822@126.com

RUN mkdir /javatest
WORKDIR /javatest
COPY . /javatest
RUN wget http://mirror.bit.edu.cn/apache/tomcat/tomcat-8/v8.0.35/bin/apache-tomcat-8.0.35.tar.gz
RUN tar -zxvf apache-tomcat-8.0.35.tar.gz
RUN mvn package
RUN rm -r ./apache-tomcat-8.0.35/webapps/*
RUN mv ./target/CMS.war ./apache-tomcat-8.0.35/webapps/ROOT.war

CMD /javatest/apache-tomcat-8.0.35/bin/catalina.sh run
EXPOSE 8080

