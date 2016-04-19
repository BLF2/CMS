FROM maven:3.3.9-jdk-8

MAINTAINER BLF2 blf20822@126.com

ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH
RUN mkdir -p "$CATALINA_HOME"

RUN mkdir /javatest
WORKDIR /javatest
COPY . /javatest
RUN wget http://apache.opencas.org/tomcat/tomcat-8/v8.0.33/bin/apache-tomcat-8.0.33.tar.gz
RUN tar -zxvf apache-tomcat-8.0.33.tar.gz
RUN mvn package
RUN rm -r ./apache-tomcat-8.0.33/webapps/*
RUN mv ./target/CMS.war ./apache-tomcat-8.0.33/webapps/ROOT.war

CMD /javatest/apache-tomcat-8.0.33/bin/catalina.sh run
EXPOSE 8080

