#!/bin/sh

path=`pwd`

site=('admin', 'site')


function start_site(){
 if [ $1 == 'admin' ]
 then
    cd $path/web-admin && npm run build && pm2 restart process.json
 else
    cd $path/web-site && npm run build && pm2 start npm --name 'site' -- run start
 fi  
}


if [ $1 == 'all' ]
then
    for web in $site
    do
     start_site $web 
    done
else
    start_site $1
fi



