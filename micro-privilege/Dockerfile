FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} micro-privilege.jar
ADD ./simsun.ttc /usr/share/fonts
EXPOSE 8229
ENTRYPOINT ["java","-jar","micro-privilege.jar","--spring.profiles.active=prod"]