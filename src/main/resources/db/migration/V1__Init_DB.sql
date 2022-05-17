create table basket
(
    id      bigint not null,
    user_id bigint,
    primary key (id)
) engine=InnoDB
Hibernate:
create table basket_product_in_basket_list
(
    basket_id                 bigint not null,
    product_in_basket_list_id bigint not null
) engine=InnoDB
Hibernate:
create table hibernate_sequence
(
    next_val bigint
) engine=InnoDB
Hibernate: insert into hibernate_sequence values ( 1 )
Hibernate:
create table orders
(
    id        bigint not null,
    address   varchar(255),
    date      date,
    paymant   varchar(255),
    price     decimal(19, 2),
    telephone varchar(255),
    user_id   bigint,
    primary key (id)
) engine=InnoDB
Hibernate:
create table orders_product_list
(
    orders_id       bigint not null,
    product_list_id bigint not null
) engine=InnoDB
Hibernate:
create table product
(
    id                     bigint not null,
    description            longtext,
    filename               varchar(255),
    name                   varchar(255),
    price                  decimal(19, 2),
    product_manufactory_id bigint,
    product_type_id        bigint,
    primary key (id)
) engine=InnoDB
Hibernate:
create table product_rating_list
(
    product_id     bigint not null,
    rating_list_id bigint not null
) engine=InnoDB
Hibernate:
create table product_in_basket
(
    id         bigint  not null,
    count      integer not null,
    product_id bigint,
    primary key (id)
) engine=InnoDB
Hibernate:
create table product_manufactory
(
    id   bigint not null,
    date datetime(6),
    name varchar(255),
    primary key (id)
) engine=InnoDB
Hibernate:
create table product_type
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
) engine=InnoDB
Hibernate:
create table rating
(
    id      bigint  not null,
    grade   integer not null,
    user_id bigint,
    primary key (id)
) engine=InnoDB
Hibernate:
create table usr
(
    id       bigint not null,
    active   bit    not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
) engine=InnoDB
Hibernate:
create table user_role
(
    user_id bigint not null,
    role    varchar(255)
) engine=InnoDB
Hibernate:
alter table basket_product_in_basket_list
    add constraint UK_49mbflg9c9s0pq41b7fg1s37b unique (product_in_basket_list_id) Hibernate:
alter table product_rating_list
    add constraint UK_3iwprocp13e58c6w983di8ais unique (rating_list_id) Hibernate:
alter table basket
    add constraint FKfp7yinn3dh4sy1ia364xp3d9g foreign key (user_id) references usr (id) Hibernate:
alter table basket_product_in_basket_list
    add constraint FKm49a4il4b8pv5jdpwiatqlk1y foreign key (product_in_basket_list_id) references product_in_basket (id) Hibernate:
alter table basket_product_in_basket_list
    add constraint FKcmk93f6jphhbp3kuapy1s7t6y foreign key (basket_id) references basket (id) Hibernate:
alter table orders
    add constraint FKel9kyl84ego2otj2accfd8mr7 foreign key (user_id) references usr (id) Hibernate:
alter table orders_product_list
    add constraint FKjc60nlpsvvqqhtvig2bhmwrbf foreign key (product_list_id) references product (id) Hibernate:
alter table orders_product_list
    add constraint FK4r5p02f5otm5r54l63eoivyh9 foreign key (orders_id) references orders (id) Hibernate:
alter table product
    add constraint FKbd94bw5bcxjjv4wge7y7allyg foreign key (product_manufactory_id) references product_manufactory (id) Hibernate:
alter table product
    add constraint FKlabq3c2e90ybbxk58rc48byqo foreign key (product_type_id) references product_type (id) Hibernate:
alter table product_rating_list
    add constraint FKmp8cw2jjbkr4de6h1st43lik1 foreign key (rating_list_id) references rating (id) Hibernate:
alter table product_rating_list
    add constraint FK76csk4ijiva9xqo53tnfo28to foreign key (product_id) references product (id) Hibernate:
alter table product_in_basket
    add constraint FKqoaeppitf37kjop4n53jmehmf foreign key (product_id) references product (id) Hibernate:
alter table rating
    add constraint FKpn05vbx6usw0c65tcyuce4dw5 foreign key (user_id) references usr (id) Hibernate:
alter table user_role
    add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references usr (id)

