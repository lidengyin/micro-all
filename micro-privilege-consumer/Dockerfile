FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} micro-privilege-consumer.jar
ADD ./simsun.ttc /usr/share/fonts
EXPOSE 8230
ENTRYPOINT ["java","-jar","micro-privilege-consumer.jar","--spring.profiles.active=prod"]