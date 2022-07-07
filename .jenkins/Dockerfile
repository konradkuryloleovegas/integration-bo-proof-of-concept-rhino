# <leo-ci:docker-from>
FROM openjdk:8-slim
# </leo-ci:docker-from>

# <leo-ci:docker-opts>
ENV JAVA_AGENT_OPTS ''
ENV JAVA_MEM_OPTS '-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:+UseContainerSupport -XX:InitialRAMPercentage=50.0 -XX:MinRAMPercentage=50.0 -XX:MaxRAMPercentage=75.0'
ENV JAVA_GC_OPTS ''
ENV JAVA_OPTS ''
ENV JAVA_ARGS ''
# </leo-ci:docker-opts>

# <leo-ci:docker-build>
ARG jar
ENV JAR_NAME $jar

RUN adduser --disabled-password --no-create-home --uid=1000 jvm \
    && mkdir -p /sandbox/ /var/log/platform/ \
    && chown -R jvm:jvm /sandbox/ /var/log/platform/

WORKDIR /sandbox
COPY --chown=jvm:jvm $JAR_NAME .
# </leo-ci:docker-build>

# <leo-ci:docker-labels>
ARG LABEL_URL
ARG LABEL_TITLE
ARG LABEL_SOURCE
ARG LABEL_REVISION
ARG LABEL_VERSION
ARG LABEL_CREATED

LABEL org.opencontainers.image.url=${LABEL_URL} \
      org.opencontainers.image.title=${LABEL_TITLE} \
      org.opencontainers.image.source=${LABEL_SOURCE} \
      org.opencontainers.image.revision=${LABEL_REVISION} \
      org.opencontainers.image.version=${LABEL_VERSION} \
      org.opencontainers.image.created=${LABEL_CREATED}
# </leo-ci:docker-labels>

# <leo-ci:docker-run>
USER jvm
ENTRYPOINT ["sh", "-c"]
CMD ["java $JAVA_AGENT_OPTS $JAVA_MEM_OPTS $JAVA_GC_OPTS $JAVA_OPTS -jar $JAR_NAME $JAVA_ARGS"]
# </leo-ci:docker-run>
