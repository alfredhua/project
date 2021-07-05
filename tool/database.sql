create database m_website;
create database m_auth;
create database m_blog;
create database m_public;
create database m_develop;
create database m_message;

CREATE USER 'manage'@'%' IDENTIFIED BY 'manage';

grant all privileges on *.* to 'manage'@'%' IDENTIFIED BY 'manage';

flush privileges;