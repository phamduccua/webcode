FROM alpine:latest

COPY . .

RUN ls -al

# C-CPP
RUN apk update \
&& apk add build-base \
&& apk add g++

# JAVA 8
RUN apk fetch openjdk8 \
&& apk add openjdk8
ENV JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"

# Python 3
RUN apk add python3 py3-pip \
&& apk add --upgrade bash

RUN chmod +x ./run.sh
ENTRYPOINT ["./run.sh"]