# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table product (
  id                            bigint auto_increment not null,
  productname                   varchar(255),
  description                   varchar(255),
  price                         float,
  constraint pk_product primary key (id)
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

drop table if exists product;

drop table if exists user;

