FROM eclipse-temurin:21.0.3_9-jdk

EXPOSE 8080

WORKDIR /root

COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

# Descarga de dependencias
RUN ./mvnw dependency:go-offline

# Copiar codigo fuente dentro del contenedor
COPY ./src /root/src

# Construir nuestra aplicacion
RUN ./mvnw clean install -DskipTests

# Levantar aplicacion cuando el contenedor inicie
# ENTRYPOINT ["java", "-jar", ""]
CMD ["./mvnw", "spring-boot:run"]