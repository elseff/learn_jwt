create table if not exists roles
(
    id   serial      not null
        constraint roles_pkey
            primary key,
    name varchar(30) not null
        constraint roles_name_key
            unique
);

alter table roles
    owner to postgres;

insert into roles(name)
values ('ROLE_USER'),
       ('ROLE_ADMIN')