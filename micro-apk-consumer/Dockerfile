FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} micro-apk-consumer.jar
ADD ./simsun.ttc /usr/share/fonts
EXPOSE 8225
ENTRYPOINT ["java","-jar","micro-apk-consumer.jar","--spring.profiles.active=prod"]