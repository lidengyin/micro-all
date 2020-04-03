FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} micro-uaa.jar
EXPOSE 8222
ENTRYPOINT ["java", "-jar", "micro-uaa.jar"]