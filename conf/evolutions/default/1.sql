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
  user_id                       bigint,
  invoice_date                  datetime(6),
  country                       varchar(255),
  zipcode                       varchar(255),
  street                        varchar(255),
  street_number                 varchar(255),
  address_extra                 varchar(255),
  shipping_costs                float,
  constraint uq_invoice_ordermodel_id unique (ordermodel_id),
  constraint pk_invoice primary key (id)
);

create table invoice_row (
  id                            bigint auto_increment not null,
  description                   varchar(255),
  invoice_id                    bigint,
  unit_price                    float,
  quantity                      integer,
  constraint pk_invoice_row primary key (id)
);

create table `order` (
  id                            bigint auto_increment not null,
  street                        varchar(255),
  street_number                 varchar(255),
  address_extra                 varchar(255),
  zipcode                       varchar(255),
  country                       varchar(255),
  city                          varchar(255),
  company                       varchar(255),
  notes                         varchar(255),
  order_time                    datetime(6),
  user_id                       bigint not null,
  constraint pk_order primary key (id)
);

create table order_product (
  id                            bigint auto_increment not null,
  order_id                      bigint not null,
  orderedproduct_id             bigint,
  price_at_ordertime            float,
  quantity                      integer,
  constraint pk_order_product primary key (id)
);

create table product (
  id                            bigint auto_increment not null,
  productname                   varchar(255),
  description                   varchar(255),
  filename                      varchar(255),
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

alter table invoice add constraint fk_invoice_ordermodel_id foreign key (ordermodel_id) references `order` (id) on delete restrict on update restrict;

alter table invoice add constraint fk_invoice_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_invoice_user_id on invoice (user_id);

alter table invoice_row add constraint fk_invoice_row_invoice_id foreign key (invoice_id) references invoice (id) on delete restrict on update restrict;
create index ix_invoice_row_invoice_id on invoice_row (invoice_id);

alter table `order` add constraint fk_order_user_id foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_order_user_id on `order` (user_id);

alter table order_product add constraint fk_order_product_order_id foreign key (order_id) references `order` (id) on delete restrict on update restrict;
create index ix_order_product_order_id on order_product (order_id);

alter table order_product add constraint fk_order_product_orderedproduct_id foreign key (orderedproduct_id) references product (id) on delete restrict on update restrict;
create index ix_order_product_orderedproduct_id on order_product (orderedproduct_id);

alter table product add constraint fk_product_categories_id foreign key (categories_id) references category (id) on delete restrict on update restrict;
create index ix_product_categories_id on product (categories_id);


# --- !Downs

alter table invoice drop foreign key fk_invoice_ordermodel_id;

alter table invoice drop foreign key fk_invoice_user_id;
drop index ix_invoice_user_id on invoice;

alter table invoice_row drop foreign key fk_invoice_row_invoice_id;
drop index ix_invoice_row_invoice_id on invoice_row;

alter table `order` drop foreign key fk_order_user_id;
drop index ix_order_user_id on `order`;

alter table order_product drop foreign key fk_order_product_order_id;
drop index ix_order_product_order_id on order_product;

alter table order_product drop foreign key fk_order_product_orderedproduct_id;
drop index ix_order_product_orderedproduct_id on order_product;

alter table product drop foreign key fk_product_categories_id;
drop index ix_product_categories_id on product;

drop table if exists category;

drop table if exists forgotten_password_code;

drop table if exists invoice;

drop table if exists invoice_row;

drop table if exists `order`;

drop table if exists order_product;

drop table if exists product;

drop table if exists user;

