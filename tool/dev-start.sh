#!/bin/sh

path=`pwd`

server_path=$path/server

if [ $1 == 'admin' ]
then
	cd $server_path && ./gradlew :pro-web:web-admin:bootRun
elif [ $1 == 'site' ] 
then
	cd $server_path && ./gradlew :pro-web:web-site:bootRun
else
	echo "error"

fi
