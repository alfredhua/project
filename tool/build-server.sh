#!/bin/sh

path=`pwd`

server_path=$path/server

rm -rf $path/build/

function mkdir_dir(){
  mkdir -pv $path/build/
}

function get_all_file(){

  mkdir_dir 

  for file in `ls $server_path` 
    do
      if [[ -d $server_path/$file && $file != 'gradle' && $file != 'pro-common' ]]
      then
          ## 进行
          jar $file
      else
        continue
      fi
    done
}

function jar(){
   if [ $file == 'pro-web' ]
   then
      mv_web_file $file 
   else
      echo "$file is not build" 
   fi
}

function mv_web_file(){
    for file in `ls $server_path/$1` 
    do
      cd $server_path && ./gradlew :pro-web:$file:bootJar && mv $server_path/pro-web/$file/build/libs/$file-1.0.0.jar $path/build/
    done
}


function mv_server_file(){
    mv $server_path/$1/$1-server/build/libs/$1-server-1.0.0.jar  $path/build/server
}

get_all_file

echo " #!/bin/sh "  >> $path/build/start.sh
echo " mkdir $path/.logs"  >> $path/build/start.sh
echo " nohup java -jar web-admin-1.0.0.jar  > $path/.logs/admin.txt &"  >> $path/build/start.sh
echo " nohup java -jar web-site-1.0.0.jar  > $path/.logs/site.txt &"  >> $path/build/start.sh
echo " #end "  >> $path/build/start.sh

chmod -R 755 $path/build/start.sh


echo " #!/bin/sh "  >> $path/build/stop.sh
echo " curl -X POST 127.0.0.1:4001/actuator/shutdown"  >> $path/build/stop.sh
echo " curl -X POST 127.0.0.1:5001/actuator/shutdown"  >> $path/build/stop.sh
echo " #end "  >> $path/build/stop.sh

chmod -R 755 $path/build/stop.sh
