FROM codenvy/ubuntu_jdk8
COPY ./target/*.jar /usr/local/bin/app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /usr/local/bin/app.jar" ]