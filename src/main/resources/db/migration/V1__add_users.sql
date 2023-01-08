create table if not exists users
(
    id                bigserial primary key,
    first_name        varchar(255) not null,
    last_name         varchar(255) not null,
    username          varchar(255) not null unique,
    password          varchar(512) not null,
    registration_date timestamp    not null
);

alter table users
    owner to postgres;

insert into users (id, first_name, last_name, username, password, registration_date)
values (0,
        'admin',
        'admin',
        'admin',
        '$2a$12$tbVMEjv2G61M5ucrHW0ljeHK6ZHxRj9qo2XRjbIzk5T7Zq3Ld/7Wy',
        current_timestamp);