FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} micro-ueditor.jar
ADD ./simsun.ttc /usr/share/fonts
EXPOSE 8227
ENTRYPOINT ["java","-jar","micro-ueditor.jar", "--spring.profiles.active=prod"]