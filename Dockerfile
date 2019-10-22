FROM openjdk:8-jdk-alpine
MAINTAINER javaonfly
COPY target/ABSolutions-CurrencyExchange-1.0-SNAPSHOT.jar /opt/lib/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/lib/ABSolutions-CurrencyExchange-1.0-SNAPSHOT.jar"]
EXPOSE 80