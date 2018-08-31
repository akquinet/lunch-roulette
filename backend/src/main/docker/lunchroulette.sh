#!/bin/sh

set JAVA_OPTS=-ea
export JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,address=8787,server=y,suspend=n"

(cd / ; sleep 5 ; nohup java ${JAVA_OPTS} -jar lunchroulette.jar) &

exec /docker-entrypoint.sh postgres
