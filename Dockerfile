# Image Java (JDK 21, recommandé pour Spring Boot)
FROM eclipse-temurin:21-jdk

# Dossier de travail
WORKDIR /app

# Copier tout le projet dans le conteneur
COPY . .

# Construire l'application avec Maven Wrapper
RUN ./mvnw clean package -DskipTests

# Exposer le port
EXPOSE 8080

# Lancer l'application
CMD ["java", "-jar", "target/*.jar"]
