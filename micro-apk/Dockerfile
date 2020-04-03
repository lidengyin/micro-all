FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} micro-apk.jar
ADD ./simsun.ttc /usr/share/fonts
ADD ./hc_logo_black.jpg /usr/local
EXPOSE 8224
ENTRYPOINT ["java","-jar","micro-apk.jar"]