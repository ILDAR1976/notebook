FROM ubuntu:20.04

ENV JAVA_OPTS -server -Dfile.encoding=UTF-8

RUN apt update
RUN apt -y install wget
RUN apt -y install openjdk-17-jdk
RUN wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.62/bin/apache-tomcat-9.0.62.tar.gz
RUN tar xfvz apache-tomcat-9.0.62.tar.gz
RUN mv apache-tomcat-9.0.62 tomcat

COPY ./build/libs/base-0.0.1.war /tomcat/webapps/base.war

CMD ["/tomcat/bin/catalina.sh","run"]