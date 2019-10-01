FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
COPY conceptID.index	conceptID.index
COPY descriptions.index	descriptions.index
EXPOSE 8082
ENTRYPOINT ["java", "-XX:-UseGCOverheadLimit","-cp","app:app/lib/*","org.weso.snoicd.search.StartUp"]
