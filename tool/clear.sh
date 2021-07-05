#! /bin/sh
#####清除编译文件脚本
path=`pwd`

server_path=$path/server

cd $server_path

for file in `ls` 
  do
    if [ -d $server_path/$file ]
      then
        rm -rf $server_path/$file/.gradle/
        if [[ $file == "common" || $file == "generator" ]]
          then
           rm -rf $server_path/$file/build
           rm -rf $server_path/$file/out
           continue
        else
          echo ""
        fi
        current_file=$server_path/$file
        cd $current_file
        for children_file in `ls`
          do
            echo $current_file/$children_file
            rm -rf $current_file/$children_file/build
            rm -rf $current_file/$children_file/out
        done
    else
        echo "$file is file and ignore"
    fi
  done
