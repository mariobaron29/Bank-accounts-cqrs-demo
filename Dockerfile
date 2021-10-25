FROM openjdk:13-oracle
EXPOSE 80
WORKDIR /app
COPY _#{Build.Repository.Name}#/Artifact-#{Build.Repository.Name}#/*.jar /app/app.jar
CMD java -XX:MaxRAMPercentage=90 \
         -XX:OnOutOfMemoryError="kill -9 %p" \
         -jar /app/app.jar