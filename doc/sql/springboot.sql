drop database if exists springboot;
create database springboot;

drop table if exists  user;
create table user (
  id bigint auto_increment,
  name varchar(100),
  age varchar(100),
  constraint pk_users primary key(id)
) charset=utf8 ENGINE=InnoDB;