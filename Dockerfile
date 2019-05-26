FROM docker.io/tomcat:latest
RUN /bin/bash -c 'rm -rf /usr/local/tomcat/webapps/ROOT; \
rm -f /usr/local/tomcat/webapps/ROOT.war'
RUN   echo "spring.profiles.active=container" >> /usr/local/tomcat/conf/catalina.properties

COPY build/libs/valmont-f-0.1.war /usr/local/tomcat/webapps/ROOT.war
COPY stopwords.csv /usr/local/tomcat/webapps/stopwords.csv
COPY clinical-stopwords.txt /usr/local/tomcat/webapps/clinical-stopwords.txt

CMD ["catalina.sh", "run"]


