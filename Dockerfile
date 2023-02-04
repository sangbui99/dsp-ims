FROM openjdk:8-jdk-alpine as builder

ENV MAVEN_VERSION 3.3.9
ENV MAVEN_ARCHIVE https://mirror.downloadvn.com/apache/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz

RUN apk update && apk --update add --no-cache bash wget curl tar git && \
  wget ${MAVEN_ARCHIVE} && \
  tar -xf apache-maven-${MAVEN_VERSION}-bin.tar.gz -C /usr/local && \
  mv /usr/local/apache-maven-${MAVEN_VERSION} /usr/local/maven && \
  rm apache-maven-${MAVEN_VERSION}-bin.tar.gz

ENV MAVEN_HOME /usr/local/maven
ENV PATH=${PATH}:${MAVEN_HOME}/bin

COPY pom.xml /opt/builder/

RUN cd /opt/builder/ && mvn install

COPY . /opt/builder/

RUN cd /opt/builder/ && mvn install

FROM openjdk:8-jdk-alpine

WORKDIR /work

COPY --from=builder /opt/builder/dist/* /work/dist/

COPY run.sh .

EXPOSE 2606

CMD ["sh", "run.sh"]
