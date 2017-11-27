# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table category (
  id                            bigint auto_increment not null,
  category_name                 varchar(255),
  constraint pk_category primary key (id)
);

create table forgotten_password_code (
  id                            bigint auto_increment not null,
  user_id                       bigint,
  code                          varchar(255),
  valid_until                   datetime(6),
  constraint pk_forgotten_password_code primary key (id)
);

create table product (
  id                            bigint auto_increment not null,
  productname                   varchar(255),
  description                   varchar(255),
  price                         float,
  categories_id                 bigint not null,
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

alter table product add constraint fk_product_categories_id foreign key (categories_id) references category (id) on delete restrict on update restrict;
create index ix_product_categories_id on product (categories_id);


# --- !Downs

alter table product drop foreign key fk_product_categories_id;
drop index ix_product_categories_id on product;

drop table if exists category;

drop table if exists forgotten_password_code;

drop table if exists product;

drop table if exists user;

