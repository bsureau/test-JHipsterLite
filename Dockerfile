FROM openjdk:21-slim AS build
COPY . /code/jhipster-app/
WORKDIR /code/jhipster-app/
RUN chmod +x mvnw \
    && ./mvnw package -B \
    -DskipTests \
    -Dmaven.javadoc.skip=true \
    -Dmaven.source.skip \
    -Ddevelocity.cache.remote.enabled=false \
    && mv /code/jhipster-app/target/*-exec.jar /code/

FROM openjdk:21-slim
COPY --from=build /code/*.jar /code/
RUN \
    # configure the "jhipster" user
    groupadd jhipster && \
    useradd jhipster -s /bin/bash -m -g jhipster -G sudo && \
    echo 'jhipster:jhipster'|chpasswd
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS=""
USER jhipster
ENTRYPOINT exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /code/*.jar
EXPOSE 7471
