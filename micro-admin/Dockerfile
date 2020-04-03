FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} micro-admin.jar
EXPOSE 8300
ENTRYPOINT ["java", "-jar", "micro-admin.jar"]
