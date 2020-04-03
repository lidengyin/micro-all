FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} micro-zuul.jar
EXPOSE 8221
ENTRYPOINT ["java", "-jar", "micro-zuul.jar"]