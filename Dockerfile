# Image Java (JDK 21, recommandé pour Spring Boot)
FROM eclipse-temurin:21-jdk

# Dossier de travail
WORKDIR /app

# Copier tout le projet dans le conteneur
COPY . .

# Donner la permission à mvnw et construire l'application
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Exposer le port
EXPOSE 8080

# Lancer le WAR produit
CMD ["java", "-jar", "target/gl-0.0.1-SNAPSHOT.war"]

