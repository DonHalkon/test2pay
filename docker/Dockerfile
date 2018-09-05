FROM jboss/wildfly

ENV JBOSS_HOME /opt/jboss/wildfly

#add deployment
ADD testear-1.0-SNAPSHOT.ear $JBOSS_HOME/standalone/deployments/

USER root

# download postgres driver
RUN curl --silent https://jdbc.postgresql.org/download/postgresql-42.2.5.jar -o $JBOSS_HOME/standalone/deployments/postgresql-42.2.5.jar

# add confoguration
ADD standalone.xml $JBOSS_HOME/standalone/configuration/

#use jboss user to start wildfly
USER jboss

# Start WildFly using the standalone mode
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
