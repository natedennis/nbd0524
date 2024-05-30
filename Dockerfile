ARG JDK_VERSION="17.0.6"
#Stage 1
FROM amazoncorretto:$JDK_VERSION as BUILD
RUN yum install -y tar which gzip # TODO remove

COPY . /app
WORKDIR /app

RUN ./mvnw clean compile package

#Stage 2
FROM amazoncorretto:$JDK_VERSION

EXPOSE 8080
COPY --from=BUILD /app/target/nbd0524.jar /opt/
WORKDIR /opt
CMD ["java","-jar","nbd0524.jar"]
