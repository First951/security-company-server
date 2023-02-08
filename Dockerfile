FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
RUN mkdir /opt/security-company-server
COPY ${JAR_FILE} /opt/security-company-server/security-company-server.jar
ENTRYPOINT ["java","-jar","/opt/security-company-server/security-company-server.jar"]
