FROM postgres

RUN set -x && apt-get update && apt-get install -y openjdk-8-jre

# Expected in target/docker directoy, copied by maven resources plugin

COPY lunchroulette.sh /
COPY lunchroulette.jar /

RUN chmod 755 /lunchroulette.sh

EXPOSE 8080

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

ENV POSTGRES_DB lunchroulette
ENV POSTGRES_USER croupier
ENV POSTGRES_PASSWORD rien-ne-va-plus

ENTRYPOINT ["/lunchroulette.sh"]

