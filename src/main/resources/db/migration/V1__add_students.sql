create table if not exists students
(
    id                bigserial primary key,
    first_name        varchar(255) not null,
    last_name         varchar(255) not null,
    registration_date timestamp    not null
);

alter table students
    owner to postgres;

insert into students (first_name, last_name, registration_date)
values ('Bob', 'Ross', current_timestamp),
       ('Mike', 'Tyson', current_timestamp),
       ('Connor', 'McGregor', current_timestamp)