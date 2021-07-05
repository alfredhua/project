#!/bin/sh

path=`pwd`

servers=('admin' 'site')

function start_server(){
   if [ ! -d $path/.logs ]  
   then
        mkdir $path/.logs
   else
        echo ''
   fi    
   cd $path/build && nohup java -jar web-$1-1.0.0.jar  > $path/.logs/$1.txt &
}

if [ $1 == 'all' ]
then
    for server in $servers
    do 
      start_server $server
    done
else
   start_server $1
fi

