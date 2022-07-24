FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/news-search-service-0.0.1-SNAPSHOT.jar news-search-service.jar
ENTRYPOINT ["java","-jar","/news-search-service.jar"]