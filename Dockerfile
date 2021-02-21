FROM java:8
COPY . .
COPY ./build/libs/face-recognition-* app.jar
EXPOSE 10011
ENTRYPOINT ["java", "-jar", "app.jar"]