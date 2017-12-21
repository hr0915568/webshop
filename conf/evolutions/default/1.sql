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

create table invoice (
  id                            bigint auto_increment not null,
  ordermodel_id                 bigint,
  total_price                   bigint,
  tax_amount                    bigint,
  constraint uq_invoice_ordermodel_id unique (ordermodel_id),
  constraint pk_invoice primary key (id)
);

create table order_model (
  id                            bigint auto_increment not null,
  address_street                varchar(255),
  address_number                bigint,
  address_number_add            varchar(255),
  postal_code                   varchar(255),
  user_id                       bigint not null,
  constraint pk_order_model primary key (id)
);

create table order_products (
  id                            bigint auto_increment not null,
  order_id                      bigint not null,
  orderedproduct_id             bigint,
  price_at_ordertime            float,
  quantity                      bigint,
  constraint uq_order_products_orderedproduct_id unique (orderedproduct_id),
  constraint pk_order_products primary key (id)
);

create table product (
  id                            bigint auto_increment not null,
  productname                   varchar(255),
  description                   varchar(255),
  price                         float,
  viewed                        bigint,
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

alter table invoice add constraint fk_invoice_ordermodel_id foreign key (ordermodel_id) references order_model (id) on delete restrict on update restrict;

alter table order_model add constraint fk_order_model_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_order_model_user_id on order_model (user_id);

alter table order_products add constraint fk_order_products_order_id foreign key (order_id) references order_model (id) on delete restrict on update restrict;
create index ix_order_products_order_id on order_products (order_id);

alter table order_products add constraint fk_order_products_orderedproduct_id foreign key (orderedproduct_id) references product (id) on delete restrict on update restrict;

alter table product add constraint fk_product_categories_id foreign key (categories_id) references category (id) on delete restrict on update restrict;
create index ix_product_categories_id on product (categories_id);


# --- !Downs

alter table invoice drop foreign key fk_invoice_ordermodel_id;

alter table order_model drop foreign key fk_order_model_user_id;
drop index ix_order_model_user_id on order_model;

alter table order_products drop foreign key fk_order_products_order_id;
drop index ix_order_products_order_id on order_products;

alter table order_products drop foreign key fk_order_products_orderedproduct_id;

alter table product drop foreign key fk_product_categories_id;
drop index ix_product_categories_id on product;

drop table if exists category;

drop table if exists forgotten_password_code;

drop table if exists invoice;

drop table if exists order_model;

drop table if exists order_products;

drop table if exists product;

drop table if exists user;

