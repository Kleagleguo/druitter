#!/usr/bin/env bash

# make sure you have druid lib under /usr/lib/druid and properties config
NODE_TYPE=$1
echo "starting $NODE_TYPE"

function check_dir_exisit {
  DIR=$1
  if [ ! -d $DIR ]
  then
    echo "$DIR doesn't exist!"
    exit 1
  fi
}

function check_file_exisit {
  FILE=$1
  if [ ! -f $FILE ]
  then
    echo "$FILE doesn't exist!"
    exit 1
  fi
}

CLASSPATH=''
JAVA_ARGS="-Xmx512m -Duser.timezone=UTC -Dfile.encoding=UTF-8 -Dfile.io.tmpdir=/tmp -Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager"

DRUID_LIB_DIR='/usr/local/lib/druid'
check_dir_exisit $DRUID_LIB_DIR

NODE_CONFIG="/usr/etc/druid_config/$NODE_TYPE"
check_dir_exisit $NODE_CONFIG

COMMON_CONFIG='/usr/etc/druid_config/common_resource'
check_dir_exisit $COMMON_CONFIG

if [ "$NODE_TYPE" = 'realtime' ]
then
  REALTIME_SPEC='/usr/etc/druid_config/realtime/realtime_spec'
  check_file_exisit $REALTIME_SPEC
  JAVA_ARGS="${JAVA_ARGS} -Ddruid.realtime.specFile=${REALTIME_SPEC}"
fi

CLASSPATH=${CLASSPATH}:${NODE_CONFIG}:${COMMON_CONFIG}:${DRUID_LIB_DIR}/*

echo "CLASSPATH=$CLASSPATH"

set -x
sudo java ${JAVA_ARGS} -classpath ${CLASSPATH}  io.druid.cli.Main server $NODE_TYPE