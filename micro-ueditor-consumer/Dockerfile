FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} micro-ueditor-consumer.jar
ADD ./simsun.ttc /usr/share/fonts
EXPOSE 8228
ENTRYPOINT ["java","-jar","micro-ueditor-consumer.jar","--spring.profiles.active=prod"]