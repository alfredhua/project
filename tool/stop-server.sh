#!/bin/sh

path=`pwd`

function stop_admin(){
   curl -X POST 127.0.0.1:4001/actuator/shutdown
}

function stop_site(){
    curl -X POST 127.0.0.1:5001/actuator/shutdown
}

if [ $1 == 'all' ]
then
    stop_admin
    stop_site
elif [ $1 == 'admin' ]
then
   stop_admin
elif [ $1 == 'site' ]
then
   stop_site  
else
   echo 'no server'    
fi