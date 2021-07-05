#!/bin/sh

pm2 stop all

path=`pwd`

site=('admin', 'blog' , 'web')


function build_site(){
 if [ $1 == 'admin' ]
 then
    cd $path/web-admin && yarn install 
 else
    cd $path/web-site/$1-site && yarn install
 fi  
}

if [ $1 == 'all' ]
then
    for web in $site
    do
      build_site $web 
    done
else
    build_site $1
fi



