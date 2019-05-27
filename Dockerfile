FROM registry.cn-hangzhou.aliyuncs.com/micro-java/openjdk:8-jre-alpine

MAINTAINER hejianzhang@tezign.com

ENV TZ="Asia/Shanghai" HOME="/root" JVM_PARAMS=" " SPRING_PARAMS=" "

WORKDIR ${HOME}

ADD target/*.jar ${HOME}/ROOT.jar

EXPOSE 8080

CMD java $JVM_PARAMS -Djava.security.egd=file:/dev/./urandom -Dspring.config.location=file:/application.properties -jar ${HOME}/ROOT.jar $SPRING_PARAMS