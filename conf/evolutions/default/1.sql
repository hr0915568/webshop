# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table forgotten_password_code (
  id                            bigint auto_increment not null,
  user_id                       bigint,
  code                          varchar(255),
  valid_until                   datetime(6),
  constraint pk_forgotten_password_code primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  firstname                     varchar(255),
  email                         varchar(255),
  password                      varchar(255),
  lastname                      varchar(255),
  done                          tinyint(1) default 0 not null,
  due_date                      datetime(6),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id)
);


# --- !Downs

drop table if exists forgotten_password_code;

drop table if exists user;

