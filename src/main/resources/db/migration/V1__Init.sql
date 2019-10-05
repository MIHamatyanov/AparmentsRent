create sequence hibernate_sequence start 1 increment 1;

create table address
(
    id           int8 not null,
    district     text,
    house_number int4 not null,
    street       text not null,
    primary key (id)
);

create table announcement
(
    id            int8 not null,
    creation_date timestamp,
    description   text,
    flat_id       int8 not null,
    user_id       int8 not null,
    primary key (id)
);

create table flat
(
    id           int8 not null,
    floor        int2 not null,
    metric_area  int4 not null,
    price        int4 not null,
    rooms_count  int2 not null,
    total_floors int2 not null,
    address_id   int8 not null,
    primary key (id)
);

create table usr
(
    id           int8 not null,
    username     text not null,
    password     text not null,
    email        text,
    name         text,
    phone_number text not null,
    primary key (id)
);

alter table if exists announcement
    add constraint announcement_flat_fk foreign key (flat_id) references flat;

alter table if exists flat
    add constraint flat_address_fk foreign key (address_id) references address;

alter table if exists announcement
    add constraint announcement_user_fk foreign key (user_id) references usr;
