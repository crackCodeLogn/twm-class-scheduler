APP_NAME="twm-class-scheduler-service"
APP_VERSION="1.0-SNAPSHOT"
JAVA_PARAM="-Xmx251m"

BIN_PATH=$TWM_HOME_PARENT/TWM/$APP_NAME/bin     #TWM-HOME-PARENT :: exported in .bashrc
JAR_PATH=$BIN_PATH/../target/$APP_NAME-$APP_VERSION.jar

APP_PARAMS="-Dscheduler.location=${BIN_PATH}/../landing/class-schedules.active.json"

echo "Starting '$APP_NAME' with java param: '$JAVA_PARAM', app params: '$APP_PARAMS' at '$JAR_PATH'"
java $JAVA_PARAM $APP_PARAMS -jar $JAR_PATH