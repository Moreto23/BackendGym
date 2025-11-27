# ==========================
# Etapa 1: Build del JAR
# ==========================
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copiamos TODO el proyecto (pom.xml, src, etc.)
COPY . .

# Compilamos el proyecto y generamos el .jar
RUN mvn -B -DskipTests package


# ==========================
# Etapa 2: Imagen final para ejecutar
# ==========================
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiamos el jar que se generó en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto interno (Spring usa 8080)
EXPOSE 8080

# Render nos pasa la variable PORT, así que la usamos:
CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
