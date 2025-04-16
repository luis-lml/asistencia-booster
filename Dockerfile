FROM tomcat:11.0.6-jdk17
ADD target/asistenciabooster.war /Users/255_Studio/Downloads/apache-tomcat-11.0.6/webapps
EXPOSE 8080
CMD ["catalina.sh", "run"]